package bioinformatic.algorithms;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sequence {

    List<Character> values;

    public Sequence(String content) {
        this.values = new ArrayList<>();
        values = content
                .chars()
                .mapToObj(x -> (char) x)
                .collect(Collectors.toList());
    }

}
