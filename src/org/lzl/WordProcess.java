/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lzl;

/**
 *
 * @author liangzl2
 */
public class WordProcess {
    private static final String ENTER=System.getProperty("line.separator");
    public static String linuxToSysEnter(String str){
        /**
         * asdfjo joawfiejoi jj oiwaejofi j
         * aweoijf oijawe iojoiawfejio jfioawej fewaoij oijwaf
         * awoiejf ioawjefoi joiawjefoij a
         * wfioj waoiejfioawejoifjlasdjfnawjkefajwfoi 
         * awef jawoiejf iowaejf ooija 
         */
        return str.replaceAll("\n", "<br>");
        /**
         * fajwieojfio aweoifjio 
         * awoiejfio awioefji oawjefoi jawioefj 
         * awoiefj oiawjefiojawoiefjiowejf oijoi jwfiao jiowej oiawjfoi 
         * awojf oiwajefio joi wjfaoij fioawjeof oij
         */
    }
}
