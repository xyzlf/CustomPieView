package com.xyzlf.view.pieviewlib;

import java.io.Serializable;

/**
 * zhanglifeng on 2016/8/22.
 */
public class CustomPieData implements Serializable {

    private String color; //颜色
    private float angel; //角度

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getAngel() {
        return angel;
    }

    public void setAngel(float angel) {
        this.angel = angel;
    }

}
