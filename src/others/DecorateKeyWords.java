/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package others;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author liangzl2
 */
class DecorateKeyWords{
    //java中的关键字
    private static final String KEYWORDS[]={"abstract","assert","boolen","break","byte","case","catch","char","class","const",  
         "continue","default","do","double","else","enum","extends","final","finally","float","for",  
         "if","implements","import","instanceof","int","interface","long","native","new","package",  
         "private","protected","public","return","short","static","strictfp","super","switch","synchrpnized",  
         "this","throw","throws","transient","try","void","volatile","while"
    };  
     // 准备关键字     
    private static final HashSet<String> keywords = new HashSet<>(); 
    
    public static void decorateStyleConstants(SimpleAttributeSet attr, Font font){
        StyleConstants.setFontFamily(attr, font.getFamily());
        StyleConstants.setFontSize(attr, font.getSize());
        switch(font.getStyle()) {
            case Font.BOLD :
                StyleConstants.setBold(attr, true);
                break;
            case Font.ITALIC :
                StyleConstants.setItalic(attr, true);
                break;
            case Font.PLAIN :
                StyleConstants.setItalic(attr, false);
                StyleConstants.setBold(attr, false);
                break;
            case Font.BOLD | Font.ITALIC :
                StyleConstants.setItalic(attr, true);
                StyleConstants.setBold(attr, true);
                break;
            default :
                break;
        }
    }
    
    public static void decorateKeyWords(JTextPane tp, MyFont myFont) {
        //初始化关键字
        keywords.addAll(Arrays.asList(KEYWORDS));
        // 对所有关键词进行修饰颜色  
        String text = tp.getText().replaceAll("\\r", "");
        StyledDocument doc = tp.getStyledDocument();
        SimpleAttributeSet keyWordAttr = new SimpleAttributeSet();
        StyleConstants.setForeground(keyWordAttr, Color.cyan);
        decorateStyleConstants(keyWordAttr, myFont.getFont());
        SimpleAttributeSet otherWordAttr = new SimpleAttributeSet();
        StyleConstants.setForeground(otherWordAttr, myFont.getColor());
        decorateStyleConstants(otherWordAttr, myFont.getFont());
        ListIterator<WordNode> iterator1 = split(text, "\\s|\\{|\\}|\\(|\\)|\\<|\\>|\\.|\\n");
        while (iterator1.hasNext()) {
            WordNode wn = iterator1.next();
            if (keywords.contains(wn.getWord())) {
                doc.setCharacterAttributes(wn.getLocation(), wn.getWord().length(), keyWordAttr, true);
            } else {
                doc.setCharacterAttributes(wn.getLocation(), wn.getWord().length(), otherWordAttr, true);
            }
        }
        // 对注释行进行修饰不同的颜色  
        SimpleAttributeSet annotationAttr = new SimpleAttributeSet();
        StyleConstants.setForeground(annotationAttr, Color.green);
        decorateStyleConstants(annotationAttr, myFont.getFont());
        ListIterator<WordNode> iterator2 = split(text, "\\n");
        boolean exegesis = false; // 是否加了/*的注释  
        while (iterator2.hasNext()) {
            WordNode wn = iterator2.next();
            if (wn.getWord().startsWith("//")) {
                doc.setCharacterAttributes(wn.getLocation(), wn.getWord()
                        .length(), annotationAttr, true);
            } else if (wn.getWord().startsWith("/*") && wn.getWord().endsWith("*/")) {
                doc.setCharacterAttributes(wn.getLocation(), wn.getWord()
                        .length(), annotationAttr, true);
            } else if (wn.getWord().startsWith("/*") && !wn.getWord().endsWith("*/")) {
                exegesis = true;
                doc.setCharacterAttributes(wn.getLocation(), wn.getWord()
                        .length(), annotationAttr, true);
            } else if (!wn.getWord().startsWith("/*") && wn.getWord().endsWith("*/") && true == exegesis) {
                doc.setCharacterAttributes(wn.getLocation(), wn.getWord()
                        .length(), annotationAttr, true);
                exegesis = false;
            } else if (true == exegesis) {
                doc.setCharacterAttributes(wn.getLocation(), wn.getWord()
                        .length(), annotationAttr, true);
            }
        }
    }
     
    /**
     * 按照指定的多个字符进行字符串分割，如‘ ’或‘,’等
     *
     * @param str 被分割的字符串
     * @param regexs 要分割所需的符号
     * @return 包含WordNodeList对象的iterator
     */
    private static ListIterator<WordNode> split(String str, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        ArrayList<WordNode> nodeList = new ArrayList<>();
        int strStart = 0; // 分割单词的首位置  
        String s; // 分割的单词  
        WordNode wn; // StringNode节点  
        while (m.find()) {
            s = str.substring(strStart, m.start());
            if (!s.equals(new String())) {
                wn = new WordNode(strStart, s);
                nodeList.add(wn);
            }
            strStart = m.start() + 1;
        }
        s = str.substring(strStart, str.length());
        wn = new WordNode(strStart, s);
        nodeList.add(wn);
        return nodeList.listIterator();
    }  
}