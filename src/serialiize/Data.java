package serialiize;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author tadaki
 */
public record Data(String name, List<Integer> result) implements Serializable{
    public Data {
        result = List.copyOf(result);
    }
}