package com.zhibei.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import org.apache.log4j.Logger;

public class Test {
    private static final Logger logger = Logger.getLogger(Test.class);
    public static void main(String[] args) throws IOException{
        int count=1000;
        long begin = System.currentTimeMillis();
        for(int i=0;i<count;i++){
            ioCopy();
        }
        long end = System.currentTimeMillis();
        logger.info("ioCopy()耗时："+(end-begin)+"毫秒.");

        long begin1 = System.currentTimeMillis();
        for(int i=0;i<count;i++){
            nioCopy();
        }
        long end1 = System.currentTimeMillis();
        logger.info("nioCopy()耗时："+(end1-begin1)+"毫秒.");
    }
    /**
     * 传统流式io的方式cofy文件
     * @throws IOException
     */
    public static void ioCopy() throws IOException{
        File f = new File("F:\\aaa.txt");
        File fout = new File("F:\\aaa1.txt");
        if(!f.exists()){
            logger.info("要读取的文件不存在");
        }else{
            if(!fout.exists()){
                fout.createNewFile();
            }
            try (FileInputStream fis = new FileInputStream(f); FileOutputStream fos = new FileOutputStream(fout,true)){
                int temp = 0;
                byte [] buf = new byte[1024];
                while( (temp=fis.read(buf)) != -1){
                    fos.write(buf,0,temp);
                }
            }
        }
    }
    /**
     * nio的方式cofy文件
     * @throws IOException
     */
    public static void nioCopy() throws IOException{
        File f = new File("F:\\bbb.txt");
        File fout = new File("F:\\bbb1.txt");
        if(!f.exists()){
            logger.info("要读取的文件不存在");
        }else{
            if(!fout.exists()){
                fout.createNewFile();
            }
            try (FileInputStream fis = new FileInputStream(f);
                 FileChannel fc = fis.getChannel();
                 FileOutputStream fos = new FileOutputStream(fout,true);
                 FileChannel fcout = fos.getChannel()){
                 ByteBuffer bf = ByteBuffer.allocate(1024);
                while(fc.read(bf) != -1){
                    bf.flip();
                    fcout.write(bf);
                    bf.clear();
                }
                fout.delete();
            }



        }
    }
}
