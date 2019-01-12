package com.zhibei.NioFile;

import com.zhibei.utils.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ThreadNio implements Runnable {
    private int i;

    public ThreadNio(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        File f = new File("F:\\com.zhibei\\IOTest\\bbb.txt");
        File fout = new File("F:\\com.zhibei\\IOTest\\bbb"+i+".txt");
        if(!f.exists()){
            Log.info("要读取的文件不存在");
        }else{
            try {
                if(!fout.exists()){
                    fout.createNewFile();
                }else {
                    fout.delete();
                    fout.createNewFile();
                }
            }catch (Exception e){
                e.printStackTrace();
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
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        System.out.println("Niocopy used time ["+(System.currentTimeMillis()- Log.start)+"]");

    }
}
