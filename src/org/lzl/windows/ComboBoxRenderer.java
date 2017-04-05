/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lzl.windows;

import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author liangzl2
 */
class ComboBoxRenderer extends JLabel implements ListCellRenderer {
    private static final long serialVersionUID = 1L;
    
    @Override public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        //然后然后this就是继承的JLabel了,对它可以设置属性了:
        System.out.println("Obj=="+value.toString()+"; index=="+index+"; isSelected=="+isSelected+"; cellHasFocus=="+cellHasFocus);
        setIcon(new ImageIcon("C:\\Users\\liangzl2\\Desktop\\opencl-3.png"));
        setText(value.toString());
        if(value.getClass() == String.class){
            String s=(String) value;
            if(s.equals("Item 3")){
                this.setBackground(Color.yellow);
                this.setForeground(Color.red);
            }
        }
        //最后把设置好的控件返回就可以了,
        return this;
    }
}
