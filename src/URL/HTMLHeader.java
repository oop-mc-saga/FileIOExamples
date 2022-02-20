package URL;

/**
 * Class for keeping header tags
 *
 * @author tadaki
 */
public class HTMLHeader {

    private final int level;
    private String title;

    public HTMLHeader(int level) {
        this.level = level;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<H").append(level).append(">");
        sb.append(title);
        sb.append("</H").append(level).append(">");
        return sb.toString();
    }
}
