package bioinformatic.algorithms;

import java.util.HashMap;
import java.util.Map;

public class SimilarityMatrix {

    private static final Character[] DnaNucleotides = {'A', 'G', 'C', 'T'};

    private Map<Character, Map<Character, Integer>> matrix;
    private Map<Character, Integer> matrixA;
    private Map<Character, Integer> matrixG;
    private Map<Character, Integer> matrixC;
    private Map<Character, Integer> matrixT;

    public SimilarityMatrix() {
        matrix = new HashMap();
        matrixA = new HashMap();
        matrixG = new HashMap();
        matrixC = new HashMap();
        matrixT = new HashMap();
    }

    public void createMatrix(String content) {
        String[] lines = content.split(System.getProperty("line.separator"));
        int i = 0;
        for (Character DnaNucleotide : DnaNucleotides) {
            String[] values = lines[i].split("\\s+");
            int counter = 0;
            for (String value : values) {
                switch (counter) {
                    case 0:
                        matrixA.putIfAbsent(DnaNucleotide, Integer.valueOf(value));
                        break;
                    case 1:
                        matrixG.putIfAbsent(DnaNucleotide, Integer.valueOf(value));
                        break;
                    case 2:
                        matrixC.putIfAbsent(DnaNucleotide, Integer.valueOf(value));
                        break;
                    case 3:
                        matrixT.putIfAbsent(DnaNucleotide, Integer.valueOf(value));
                        break;
                }
                counter++;
            }
            i++;
        }

        matrix.putIfAbsent(DnaNucleotides[0], matrixA);
        matrix.putIfAbsent(DnaNucleotides[1], matrixG);
        matrix.putIfAbsent(DnaNucleotides[2], matrixC);
        matrix.putIfAbsent(DnaNucleotides[3], matrixT);
    }

}
