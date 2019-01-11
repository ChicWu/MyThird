package com.zhibei.Demo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class Service implements Runnable {
    private static ServerSocketChannel serverSocketChannel;
    private static Selector selector;
    private ByteBuffer readBuffer = ByteBuffer.allocate(1024);//调整缓存的大小可以看到打印输出的变化
    private ByteBuffer sendBuffer = ByteBuffer.allocate(1024);//调整缓存的大小可以看到打印输出的变化
    String str;
    static {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(InetAddress.getLocalHost(),8000));
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        try {
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (!Thread.currentThread().isInterrupted()){
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
               while (iterator.hasNext()){
                   SelectionKey selectionKey = iterator.next();
                   if (!selectionKey.isValid()) {
                       continue;
                   }
                   if (selectionKey.isAcceptable()) {
                       accept(selectionKey);
                   } else if (selectionKey.isReadable()) {
                       read(selectionKey);
                   } else if (selectionKey.isWritable()) {
                       write(selectionKey);
                   }
                   iterator.remove(); //该事件已经处理，可以丢弃
               }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void write(SelectionKey key) throws IOException, ClosedChannelException {
        //通过key获取创建次key的通道
        SocketChannel channel = (SocketChannel) key.channel();
        System.out.println("write:"+str);
        //
        sendBuffer.clear();
        sendBuffer.put(str.getBytes());
        //反转这个缓冲区
        sendBuffer.flip();
        channel.write(sendBuffer);
        channel.register(selector, SelectionKey.OP_READ);
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        // Clear out our read buffer so it's ready for new data
        this.readBuffer.clear();
//        readBuffer.flip();
        // Attempt to read off the channel
        int numRead;
        try {
            numRead = socketChannel.read(this.readBuffer);
        } catch (IOException e) {
            // The remote forcibly closed the connection, cancel
            // the selection key and close the channel.
            //取消该密钥的通道与选择器的注册，关闭    通道。
            key.cancel();
            socketChannel.close();
            return;
        }

        str = new String(readBuffer.array(), 0, numRead);
        System.out.println(str);
        socketChannel.register(selector, SelectionKey.OP_WRITE);
    }

    private void accept(SelectionKey key) throws IOException {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel clientChannel = ssc.accept();
        clientChannel.configureBlocking(false);
        clientChannel.register(selector, SelectionKey.OP_READ);
        System.out.println("a new client connected "+clientChannel.getRemoteAddress());
    }
}
