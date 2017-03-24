


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lzl;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.lzl.windows.ExecutorForm;

/**
 *
 * @author liangzl2
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
//        IDEFrame form1=new IDEFrame();
//        form1.setVisible(true);
        
        SwingUtilities.invokeLater(() -> {
            try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {e.printStackTrace();}
            ExecutorForm e = new ExecutorForm();
            e.setVisible(true);
        });
    }
    
}
