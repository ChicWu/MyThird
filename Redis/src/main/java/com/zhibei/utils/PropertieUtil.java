package com.zhibei.utils;

import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  配置文件的读取工具类
 */
public class PropertieUtil {
    private static Properties properties = new Properties();
    private static HashSet<String> keySet = new HashSet<>();
   static {
       try{
           InputStream in = PropertieUtil.class.getClassLoader().getResourceAsStream("config,properties");
           properties.load(in);
           Enumeration<String> enumeration = (Enumeration<String>) properties.propertyNames();
           while (enumeration.hasMoreElements()){
               String key = enumeration.nextElement();
               keySet.add(key);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
    public static String read(String key) {
        String value;
       if (keyFind(key)){
            value = properties.getProperty(key);
            if (value.equals("")){
                return null;
            }
       }else {
           throw new RuntimeException("read Exception properties key:["+key+"] notFind");
       }
       return value;
    }

    public static Integer  readInteger(String key){
        String strValue = read(key);
        Integer value ;
        Pattern p = Pattern.compile("\\d+");
        Matcher matcher = p.matcher(strValue);
        if(Objects.equals("",strValue)){
            return null;
        }else if (matcher.matches()){
            value = new Integer(strValue);
        }else {
            System.out.println(matcher.matches());
            throw new RuntimeException("readInteger Exception key:["+key+"] ,value["+strValue+"]is not Integer");
        }
        return value;
    }

    private static boolean keyFind(String findKey){
        for (String key: keySet) {
            if (Objects.equals(key,findKey)){
                return true;
            }
        }
        return false;
    }

    private Set<String> getKeySet(){
        return keySet;
    }
}
