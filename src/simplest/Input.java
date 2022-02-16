package simplest;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Input Streamの例
 *
 * @author tadaki
 */
public class Input {

    static final String ENC = "UTF-8";//character code

    /**
     * Read characters from a file
     *
     * @param filename
     * @return
     * @throws IOException
     */
    static public String openInputStream(String filename)
            throws IOException {
        File file = new File(filename);//Specify file for reading
        StringBuilder sb = new StringBuilder();
        //Open input buffer
        try ( BufferedInputStream in
                = new BufferedInputStream(
                        new FileInputStream(file))) {
            int n;
            while ((n = in.read()) != -1) {//Read byte by byte
                char c = (char) n;//Convert byte to character
                sb.append(c);//append to string builder
            }
        }
        return sb.toString();
    }

    /**
     * Read lines from a file and return them as a string
     *
     * @param filename
     * @return
     * @throws IOException
     */
    static List<String> openReader(String filename)
            throws IOException {
        File file = new File(filename);
        List<String> stringList
                = Collections.synchronizedList(new ArrayList<>());
        try ( BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file), ENC))) {
            String line;
            //Read line by line
            while ((line = in.readLine()) != null) {
                stringList.add(line);
            }
        }
        return stringList;
    }

    public static void wrapping() {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String filename = "input.txt";
        String readString = openInputStream(filename);
        System.out.println(readString);
        System.out.println("--------------");
        List<String> lines = openReader(filename);
        lines.forEach(
                s -> System.out.println(s)
        );
    }
}
