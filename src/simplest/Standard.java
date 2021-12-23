package simplest;

import java.io.IOException;

/**
 *
 * @author tadaki
 */
public class Standard {

    public static void standardInput() {
        StringBuilder b = new StringBuilder();
        int c;
        try {
            while ((c = System.in.read()) != -1) {
                b.append((char) c);
                //1byte ずつ読み込みbに追加
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
