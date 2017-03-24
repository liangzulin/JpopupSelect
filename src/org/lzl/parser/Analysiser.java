/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lzl.parser;

/**
 *
 * @author liangzl2
 */
public class Analysiser {
    public static void run(Tree t){
        String func=t.getFunc();
        String left=t.getLeft().getFunc();
        String right=t.getRight().getFunc();
        
    }
}
