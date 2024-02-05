package simplest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 *
 * @author tadaki
 */
public class PrintStreamExample {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) 
            throws FileNotFoundException {
        File file = new File("PrintStreamSampleOutput.txt");
        try ( PrintStream out = new PrintStream(file)) {
            for (int i = 0; i < 100; i++) {
                int x = (int) (100 * Math.random());
                out.println(x);
            }
        }
    }

}
