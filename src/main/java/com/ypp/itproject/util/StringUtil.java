package com.ypp.itproject.util;

public class StringUtil {
    public static boolean isExcelled(String content, int lengthLimit){
        boolean flag = false;
        if(content.length()>lengthLimit){
            flag = true;
        }
        return flag;
    }
}
