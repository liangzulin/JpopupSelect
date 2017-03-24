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
public class ParseS {
    public static void main2() {
        System.out.println("hello world");
    }

    
    public static Tree parseContent(String text, int i, Tree t){
        //直接从括号后的下一个开始记录
        i++;
        StringBuilder sb=new StringBuilder();//可能的性能问题 先mark *****
        char c = text.charAt(i);
        if(c==' '){
            System.err.println("None function name");
            return null;
        }else if(c==')'){
            System.err.println("None function name");
            return null;
        }
        while(true){
            sb.append(c);
            if(c==' '){
                break;
            }else if(c==')'){
                break;
            }
            i++;
            c = text.charAt(i);
        }
        //收集到第一个函数名
        t.setO(sb.toString());
        
        i++;
        if(text.charAt(i)=='('){
            //如果又遇到新函数，则：
            Tree t1=parseContent(text, i, new Tree(null,null,null));
            t.setLeft(t1);
        }else{
            //没遇到新函数，遇到的是参数，则：
            StringBuilder sb1=new StringBuilder();//可能的性能问题 先mark *****
            char c1 = text.charAt(i);
            while (true) {
                sb1.append(c1);
                if (c1 == ' ') {
                    break;
                } else if (c1 == ')') {
                    break;
                }
                i++;
                c1 = text.charAt(i);
            }
            t.setLeft(new Tree(sb1.toString(),null,null));
        }
        
        i++;
        if(text.charAt(i)=='('){
            //如果又遇到新函数，则：
            Tree t1=parseContent(text, i, new Tree(null,null,null));
            t.setRight(t1);
        }else{
            //没遇到新函数，遇到的是参数，则：
            StringBuilder sb1=new StringBuilder();//可能的性能问题 先mark *****
            char c1 = text.charAt(i);
            while (true) {
                sb1.append(c1);
                if (c1 == ' ') {
                    break;
                } else if (c1 == ')') {
                    break;
                }
                i++;
                c1 = text.charAt(i);
            }
            t.setRight(new Tree(sb1.toString(),null,null));
        }
        
        return t;
    }
    
    public static void printTree(Tree t, int level){
        if(t.getFunc()!=null){
            //获取并打印第一个func
            String func=t.getFunc();
            System.out.println("{"+func);
            
            //确立进阶
            StringBuilder sb=new StringBuilder();
            for(int i=0;i<=level;i++){
                sb.append("给");
            }
            
            
            System.out.print(sb.toString());//新的一行打印空格，打印left
            if(t.getLeft()!=null){
                printTree(t.getLeft(),level+1);
            }else{
                System.out.print("[null]");
            }
            
            System.out.print(sb.toString());//新的一行打印空格，打印right
            if(t.getRight()!=null){
                printTree(t.getRight(),level+1);
            }else{
                System.out.print("[null]");
            }
            System.out.println("}");
        }
    }
    
    
    public static int ii=0;
    
    /**
     *
     * @param text 原始文本
     * @return
     */
    public static Tree parse2(String text){
        System.out.println(text.charAt(ii)+"----------"+ii);
        //从得到的i位置，i之后是函数体
        Tree t = new Tree(null,null,null);
        String func;
        ii++;//于是从i之后开始读
        int current = ii;
        while(text.charAt(ii)!=' '){
            System.out.print(text.charAt(ii));
            //当读到的不是空格的时候，继续使劲读
            ii++;
        }
        //读到空格了，那么：
        func=text.substring(current, ii);
        t.setO(new String(func));
        
        //读左参数
        ii++;//读取空格的下一个
        if(text.charAt(ii)=='('){
            System.out.print(text.charAt(ii)+"[Tree]");
            //读到一个新函数
            t.setLeft(parse2(text));
        }else{
            //读到一个参数，整个做成字符串
            current = ii;
            while (text.charAt(ii) != ' ') {
                System.out.print(text.charAt(ii));
                //当读到的不是空格的时候，继续使劲读
                ii++;
            }
            func=text.substring(current, ii);
            t.setLeft(new Tree(new String(func),null,null));
        }
        
        //读右参数
        ii++;//读取空格的下一个
        if(text.charAt(ii)=='('){
            System.out.print(text.charAt(ii)+"[Tree]");
            //读到一个新函数
            t.setRight(parse2(text));
        }else{
            //读到一个参数，整个做成字符串
            current = ii;
            while (text.charAt(ii) != ')') {
                System.out.print(text.charAt(ii));
                //当读到的不是空格的时候，继续使劲读
                ii++;
            }
            func=text.substring(current, ii);
            t.setRight(new Tree(new String(func),null,null));
            ii++;
        }
        
        
        return t;
    }
    
    public static String readNextWord(String text, int i){
        int current = i+1;
        while (text.charAt(i) != ' ') {
                //当读到的不是空格的时候，继续使劲读
            i++;
        }
        return text.substring(current, i-current);
    }
}
