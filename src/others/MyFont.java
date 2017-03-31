/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package others;

import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author liangzl2
 */
class MyFont{
    private Font font;
    private Color color;

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    //字体名称索引
    private int familyIndex = -1;
    //字体大小索引
    private int sizeIndex = -1;
    //字体颜色索引
    private int colorIndex = -1;
    //字体风格索引
    private int styleIndex = -1;

    public int getFamilyIndex() {
        return familyIndex;
    }

    public int getSizeIndex() {
        return sizeIndex;
    }

    public int getColorIndex() {
        return colorIndex;
    }

    public int getStyleIndex() {
        return styleIndex;
    }

    public void setFamilyIndex(int familyIndex) {
        this.familyIndex = familyIndex;
    }

    public void setSizeIndex(int sizeIndex) {
        this.sizeIndex = sizeIndex;
    }

    public void setColorIndex(int colorIndex) {
        this.colorIndex = colorIndex;
    }

    public void setStyleIndex(int styleIndex) {
        this.styleIndex = styleIndex;
    }

    @Override
    public String toString() {
        return familyIndex + " " + sizeIndex + " " + styleIndex + " " + colorIndex + " \n" +
                font + " " + color; 
    }
    
}
