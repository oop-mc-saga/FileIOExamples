package fileCopy;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Copy a binary file
 *
 * @author tadaki
 */
public class BinaryFileCopy {

    private final FileInputStream in;
    private final FileOutputStream out;//Writer

    /**
     * Specify source file
     *
     * @param inFileName
     * @param outFileName
     * @throws IOException
     */
    public BinaryFileCopy(String inFileName, String outFileName)
            throws IOException {
        in = openInputStream(inFileName);
        out = openOutputStream(outFileName);
    }

    /**
     * Open inputStream by specifying input file
     *
     * @param inputFileName
     * @return
     * @throws FileNotFoundException
     */
    static public FileInputStream openInputStream(String inputFileName)
            throws FileNotFoundException {
        FileInputStream in = null;
        if (inputFileName != null && !inputFileName.isEmpty()) {
            File inFile = new File(inputFileName);
            //If file can not be accessed, throw exception
            if (!inFile.isFile() || !inFile.canRead()) {
                String message = "File "
                        + inFile.getAbsolutePath() + " not found";
                throw new FileNotFoundException(message);
            }
            in = new FileInputStream(inFile);
        }
        return in;
    }

    /**
     * Open outputStream by specifying output file
     *
     * @param outFileName
     * @return
     * @throws IOException
     */
    static public FileOutputStream openOutputStream(String outFileName)
            throws IOException {
        FileOutputStream out = null;
        if (outFileName != null && !outFileName.isEmpty()) {
            File outFile = new File(outFileName);
            if (!outFile.exists()) {//If file does not exist, create new one
                outFile.createNewFile();
            }
            if (!outFile.canWrite()) {//If file is not writable
                String message = "File "
                        + outFile.getAbsolutePath() + " is not writable";
                throw new IOException(message);
            }
            out = new FileOutputStream(outFile);
        }
        return out;
    }

    /**
     * Copy contents of file
     *
     * @return THe number of bytes copied
     * @throws IOException
     */
    public int copyData() throws IOException {
        int n = 0;

        
        
        
        
        
        
        
        return n;
    }

    /**
     * @param args the command b arguments
     */
    public static void main(String[] args) {
        try {
            BinaryFileCopy main = new BinaryFileCopy("dist/FileIOSamples.jar", "output.jar");
            int n = main.copyData();
            System.err.println("copy " + n + "bytes");
        } catch (IOException ex) {
            Logger.getLogger(BinaryFileCopy.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
}
