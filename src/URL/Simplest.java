package URL;

import java.io.*;
import java.net.Socket;

/**
 *
 * @author tadaki
 */
public class Simplest {

    /**
     * Socketを開き、サーバからの応答を得る
     *
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Socket server = new Socket("aoba.cc.saga-u.ac.jp", 80);
        //サーバからの応答を得るReaderを開く
        //サーバへの送信を送るWriterを開く
        try (BufferedReader in
                = new BufferedReader(
                        new InputStreamReader(server.getInputStream()));
                PrintWriter out
                = new PrintWriter(server.getOutputStream(), true)) {

            out.println("GET /");//サーバへのコマンド送信

            String line;
            //サーバからの応答を一行毎に印刷
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
