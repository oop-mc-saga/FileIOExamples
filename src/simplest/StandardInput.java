package simplest;

import java.io.IOException;

/**
 *
 * @author tadaki
 */
public class StandardInput {

    public static void standardInput() {
        StringBuilder b = new StringBuilder();
        int c;
        try {
            while ((c = System.in.read()) != -1) {
                b.append((char) c);
                //read 1byte data and append b
            }
        } catch (IOException ex) {
            System.err.println("err");
        }
        System.out.println(b.toString());
    }
    
    public static void main(String args[]){
        standardInput();
    }
}
