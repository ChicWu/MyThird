package com.zhibei.log;

import com.zhibei.util.Log;

public class Log1 {

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            Log.debug("HellWord");
            Log.error("HelloWord");
        }
    }
}
