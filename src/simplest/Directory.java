package simplest;

import java.io.File;

/**
 *
 * @author tadaki
 */
public class Directory {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File file = new File(".");
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getPath());
        System.out.println(file.isDirectory());
        System.out.println(File.separator);
    }
    
}
