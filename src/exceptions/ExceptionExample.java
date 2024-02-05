package exceptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author tadaki
 */
public class ExceptionExample {

    public static double str2Double(String str)
            throws NumberFormatException {
        double d = Double.valueOf(str);
        return d;
    }

    public static BufferedReader openReader(String filename) 
            throws FileNotFoundException{
        File file = new File(filename);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(new FileInputStream(file)));
        return in;
    }
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader in = openReader("sampleInput.txt");
        String line;
        while ((line = in.readLine()) != null) {
            try {
                double d = str2Double(line);
                System.out.println(line + "->" + d);
            } catch (NumberFormatException e) {
                System.out.println(line);
            }
        }
    }

}
