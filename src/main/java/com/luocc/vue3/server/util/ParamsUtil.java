package com.luocc.vue3.server.util;


public class ParamsUtil {

    private static final String PERCENT = "%";

    public static String joinLike(String str) {
        if (null == str || "".equals(str)) {
            return "";
        } else {
            String cstr = str.replaceAll("[?]", "").replaceAll("[*]", "").replaceAll("[#]", "");
            return PERCENT.concat(cstr).concat(PERCENT);
        }
    }

    public static String toString(Object obj) {
        return null == obj ? null : obj.toString();
    }
}
