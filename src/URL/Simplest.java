package URL;

import java.io.*;
import java.net.Socket;

/**
 *
 * @author tadaki
 */
public class Simplest {

    /**
     * Open socket and receive responses from sever
     *
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Socket server = new Socket("aoba.cc.saga-u.ac.jp", 80);
        //Open Reader for receiving response from server
        //Open Writer for sending message to server
        try (BufferedReader in
                = new BufferedReader(
                        new InputStreamReader(server.getInputStream()));
                PrintWriter out
                = new PrintWriter(server.getOutputStream(), true)) {

            out.println("GET /");//Send message to server

            String line;
            //Print responses from server
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
