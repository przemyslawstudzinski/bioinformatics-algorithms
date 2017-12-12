package bioinformatic.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sequence {

    private String content;
    private List<Character> values;

    public Sequence(String content) {
       this.content = content;
        this.values = new ArrayList<>();
        values = content
                .chars()
                .mapToObj(x -> (char) x)
                .collect(Collectors.toList());
    }

    public String getContent() {
        return content;
    }

    public List<Character> getValues() {
        return values;
    }
}
