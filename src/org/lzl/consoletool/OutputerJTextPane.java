/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lzl.consoletool;

import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author liangzl2
 */
public class OutputerJTextPane implements Outputers{
    private static final String ENTER=System.getProperty("line.separator");
    private final JTextPane jtp;
    private final StringBuffer sb = new StringBuffer();
    public OutputerJTextPane(JTextPane jtp){
        this.jtp = jtp;
    }

    @Override
    public void println(String s) {
        sb.append(s).append(ENTER);
        SwingUtilities.invokeLater(() -> {
            jtp.setText(sb.toString());
        });
    }

    @Override
    public void print(String s) {
        sb.append(s);
        SwingUtilities.invokeLater(() -> {
            jtp.setText(sb.toString());
        });
    }
    
}
