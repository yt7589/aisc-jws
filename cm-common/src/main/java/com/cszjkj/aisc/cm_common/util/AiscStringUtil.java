package com.cszjkj.aisc.cm_common.util;

import java.util.Random;

public class AiscStringUtil {
    public final static String DIGITS = "0123456789";
    public final static String ALPHABETS_SUPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public final static String ALPHABETS_LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    public final static String ALPHABETS_NO_CASE = ALPHABETS_SUPER_CASE + ALPHABETS_LOWER_CASE;
    public final static String ALPHABETS_ALL = DIGITS + ALPHABETS_NO_CASE;

    public static String generateRandomCode(String alphabets, int size) {
        StringBuilder code = new StringBuilder(size);
        Random random = new Random();
        int loc = 0;
        for (int i=0; i<size; i++) {
            loc = random.nextInt(alphabets.length());
            code.append(alphabets.charAt(loc));
        }
        return code.toString();
    }
}
