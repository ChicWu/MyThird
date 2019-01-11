package com.zhibei.Thread;

import com.zhibei.main.Test0;
import com.zhibei.utils.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ThreadIo implements Runnable{
    private int i;

    public ThreadIo(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        File f = new File("F:\\com.zhibei\\IOTest\\aaa.txt");
        File fout = new File("F:\\com.zhibei\\IOTest\\aaa"+i+".txt");
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
            }catch (IOException e){
                e.printStackTrace();
            }
            try (FileInputStream fis = new FileInputStream(f); FileOutputStream fos = new FileOutputStream(fout,true)){
                int temp = 0;
                byte [] buf = new byte[1024];
                while( (temp=fis.read(buf)) != -1){
                    fos.write(buf,0,temp);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        System.out.println("Iocopy used time ["+(System.currentTimeMillis()- Test0.start)+"]");
    }
}
