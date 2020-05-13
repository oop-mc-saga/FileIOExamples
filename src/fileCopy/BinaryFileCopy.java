package fileCopy;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Fileの内容を一行単位で複写する
 *
 * @author tadaki
 */
public class BinaryFileCopy {

    private final FileInputStream in;
    private final FileOutputStream out;//Writer

    /**
     * 入出力ファイルを指定
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
     * ファイル名を指定してInputStreamを開く
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
            //指定されたファイルが開けない場合に例外を投げる
            if (!inFile.isFile() || !inFile.canRead()) {
                String message = "入力ファイル"
                        + inFile.getAbsolutePath() + "がありません。";
                throw new FileNotFoundException(message);
            }
            in = new FileInputStream(inFile);
        }
        return in;
    }

    /**
     * ファイル名を指定してOutputStreamを開く
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
            if (!outFile.exists()) {//ファイルが存在しない場合には、新規に作成
                outFile.createNewFile();
            }
            if (!outFile.canWrite()) {//ファイルに書けない場合
                String message = "出力ファイル"
                        + outFile.getAbsolutePath() + "には書けません。";
                throw new IOException(message);
            }
            out = new FileOutputStream(outFile);
        }
        return out;
    }

    /**
     * ファイルの内容をコピーする
     *
     * @return コピーしたバイト数を返す
     * @throws IOException
     */
    public int copyData() throws IOException {
        int n = 0;
        int b;
        //一バイト毎にコピー
        while ((b = in.read()) != -1) {
            n++;
            out.write(b);
        }
        in.close();
        out.close();
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
