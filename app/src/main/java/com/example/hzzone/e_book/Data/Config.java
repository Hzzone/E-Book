package com.example.hzzone.e_book.Data;

/**
 * Created by Hzzone on 2017/5/4.
 */

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * This class describe the user's reading configure
 */
public class Config extends DataSupport{
    @Column(defaultValue = "18")
    private int fontSize;

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public boolean isNightState() {
        return isNightState;
    }

    public void setNightState(boolean nightState) {
        isNightState = nightState;
    }

    @Column(defaultValue = "false")
    private boolean isNightState;

}
