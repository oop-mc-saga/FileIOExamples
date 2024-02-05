package urls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 *
 * @author tadaki
 */
public class OpenURL {

    private final BufferedReader reader;

    public OpenURL(String urlName) throws IOException, URISyntaxException {
        URL url = new URI(urlName).toURL();
        reader = new BufferedReader(new InputStreamReader(url.openStream()));
    }

    public BufferedReader getReader() {
        return reader;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, URISyntaxException {
        OpenURL url = new OpenURL("http://traffic.cc.saga-u.ac.jp");
        BufferedReader in = url.getReader();
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
    }

}
