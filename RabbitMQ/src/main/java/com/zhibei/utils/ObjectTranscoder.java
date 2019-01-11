package com.zhibei.utils;


import java.io.*;

public class ObjectTranscoder {

    private ObjectTranscoder() {
    }

    public static byte[] serialize(Object value) {
        if (value == null) {
            throw new NullPointerException("Can't serialize null");
        }
        byte[] rv=null;
        try(ByteArrayOutputStream bos = new ByteArrayOutputStream();ObjectOutputStream os = new ObjectOutputStream(bos)) {
            os.writeObject(value);
            rv = bos.toByteArray();
        }catch (IOException e) {
            throw new IllegalArgumentException("Non-serializable object", e);
        }
        return rv;
    }

    public static Object deserialize(byte[] in) {
        Object rv=null;
        if (in!=null){
            try (ByteArrayInputStream bis = new ByteArrayInputStream(in);ObjectInputStream is = new ObjectInputStream(bis)){
                rv = is.readObject();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return rv;
    }




}
