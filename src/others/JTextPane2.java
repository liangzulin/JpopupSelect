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
public class JTextPane2 extends JFrame{

    /**
     * 若你想在JTextPane上置入图形或其他组件(如表格或按钮)，你可以分别使用JTextPane所提供的insetIcon()与insertComponent()
     * http://blog.csdn.net/dean_deng/article/details/6621458
     */
    private static final long serialVersionUID = 1L;
    private JTextPane mypane;
    private JTextPane pane;
    private final JScrollPane scrollMypane;
    private final JScrollPane scrollPane;
    
    public JTextPane2() {
        super("JTextPane Test");
        Container container = getContentPane();
        container.setLayout(null);
        pane = new JTextPane();
        
        pane.setEditable(false);
        scrollPane = new JScrollPane(pane);
        scrollPane.setBounds(new Rectangle(0,10,400,200));
        JButton insertButton = new JButton("插入圖片");
        insertButton.setBounds(new Rectangle(0,220,100,40));
        
        insertButton.addActionListener((ActionEvent e) -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.showOpenDialog(JTextPane2.this);
            mypane.insertIcon(new ImageIcon(chooser.getSelectedFile().toString()));
        });
        
        mypane = new JTextPane();
        
        scrollMypane = new JScrollPane(mypane);
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
            StyledDocument doc = mypane.getStyledDocument();
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
            
            
            System.err.println("發送 JTextPane 的內容是 :/n"+mypane.getText().replace("\r\n", "\\r\\n"));
            int k = 0;
            String tmp=rmMicroSoftWindowsEnter(mypane.getText());
            for(int i = 0; i < mypane.getStyledDocument().getLength(); i++){
                if(mypane.getStyledDocument().getCharacterElement(i).getName().equals("icon")){
                    System.out.println("你在第 " + i + " 位置插入了圖片，圖片的路徑是 :");
                    pane.insertIcon(new ImageIcon(picVector.get(k).toString()));
                    System.err.println(picVector.get(k).toString());
                    k++;
                }else{
                    try {
                        System.out.println("localength=="+pane.getDocument().getLength()+"; StyledDoclength=="+pane.getStyledDocument().getLength());
                        String s = mypane.getStyledDocument().getText(i,1);
                        String s1 = mypane.getStyledDocument().getText(i,1);
                        if(s.equals("\r")){
                            s="\\r";
                        }else if(s.equals("\n")){
                            s="\\n";
                        }else if(s.equals("\r\n")){
                            s="\\r\\n";
                        }
                        System.out.print("; str=="+s+"; i=="+i+"; ");
                        pane.getStyledDocument().insertString(pane.getDocument().getLength(), s1, null);
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
        new JTextPane2().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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