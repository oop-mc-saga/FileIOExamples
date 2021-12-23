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

    static final String ENC = "UTF-8";//文字コード

    /**
     * ファイルから文字を読み込み
     *
     * @param filename
     * @return
     * @throws IOException
     */
    static public String openInputStream(String filename)
            throws IOException {
        File file = new File(filename);//ファイル指定
        StringBuilder sb = new StringBuilder();
        //入力バッファを開く
        try ( BufferedInputStream in
                = new BufferedInputStream(
                        new FileInputStream(file))) {
            int n;
            while ((n = in.read()) != -1) {//１バイト毎に読み込み
                char c = (char) n;//コードを文字へ変換
                sb.append(c);//ビルダへ追加
            }
        }
        return sb.toString();
    }

    /**
     * ファイルから内容を読み出し、文字列として返す
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
            //一行毎に読み込み
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
