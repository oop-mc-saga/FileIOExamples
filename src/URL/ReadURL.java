package URL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author tadaki
 */
public class ReadURL {

    private final List<HTMLHeader> headerList;
    private final HttpURLConnection urlConnection;
    private final Map<String, List<String>> headerFields;
    public static final String nl = System.getProperty("line.separator");
    private String htmlContent = null;

    /**
     * contructor
     *
     * @param urlString String expressing URL
     * @throws java.io.IOException
     */
    public ReadURL(String urlString) throws IOException {
        URL url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestProperty("Accept-Language", "ja");
        headerFields = urlConnection.getHeaderFields();
        headerList = Collections.synchronizedList(new ArrayList<>());
    }

    /**
     * Get pairs of keys and values in header part
     *
     * @return
     */
    public String showHeaderFields() {
        StringBuilder sb = new StringBuilder();
        headerFields.keySet().forEach(
                s -> {
                    sb.append(s).append("->");
                    sb.append(headerFields.get(s)).append(nl);
                });
        return sb.toString();
    }

    /**
     * read contents for text/html cases
     *
     * @return
     * @throws IOException
     */
    public String readPage() throws IOException {
        if (!isHTML()) {
            System.err.println("not a html file");
            return null;
        }
        int code = urlConnection.getResponseCode();
        if (code != 200) {
            System.err.println("Incorrect URL:code=" + code);
            return null;
        }

        StringBuilder sb;
        //Read contents as a string
        try ( //open url as stream
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(urlConnection.getInputStream()))) {
            sb = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line).append(nl);
            }
        }
        return sb.toString();
    }

    /**
     * check content-type is text/html
     *
     * @return
     * @throws IOException
     */
    public boolean isHTML() throws IOException {
        Pattern pattern = Pattern.compile("html", Pattern.CASE_INSENSITIVE);
        Matcher m = pattern.matcher(urlConnection.getContentType());
        return m.find();
    }

    /**
     * get page title
     *
     * @return
     * @throws IOException
     */
    public String getTitle() throws IOException {
        if (htmlContent == null) {
            htmlContent = readPage();
        }
        if (htmlContent == null) {
            return null;
        }
        String tp = "<title>(.+)</title>";
        //Make compatible for multilines and case insensitive
        Pattern pattern = Pattern.compile(tp,
                Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
        Matcher m = pattern.matcher(htmlContent);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }

    /**
     * read header part int list
     *
     * @return the number of header
     * @throws IOException
     */
    public int readHeaders() throws IOException {
        if (htmlContent == null) {
            htmlContent = readPage();
        }
        if (htmlContent == null) {
            return 0;
        }
        int n = 0;
        //Make compatible for multilines
        Pattern pattern = Pattern.compile("<h(\\d+)>(.+)</h\\1>",
                Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
        Matcher m = pattern.matcher(htmlContent);
        while (m.find()) {
            int level = Integer.valueOf(m.group(1));
            String title = m.group(2);
            HTMLHeader header = new HTMLHeader(level);
            header.setTitle(title);
            headerList.add(header);
            n++;
        }
        return n;
    }

    public List<HTMLHeader> getHeaderList() {
        return headerList;
    }

}
