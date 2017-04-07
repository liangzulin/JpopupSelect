/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lzl.consoletool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 *
 * @author liangzl2
 */
public class CMDrun {
    private final Outputers out;
    private final int INFO = 0;
    private final int ERROR = 1;
    private final String decode;
    
    /**
     * Create a CMD runner, input the desire Outputers for output (System.out.print
     * or JTextPanel, anyway you want), and point out which decode you want.for 
     * example, the gb2312 for Chinese if you run CMD in Chinese Windows system
     * @param out the Outputers, you should implement all abstract method
     * @param decode the Text Encoding you want.
     */
    public CMDrun(Outputers out, String decode){
        this.out = out;
        this.decode = decode;
    }
    
    /**
     * Create a CMD runner, input the desire Outputers for output (System.out.print
     * or JTextPanel, anyway you want).This method use gb2312 for decoder.
     * @param out the Outputers, you should implement all abstract method
     */
    public CMDrun(Outputers out){
        this.out = out;
        this.decode = "gb2312";
    }
    
    /**
     * Run the terminal command like bash, shell or CMD.This method can run
     * the program which is executable.The 
     * @param command some command like "ls", "dir", ""
     * @throws IOException
     * @throws InterruptedException
     */
    public void startEXE(String command) throws IOException, InterruptedException {
        // Process child = Runtime.getRuntime().exec("run.bat"); 
        // Process child = Runtime.getRuntime().exec(" java -classpath bin helloworld.Test ");
        
        int result;
//        String command = "javac";
        Process child;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
//        while(true){
//            out.print("INPUT>");
//            str=br.readLine();
//            if(str.equals("exit")){
//                break;
//            }
//            command = str;
            try{
                child = Runtime.getRuntime().exec(command);
            }catch(java.lang.IllegalArgumentException e){
                return;
            }catch(java.io.IOException e){
                out.println(e.getMessage());
                return;
                //child = Runtime.getRuntime().exec("cmd.exe dir");
            }
            OutputStream os = child.getOutputStream();
            InputStream stdin = child.getInputStream();
            InputStream stderr = child.getErrorStream();
            
            
            Thread tIn = new Thread(new ConsoleSimulator(stdin, INFO, out, this.decode));
            Thread tErr = new Thread(new ConsoleSimulator(stderr, ERROR, out, this.decode));
            tIn.start();
            tErr.start();
            result = child.waitFor();
            tIn.join();
            tErr.join();
//        }
        
        if (result == 0) {
            out.println("SUCCESS! Return "+result);
        } else {
            out.println("FAILED! Return "+result);
        }
    }
}
