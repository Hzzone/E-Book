package com.example.hzzone.e_book.Data;

/**
 * Created by Hzzone on 2017/5/4.
 */

/**
 * This class describe the user's reading configure
 */
public class Config {
    private static float fontSize = 1;
    private static boolean isNightState = false;

    public static float getFontSize() {
        return fontSize;
    }

    public static void setFontSize(float fontSize) {
        Config.fontSize = fontSize;
    }

    public static boolean isNightState() {
        return isNightState;
    }

    public static void setIsNightState(boolean isNightState) {
        Config.isNightState = isNightState;
    }

}
