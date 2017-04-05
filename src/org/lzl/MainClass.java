


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lzl;

import java.util.Arrays;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.lzl.windows.ExecutorForm;
import org.lzl.windows.ImageTextPaneForm;
import others.ComplexCellRenderer2;
import others.EditorDemo;

/**
 *
 * @author liangzl2
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        System.out.println("FIB2");
//        long n = 50;
//        long expResult = 12586269025l;
//        long result = 0;
//        try {
//            result = MultiThreadFIB.FIBmulti(n);
//        } catch (Exception ex) {
//            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.out.println(result);
        
        
//        double x=2.1;
//        double y=7.8;
        double[] x1={2.1, 3.4, 7.8, 10.3};
        double n=10.0;
        double range=16.0;
        for(n=-range;n<=range;n+=2.0){
            double tmp=0.0;
            for(double xn:x1){
                tmp+=Math.pow(xn,n);
            }
            double result=Math.pow(tmp, 1.0/n);
            System.out.print(Arrays.toString(x1));
            System.out.printf("; n=%.2f; result is %.8f\n",n,result);
        }
        
        
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {e.printStackTrace();}
            ExecutorForm e = new ExecutorForm();
            IDEFrame selfIDE=new IDEFrame();
//            EditorDemo ed=new EditorDemo();
//            selfIDE.setVisible(true);
//            ed.setVisible(true);
//            e.setVisible(true);
//            JTextPaneImage.main2();
            ImageTextPaneForm itpf = new ImageTextPaneForm();
            itpf.setVisible(true);
            ComplexCellRenderer2.main2();
        });
    }
    
}
