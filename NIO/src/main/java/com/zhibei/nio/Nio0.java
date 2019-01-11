package com.zhibei.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Nio0 {
    public static void main(String[] args) {
        long start = System.nanoTime();
        method1();
        System.out.println();
        System.out.println(System.nanoTime()-start);
        start = System.nanoTime();
        method2();
        System.out.println();
        System.out.println(System.nanoTime()-start);
    }

    public static void method1(){
        try(RandomAccessFile aFile = new RandomAccessFile("F:\\aaa.txt","rw")){
            FileChannel fileChannel = aFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(40960);
            int bytesRead = fileChannel.read(buf);
//            System.out.println(bytesRead);
            while(bytesRead != -1){
                buf.flip();
                while(buf.hasRemaining()){
                    buf.get();
//                    System.out.print((char)buf.get());
                }
                buf.compact();
                bytesRead = fileChannel.read(buf);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void method2(){
        try(InputStream in = new BufferedInputStream(new FileInputStream("F:\\bbb.txt"))){
            byte [] buf = new byte[40960];
            int bytesRead = in.read(buf);
            while(bytesRead != -1){
                for(int i=0;i<bytesRead;i++) {
                    byte b = buf[i];
                }
//                    System.out.print((char)buf[i]);
                bytesRead = in.read(buf);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
