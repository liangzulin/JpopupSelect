/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lzl.windows;

import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author flash胜龙
 */
public class ImageTextPaneController {
    
    
    public static void add2(JTextPane inputJTpane,
                           JTextPane outputJTpane,
                             JButton insertButton,
                             JButton sendButton,
                              JFrame jf){
        /**
         * Add file chooser for image choosing
         */
        insertButton.addActionListener((ActionEvent e) -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = chooser.showOpenDialog(jf);
            if (result == JFileChooser.APPROVE_OPTION) {
                inputJTpane.insertIcon(new ImageIcon(chooser.getSelectedFile().toString()));
            }
        });

        
        /**
         * Add actionlistener for send button
         */
        sendButton.addActionListener((ActionEvent e) -> {
            StyledDocument inputSDoc = inputJTpane.getStyledDocument(); //获取读取的StyledDocument
            StyledDocument outSDoc = outputJTpane.getStyledDocument(); //获取欲输出的StyledDocument
            for (int i = 0; i < inputSDoc.getLength(); i++) { //遍历读取的StyledDocument
                if (inputSDoc.getCharacterElement(i).getName().equals("icon")) { //如果发现是icon元素，那么：
                    Element ele = inputSDoc.getCharacterElement(i);
                    ImageIcon icon = (ImageIcon) StyleConstants.getIcon(ele.getAttributes());
                    outputJTpane.insertIcon(new ImageIcon(icon.toString()));//插入icon元素
                } else {//如果不是icon（可以判断是文字，因为目前只有图片元素插入）
                    try {
                        String s = inputSDoc.getText(i, 1);
                        outSDoc.insertString(outputJTpane.getCaretPosition(), s, null);//从光标处插入文字
                    } catch (BadLocationException e1) {
                        e1.printStackTrace();
                    }
                }
            }    
        });
        //===========End of sendButton
        
    }
    
    
    public static void setJComboBox(JComboBox jcb){
        jcb.setRenderer(new ComboBoxRenderer());
//        JPanel jp=new JPanel();
//        jp.add(new JButton("hihihi!"));
//        jcb.addItem(jp.getUI());
//        jcb.addItem(new ImageIcon("C:\\Users\\liangzl2\\Desktop\\opencl-3.png"));
    }
}
