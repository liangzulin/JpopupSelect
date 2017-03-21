/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lzl;

import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.DefaultListModel;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.Caret;

/**
 *
 * @author liangzl2
 */
public class JCodeCompletePopupMenu {
    private final JEditorPane jep1;
    private DefaultListModel dlm;
    private JList jl1;
    private JPopupMenu popupMenu;
    private final ArrayList<String> items;
    private String curtAnalysStr;

    public String getCurtAnalysStr() {
        return curtAnalysStr;
    }
    
    public JCodeCompletePopupMenu(JEditorPane jep1, ArrayList<String> items) {
        this.jep1 = jep1;
        this.items = items;
        initComponent();
    }
    
    public JCodeCompletePopupMenu(JEditorPane jep1) {
        this.jep1 = jep1;
        Locale[] locales = Locale.getAvailableLocales();
        /** 导入国家语言样板数据 */
        items = new ArrayList<>();
        for (Locale locale : locales) {
            String item = locale.getDisplayName();
            items.add(item);
        }
        initComponent();
    }
    
    private void initComponent(){
        dlm = new DefaultListModel();
        jl1 = new JList();
        jl1.setModel(dlm);
        jl1.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent fe) {
                jep1.requestFocus();
            }
            @Override public void focusLost(FocusEvent fe) {}
        });
        popupMenu = new JPopupMenu();
        popupMenu.add(jl1);
        jep1.addKeyListener(new mKeyListener());
        jep1.addCaretListener(new mCaretListener());
        
    }
    
    private class mKeyListener implements KeyListener{
        //用于监听所有在文本框的按键指令
            @Override public void keyTyped(KeyEvent e) {
                
            }
            @Override public void keyPressed(KeyEvent e) {
                /** 
                 * 这样直接将整个上下按钮的指令拦截到popup悬浮框，
                 * 输入框按上下就没有位置移动的功能了
                 */
                int keycode = e.getKeyCode();
                if(keycode == KeyEvent.VK_DOWN || keycode == KeyEvent.VK_UP){
                    // 如果输入的上下方向键
                    if (popupMenu.isShowing()) { //所以增加该窗口是否show的判断
                        e.setSource(jl1);    //设置新的event源
                        jl1.dispatchEvent(e);//传输给jl1，dispatch: vt. 派遣；分派
                    }
                }else if(keycode == KeyEvent.VK_ENTER){
                    //System.out.println("e.getKeyCode()=="+e.getKeyCode());
                    /** 把消息源扔过去，免得在编辑器里面按回车换行了 */
                    e.setSource(popupMenu);
                    popupMenu.dispatchEvent(e);
                    
                        
                    if(popupMenu.isVisible()){
                        String selectedValue=jl1.getSelectedValue().toString();
                        System.out.println("Selected Value==" + selectedValue);
                        Caret crt = jep1.getCaret();
                        String str = jep1.getText();
                        
                        String[] strArry = str.split("\r");
                
                        StringBuilder sb = new StringBuilder();
                        for (String stmp : strArry) {
                            sb.append(stmp);
                        }
                        int dot = crt.getDot();
                        sb.insert(dot, selectedValue);
                        jep1.setText(sb.toString());
                        crt.setDot(dot+selectedValue.length());
                        
                        //jep1.setCaret(crt);
                        
                        popupMenu.setVisible(false);
                    }
                    
                    //if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    
                    //}
                }else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_BACK_SLASH && !popupMenu.isShowing()) {
                    //按键k开启提示窗口
                    Caret crt = jep1.getCaret();
                    Point p = crt.getMagicCaretPosition();
                    if (p == null) {
                    } else {
                        int x = (int)p.getX();
                        int y = (int)p.getY();
                        //设置窗体的显示位置，相对于母组件
                        popupMenu.show(e.getComponent(), x, y+20);
                        jl1.setSelectedIndex(0);
                        //System.out.println(x+" "+y+20);
                        /**
                         * 将焦点转移回到原来的编辑器
                         * Mac is of no use, Win10 it works
                         */
                        jep1.requestFocusInWindow(); 
                    }
                }else{
                    /**
                     * 其他按键按照正常的jEditorPane来运行，不进行处理
                     */
                }
//                switch(){
//                    case KeyEvent.VK_DOWN:
//                        
//                        break;
//                    case KeyEvent.VK_UP:
//                        if(popupMenu.isShowing()){ //所以增加该窗口是否show的判断
//                            e.setSource(jl1);
//                            jl1.dispatchEvent(e);
//                        }
//                        break;
//                }

                //System.out.println("e.isControlDown()"+e.isControlDown()+"; key=="+e.getKeyChar());
                //throw new UnsupportedOperationException("Not supported yet.");
                
            }
            @Override public void keyReleased(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
    }

    private class mCaretListener implements CaretListener{
        @Override
            public void caretUpdate(CaretEvent ce) {
                //该【光标监听器】用于获取光标附近所取得的文本，用于判断和显示提示符
                int dotposi = ce.getDot();
                
                Caret crt = jep1.getCaret();
                Point p = crt.getMagicCaretPosition();//获取该光标的像素点位置，用于打印
                if (p == null) {
                    //System.out.println("java.lang.NullPointerException");
                } else {
                    String str = jep1.getText();
                    // i从光标处的前一个字符开始历遍，直到找到空格
                    int i = ce.getDot()-1;
                    /**
                     *  0 1 2 3 4 5 6
                     * |a|b|c|d|e|f|g|
                     * 0 1 2 3 4 5 6 7
                     */
                    str=rmMicroSoftWindowsEnter(str);
                    /**
                     * 分隔处理条件，以空格或者回车为界
                     */
                    for(;i>0;i--){
                        if(i==0 || str.charAt(i)==' '){
                            break;
                        }else if(str.charAt(i)=='\n'){
                            break;
                        }
                    }
                    /**
                     * 根据逻辑边界确定探索的字符串范围后，进行一些处理得出字符串tmp
                     */
                    String tmp;
                    if(dotposi!=0 && i==0){
                        tmp = str.substring(i, dotposi);
                    }else if(dotposi!=0 && i!=0){
                        tmp = str.substring(i+1, dotposi);
                    }else{
                        tmp = "";
                    }
                    curtAnalysStr=tmp;
                    if(updateList(tmp)){
                        /**
                         * 此时有信息，就应该给予备选列表，并显示窗口
                         */
                        p = crt.getMagicCaretPosition();
                        
                        int x = (int)p.getX();
                        int y = (int)p.getY();
                        //设置窗体的显示位置，相对于母组件
                        popupMenu.show(jep1, x, y+20);
                        jl1.setSelectedIndex(0);
                        //System.out.println(x+" "+y+20);
                        /**
                         * 将焦点转移回到原来的编辑器
                         * Mac is of no use, Win10 it works
                         */
                        jep1.requestFocusInWindow(); 
                    }else{
                        popupMenu.setVisible(false);
//                        dlm.removeAllElements();
//                        dlm.addElement("<" + tmp + ">");
//                        dlm.addElement("MARK==" + ce.getMark());
//                        dlm.addElement("DOT==" + dotposi);
//                        
//                        dlm.addElement("x==" + p.getX());
//                dlm.setElementAt("y==" + p.getY(), 4);
//                        dlm.addElement("i==" + i);
                    }
                }
            }
    }
    
    private boolean updateList(String input){
        dlm.removeAllElements();
        if (!input.isEmpty()) {
            items.stream().filter((item) -> (item.toLowerCase().startsWith(input.toLowerCase()))).forEach((item) -> {
                dlm.addElement(item);
                //dlm.set(5, item);
            });
        }
        return dlm.size()!=0;
    }
    
    private void showPopupList(){
        /**
         * 每次show的时候，都应该检查一遍光标附近的文字，以及更新一遍popupmenu中的内容
         */
    }
    
    private String rmMicroSoftWindowsEnter(String str){
        /**
         * 13 10 问题： 出现了换行后不对称的问题是windows \r\n 的锅 此处需要对系统进行一个判断。解决办法：
         * 【直接去掉\r再拼合】
         */
        String[] strArry = str.split("\r");
        int lineCount = strArry.length;
        StringBuilder sb = new StringBuilder();
        for (String stmp : strArry) {
            sb.append(stmp);
        }
        return sb.toString();
    }
    
}
