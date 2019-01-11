package com.zhibei.Demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

public class Client implements Runnable {
    private static SocketChannel socketChannel;
    private static Selector selector;
    private ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
    private ByteBuffer readBuffer = ByteBuffer.allocate(1024);

    static{
        try {
            socketChannel  = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.bind(new InetSocketAddress("localhost",8081));
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);
            Scanner scanner = new Scanner(System.in);
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()){
                SelectionKey selectionKey = keyIterator.next();
                keyIterator.remove();
                if (selectionKey.isConnectable()) {
                    socketChannel.finishConnect();
                    socketChannel.register(selector, SelectionKey.OP_WRITE);
                    System.out.println("server connected...");
                    break;
                } else if (selectionKey.isWritable()) { //写数据
                    System.out.print("please input message:");
                    String message = scanner.nextLine();
                    //ByteBuffer writeBuffer = ByteBuffer.wrap(message.getBytes());
                    writeBuffer.clear();
                    writeBuffer.put(message.getBytes());
                    //将缓冲区各标志复位,因为向里面put了数据标志被改变要想从中读取数据发向服务器,就要复位
                    writeBuffer.flip();
                    socketChannel.write(writeBuffer);

                    //注册写操作,每个chanel只能注册一个操作，最后注册的一个生效
                    //如果你对不止一种事件感兴趣，那么可以用“位或”操作符将常量连接起来
                    //int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE;
                    //使用interest集合
                    socketChannel.register(selector, SelectionKey.OP_READ);

                } else if (selectionKey.isReadable()){
                    //读取数据
                    System.out.print("receive message:");
                    SocketChannel client = (SocketChannel) selectionKey.channel();
                    //将缓冲区清空以备下次读取
                    readBuffer.clear();
                    int num = client.read(readBuffer);
                    System.out.println(new String(readBuffer.array(),0, num));
                    //注册读操作，下一次读取
                    socketChannel.register(selector, SelectionKey.OP_WRITE);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
