package fileCopy;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Copy a text file line by line
 *
 * @author tadaki
 */
public class FileCopy {

    private final BufferedReader in;//Reader
    private final BufferedWriter out;//Writer

    /**
     * Specify source file
     *
     * @param inFileName
     * @param outFileName
     * @throws IOException
     */
    public FileCopy(String inFileName, String outFileName)
            throws IOException {
        in = openReader(inFileName);
        out = openWriter(outFileName);
    }

    /**
     * Open reader by specifying input file
     *
     * Open standard input if input file is not specified
     *
     * @param inputFileName
     * @return
     * @throws FileNotFoundException
     */
    static public BufferedReader openReader(String inputFileName)
            throws FileNotFoundException {
        //Open reader for standard input as default
        BufferedReader reader
                = new BufferedReader(new InputStreamReader(System.in));
        //If input file name is specified
        if (inputFileName != null && !inputFileName.isEmpty()) {
            File inFile = new File(inputFileName);
            //If file can not be accessed, throw exception
            if (!inFile.isFile() || !inFile.canRead()) {
                String message = "File "
                        + inFile.getAbsolutePath() + " not found";
                throw new FileNotFoundException(message);
            }
            reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(inFile)));
        }
        return reader;
    }

    /**
     * Open writer by specifying output file
     *
     * Open standard output if input file is not specified
     *
     * @param outFileName
     * @return
     * @throws IOException
     */
    static public BufferedWriter openWriter(String outFileName)
            throws IOException {
        //Open writer for standard output as default
        BufferedWriter writer
                = new BufferedWriter(new OutputStreamWriter(System.out));
        //If output file name is specified
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
            writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(outFile)));
        }
        return writer;
    }

    /**
     * Copy contents of file
     *
     * @return The number of lines copied
     * @throws IOException
     */
    public int copyData() throws IOException {
        int n = 0;
        String line;
        //Copy line by line
        while ((line = in.readLine()) != null) {
            n++;
            out.write(line);
            out.newLine();
        }
        in.close();
        out.close();
        return n;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            FileCopy main = new FileCopy("input.txt", "output.txt");
            int n = main.copyData();
            System.err.println("copy " + n + "lines");
        } catch (IOException ex) {
            Logger.getLogger(
                    FileCopy.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
}
