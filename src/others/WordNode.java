/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package others;

/**
 *
 * @author liangzl2
 */
class WordNode {  
     private final int location;
     private final String word;
     public WordNode(int location, String str) {
         super();
         this.location = location;
         this.word = str;
     }  
     public int getLocation() {
         return location;
     }  
     public String getWord() {
         return word;
     }  
}