package com.influans.sp.utils;

import java.util.Random;

public class ColorUtils {

    public static String getRandomColor() {
        Random ra = new Random();
        int r, g, b;
        r = ra.nextInt(255);
        g = ra.nextInt(255);
        b = ra.nextInt(255);
        String hex = String.format("#%02x%02x%02x", r, g, b);
        return hex;
    }
}
