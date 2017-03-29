/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lzl.algorithm;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author LIANG
 */
public class MultiThreadFIBTest {
    
    public MultiThreadFIBTest() {
    }
 

    /**
     * Test of FIB method, of class MultiThreadFIB.
     */
    @Test
    public void testFIB() {
        System.out.println("FIB");
        long n = 24;
        long expResult = 12586269025l;//when 50
        expResult = 46368l;//when 24
        long result = MultiThreadFIB.FIB(n);
        System.out.println(result);
        assertEquals(expResult, result);
    }

    @Test
    public void testFIB2() {
        System.out.println("FIB2_Multi");
        long n = 24;
        long expResult = 12586269025l;//when 50
        expResult = 46368l;//when 24
        long result = 0;
        try {
            result = MultiThreadFIB.FIBmulti(n);
        } catch (Exception ex) {
            Logger.getLogger(MultiThreadFIBTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(result);
        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
    
        @Test
    public void testFIB3() {
        System.out.println("FIB3_tail");
        long n = 24;
        long expResult = 12586269025l;//when 50
        expResult = 46368l;//when 24
        long result = MultiThreadFIB.FIBtail2(n,0,1);
        System.out.println(result);
        
        assertEquals(expResult, result);
    }
    
}
