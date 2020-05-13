package URL;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author tadaki
 */
public class URLMain {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        String urlString = "http://aoba.cc.saga-u.ac.jp/";
        ReadURL readURL = new ReadURL(urlString);
        System.out.println(readURL.showHeaderFields());
        System.out.println("title: "+readURL.getTitle());
        readURL.readHeaders();
        List<HTMLHeader> headerList = readURL.getHeaderList();
        headerList.stream().forEachOrdered(h->System.out.println(h));
    }
    
}
