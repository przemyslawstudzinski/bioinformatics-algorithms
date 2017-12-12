package bioinformatic.algorithms;

public class Main {

    public static final int gapPenalty = -1;

    public static void main(String[] args) {
        FileReader seq1 = new FileReader("Sequence1.txt");
        seq1.readFile();
        System.out.println("Sequence 1:");
        System.out.println(seq1.getContent());
        System.out.println();
        Sequence sequence1 = new Sequence(seq1.getContent());

        FileReader seq2 = new FileReader("Sequence2.txt");
        seq2.readFile();
        System.out.println("Sequence 2:");
        System.out.println(seq2.getContent());
        System.out.println();
        Sequence sequence2 = new Sequence(seq2.getContent());

        FileReader seq3 = new FileReader("similarity_matrix.txt");
        seq3.readFile();
        System.out.println("Similarity Matrix");
        System.out.println(seq3.getContent());
        System.out.println();
        SimilarityMatrix similarityMatrix = new SimilarityMatrix();
        similarityMatrix.createMatrix(seq3.getContent());

        Fmatrix fmatrix1 = new Fmatrix(sequence1, sequence2, similarityMatrix, gapPenalty);
        fmatrix1.compute();
        System.out.println("F Matrix with gap= " + gapPenalty);
        System.out.println(fmatrix1.toString());
        System.out.println();

        System.out.println("NEEDLEMAN-WUNSCH:");
        Algorithm needlemanWunsch = new Algorithm(sequence1, sequence2, fmatrix1, similarityMatrix);
        needlemanWunsch.computeNeedlemanWunsch();
        needlemanWunsch.calculateScore();
        System.out.println(needlemanWunsch.getAlignmentA());
        System.out.println(needlemanWunsch.getAlignmentB());
        System.out.println("Score: " + needlemanWunsch.getScore());
        System.out.println();


        Fmatrix fmatrix2 = new Fmatrix(sequence1, sequence2, similarityMatrix, 0);
        fmatrix2.compute();
        System.out.println("F Matrix with no gap");
        System.out.println(fmatrix2.toString());
        System.out.println();

        System.out.println("HIRSCHBERG:");
        Algorithm hirschberg = new Algorithm(sequence1, sequence2, fmatrix2, similarityMatrix);
        hirschberg.computeHirschberg(null, null);
        hirschberg.calculateScore();

        System.out.println(hirschberg.getAlignmentA());
        System.out.println(hirschberg.getAlignmentB());
        System.out.println("Score: " + hirschberg.getScore());
        System.out.println();
    }
}
