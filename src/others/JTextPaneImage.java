/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package others;

import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author liangzl2
 */
public class JTextPaneImage extends JFrame{

    /**
     * 若你想在JTextPane上置入图形或其他组件(如表格或按钮)，你可以分别使用JTextPane所提供的insetIcon()与insertComponent()
     * http://blog.csdn.net/dean_deng/article/details/6621458
     * 本JTextPane更改自如下：
     * http://blog.csdn.net/lishigui/article/details/2959094
     * 其中运用了文件选择器：http://www.cnblogs.com/happyPawpaw/archive/2013/04/27/3046414.html
     * Swing线程安全响应：http://blog.csdn.net/bruno231/article/details/42098277、 http://blog.sina.com.cn/s/blog_1660ab4300102xgpz.html
     * JTextPane如何取消自动换行的问题：http://blog.csdn.net/ycb1689/article/details/52459599
     * 运用文本编辑器对JTextPane进行关键词渲染：http://www.cnblogs.com/hujunzheng/p/5232125.html
     * 
     */
    private static final long serialVersionUID = 1L;
    private JTextPane inputpane;
    private JTextPane ouputpane;
    private final JScrollPane scrollMypane;
    private final JScrollPane scrollPane;
    
    public JTextPaneImage() {
        super("JTextPane Test");
        Container container = getContentPane();
        container.setLayout(null);
        ouputpane = new JTextPane();
        
        ouputpane.setEditable(false);
        scrollPane = new JScrollPane(ouputpane);
        scrollPane.setBounds(new Rectangle(0,10,400,200));
        JButton insertButton = new JButton("插入圖片");
        insertButton.setBounds(new Rectangle(0,220,100,40));
        
        insertButton.addActionListener((ActionEvent e) -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.showOpenDialog(JTextPaneImage.this);
            inputpane.insertIcon(new ImageIcon(chooser.getSelectedFile().toString()));
        });
        
        inputpane = new JTextPane();
        
        scrollMypane = new JScrollPane(inputpane);
        scrollMypane.setBounds(new Rectangle(0,270,400,200));
        
        JButton sendButton = new JButton("發送");
        sendButton.setBounds(new Rectangle(170,500,160,40));
        sendButton.addActionListener((ActionEvent e) -> {
//            Vector picVector = new Vector();
//            for(int i = 0; i < mypane.getStyledDocument().getRootElements()[0].getElement(0).getElementCount(); i++){
//                Icon icon = StyleConstants.getIcon(mypane.getStyledDocument().getRootElements()[0].getElement(0).getElement(i).getAttributes());
//                if(icon != null){
//                    picVector.add(icon.toString());
//                }
//            }
            StyledDocument doc = inputpane.getStyledDocument();
            java.util.List<ImageIcon> picVector = new ArrayList<>();
            for (int i = 0; i < doc.getRootElements()[0].getElementCount(); i++) {
                javax.swing.text.Element root = doc.getRootElements()[0].getElement(i);
                for (int j = 0; j < root.getElementCount(); j++) {
                    ImageIcon icon = (ImageIcon) StyleConstants.getIcon(root.getElement(j).getAttributes());
                    if (icon != null) {
                        picVector.add(icon);
                    }
                }
            }
            
            
            System.err.println("發送 JTextPane 的內容是 :/n"+inputpane.getText().replace("\r\n", "\\r\\n"));
            int k = 0;
            String tmp=rmMicroSoftWindowsEnter(inputpane.getText());
            for(int i = 0; i < inputpane.getStyledDocument().getLength(); i++){
                if(inputpane.getStyledDocument().getCharacterElement(i).getName().equals("icon")){
                    System.out.println("你在第 " + i + " 位置插入了圖片，圖片的路徑是 :");
                    ouputpane.insertIcon(new ImageIcon(picVector.get(k).toString()));
                    System.err.println(picVector.get(k).toString());
                    k++;
                }else{
                    try {
                        System.out.println("localength=="+ouputpane.getDocument().getLength()+"; StyledDoclength=="+ouputpane.getStyledDocument().getLength());
                        String s = inputpane.getStyledDocument().getText(i,1);
                        String s1 = inputpane.getStyledDocument().getText(i,1);
                        if(s.equals("\r")){
                            s="\\r";
                        }else if(s.equals("\n")){
                            s="\\n";
                        }else if(s.equals("\r\n")){
                            s="\\r\\n";
                        }
                        System.out.print("; str=="+s+"; i=="+i+"; ");
                        //重点修改pane.getText().length()——>pane.getDocument().getLength()
                        ouputpane.getStyledDocument().insertString(ouputpane.getDocument().getLength(), s1, null);
//                        pane.getStyledDocument()
                    } catch (BadLocationException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            picVector.clear();
        });
        container.add(scrollPane);
        container.add(insertButton);
        container.add(scrollMypane);
        container.add(sendButton);
        setSize(400,600);
        setVisible(true);
        
    }
    public static void main2() {
        new JTextPaneImage().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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