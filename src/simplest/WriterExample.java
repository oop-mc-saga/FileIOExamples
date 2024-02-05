package simplest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 *
 * @author tadaki
 */
public class WriterExample {

    public static void wrapping() {
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(System.out));
        try {
            out.write("Something");
            out.newLine();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws IOException {
        File file = new File("WriterSampleOutput.txt");
        try ( BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(file)))) {
            for (int i = 0; i < 100; i++) {
                int x = (int) (100 * Math.random());
                out.write(String.valueOf(x));
                out.newLine();
            }
        }
    }

}
