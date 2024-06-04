package serialiize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

/**
 *
 * @author tadaki
 */
public class SerializeExample {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String filename = "record.ser";
        File file = new File(filename);
        Data data = null;
        if (file.exists()) {
            try (ObjectInputStream input
                    = new ObjectInputStream(new FileInputStream(file))) {
                data = (Data) input.readObject();
                System.out.println("read data");
            }
        } else {
            Integer[] record = {4, 2, 6, 4};
            // TODO code application logic here
            data = new Data("example1", Arrays.asList(record));
            try (ObjectOutputStream output
                    = new ObjectOutputStream(new FileOutputStream(file))) {
                output.writeObject(data);
                System.out.println("write data");
            }
        }
        System.out.println(data);
    }

}
