package serialiize;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author tadaki
 */
public record Data(String name, List<Integer> result) implements Serializable{
    public Data { //constructor
        result = List.copyOf(result); //immutable copy
    }
}