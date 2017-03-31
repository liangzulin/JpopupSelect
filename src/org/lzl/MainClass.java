


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lzl;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.lzl.algorithm.MultiThreadFIB;
import org.lzl.windows.ExecutorForm;
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
        
        
        
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {e.printStackTrace();}
            ExecutorForm e = new ExecutorForm();
            IDEFrame form1=new IDEFrame();
            EditorDemo ed=new EditorDemo();
            form1.setVisible(true);
            ed.setVisible(true);
//            e.setVisible(true);
        });
    }
    
}
