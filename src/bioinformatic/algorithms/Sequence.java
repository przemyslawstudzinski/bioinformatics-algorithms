package bioinformatic.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sequence {

    private String content;
    List<Character> values;

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

    public void setContent(String content) {
        this.content = content;
    }

    public List<Character> getValues() {
        return values;
    }

    public void setValues(List<Character> values) {
        this.values = values;
    }
}
