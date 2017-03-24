/*
 * http://blog.csdn.net/hanghangde/article/details/50614318
 */
package org.lzl.consoletool;

import java.io.IOException;
import java.io.InputStream;

public class ConsoleSimulator implements Runnable {
    private final Outputers out;
    private static final int INFO = 0;
    private static final int ERROR = 1;
    private final InputStream is;
    private final int type;
    private final String decode;

    /**
     * Creates a new instance of StreamInterceptor
     * @param is
     * @param type
     * @param out
     * @param decode
     */
    public ConsoleSimulator(InputStream is, int type, Outputers out, String decode) {
        this.is = is;
        this.type = type;
        this.out = out;
        this.decode = decode;
    }

    
    @Override
    public void run() {
        byte[] bb;
        try {
            byte[] b = null;
            byte[] bytes = new byte[1024];
            StringBuilder buffer = new StringBuilder();
            for(int n ; (n = is.read(bytes))!=-1 ; ){
                buffer.append(new String(bytes,0,n, decode));
            }
            String[] sline = buffer.toString().split("\n");
            for(String s:sline){
                if (type == INFO) {
                    out.print("info > " + s);
                }else {
                    out.print("error> " + s);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}