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
public class Tree {
    private String o;
    private Tree left;
    private Tree right;

    public String getFunc() {
        return o;
    }

    public Tree getLeft() {
        return left;
    }

    public void setLeft(Tree left) {
        this.left = left;
    }

    public void setO(String o) {
        this.o = o;
    }

    public Tree getRight() {
        return right;
    }

    public void setRight(Tree right) {
        this.right = right;
    }
    public Tree(String o, Tree left, Tree right){
        this.o = o;
        this.left = left;
        this.right = right;
    }
    
}
