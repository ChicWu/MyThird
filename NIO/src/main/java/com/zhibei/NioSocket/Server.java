package com.zhibei.NioSocket;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;

public class Server {
    private Selector selector;
    private ByteBuffer readBuffer = ByteBuffer.allocate(1024);//调整缓存的大小可以看到打印输出的变化
    private String str;ByteBuffer sendBuffer = ByteBuffer.allocate(1024);//调整缓存的大小可以看到打印输出的变化


    public void start() throws IOException {
        // 打开服务器套接字通道
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 服务器配置为非阻塞
        ssc.configureBlocking(false);
        // 进行服务的绑定
        ssc.bind(new InetSocketAddress("localhost", 8001));

        // 通过open()方法找到Selector
        selector = Selector.open();
        // 注册到selector，等待连接
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        //如果线程没有被中断
        while (!Thread.currentThread().isInterrupted()) {
            selector.select();
            //获取次选择器的选择键集
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = keys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                //如果这个密钥无效跳过此次循环
                if (!key.isValid()) {
                    continue;
                }
                if (key.isAcceptable()) {
                    accept(key);
                } else if (key.isReadable()) {
                    read(key);
                } else if (key.isWritable()) {
                    write(key);
                }
                keyIterator.remove(); //该事件已经处理，可以丢弃
            }
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

    public static void main(String[] args) throws IOException {
        System.out.println("server started...");
        new Server().start();
    }
}
