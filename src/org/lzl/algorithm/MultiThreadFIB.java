/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lzl.algorithm;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author LIANG
 */
public class MultiThreadFIB {
    static ExecutorService pool;
    static{
        pool = Executors.newFixedThreadPool(100000); 
    }
    
    public static long FIB(long n){
        if(n<=1){
            return n;
        }else{
            long x = FIB(n-1);
            long y = FIB(n-2);
            return x+y;
        }
    }
    
    public static long FIBtail(long n){
        if(n<=1){
            return n;
        }else{
//            long x = FIB(n-1);
//            long y = FIB(n-2);
            return FIBtail(n-1)+FIBtail(n-2);
        }
    }
    
    public static long FIBtail2(long n, long ret1, long ret2) {
        if (n == 0) {
            return ret1;
        } else {
            return FIBtail2(n - 1, ret2, ret1 + ret2);
        }
    }

    
    
    public static long FIBmulti(long n) throws Exception{
//        if(n<=1){
//            return n;
//        }else{
//            long x = FIB(n-1);
//            long y = FIB(n-2);
//            return x+y;
//        }
        FIBMultiClass2 fib=new FIBMultiClass2(n);
        return (long) fib.call();
    }
    
    static class FIBMultiClass implements Callable {
        private static final long serialVersionUID = 1L;

        private final long n;
        FIBMultiClass(long oid) {
            this.n = oid;
        }
        @Override
        public Object call() throws Exception {
            if (n <= 1) {
                return n;
            } else {
                //long x = FIB(n - 1);//
                Callable xC = new FIBMultiClass(n-1);
                Callable xC2 = new FIBMultiClass(n - 2);
                Future xf = pool.submit(xC);
                Future xf2 = pool.submit(xC2);
                long x;
                long y = (long) xf2.get();
                x=(long)xf.get();
                return x + y;
            }
        }
    }
    
    
    static class FIBMultiClass2 implements Callable {
        private static final long serialVersionUID = 1L;

        private final long n;
        FIBMultiClass2(long oid) {
            this.n = oid;
        }
        @Override
        public Object call() throws Exception {
            if (n <= 1) {
                return n;
            } else {
                //long x = FIB(n - 1);//
                Callable xC = new FIBMultiClass(n-1);
                Future xf = pool.submit(xC);
                long x;
                long y = FIBtail2(n - 2,0,1);
                x=(long)xf.get();
                return x + y;
            }
        }
    }
}


