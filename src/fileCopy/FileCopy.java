package fileCopy;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Fileの内容を一行単位で複写する
 *
 * @author tadaki
 */
public class FileCopy {

    private final BufferedReader in;//Reader
    private final BufferedWriter out;//Writer

    /**
     * 入出力ファイルを指定
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
     * ファイル名を指定してReaderを開く
     *
     * ファイル名を指定しないと標準入力を開く
     *
     * @param inputFileName
     * @return
     * @throws FileNotFoundException
     */
    static public BufferedReader openReader(String inputFileName)
            throws FileNotFoundException {
        //標準入力をデフォルトとして指定
        BufferedReader reader
                = new BufferedReader(new InputStreamReader(System.in));
        //ファイル名を指定されている場合
        if (inputFileName != null && !inputFileName.isEmpty()) {
            File inFile = new File(inputFileName);
            //指定されたファイルが開けない場合に例外を投げる
            if (!inFile.isFile() || !inFile.canRead()) {
                String message = "入力ファイル"
                        + inFile.getAbsolutePath() + "がありません。";
                throw new FileNotFoundException(message);
            }
            reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(inFile)));
        }
        return reader;
    }

    /**
     * ファイル名を指定してWriterを開く
     *
     * ファイル名を指定しないと標準出力を開く
     *
     * @param outFileName
     * @return
     * @throws IOException
     */
    static public BufferedWriter openWriter(String outFileName)
            throws IOException {
        //標準出力をデフォルトとして開く
        BufferedWriter writer
                = new BufferedWriter(new OutputStreamWriter(System.out));
        //ファイル名を指定されている場合
        if (outFileName != null && !outFileName.isEmpty()) {
            File outFile = new File(outFileName);
            if (!outFile.exists()) {//ファイルが存在しない場合には、新規に作成
                outFile.createNewFile();
            }
            if (!outFile.canWrite()) {//ファイルに書けない場合
                String message = "出力ファイル"
                        + outFile.getAbsolutePath() + "には書けません。";
                throw new IOException(message);
            }
            writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(outFile)));
        }
        return writer;
    }

    /**
     * ファイルの内容をコピーする
     *
     * @return コピーした行数を返す
     * @throws IOException
     */
    public int copyData() throws IOException {
        int n = 0;
        String line;
        //一行毎にコピー
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
