package bioinformatic.algorithms;

public class Main {

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

        Fmatrix fmatrix = new Fmatrix(sequence1, sequence2, similarityMatrix);
        fmatrix.compute();
        System.out.println("F Matrix");
        System.out.println(fmatrix.toString());
        System.out.println();

        System.out.println("GLOBAL ALIGNMENT - NEEDLEMAN-WUNSCH");
        NeedlemanWunsch needlemanWunsch = new NeedlemanWunsch(sequence1, sequence2, fmatrix, similarityMatrix);
        needlemanWunsch.compute();
        needlemanWunsch.calculateScore();

        System.out.println(needlemanWunsch.getAlignmentA());
        System.out.println(needlemanWunsch.getAlignmentB());
        System.out.println("Score: " + needlemanWunsch.getScore());
        System.out.println();

    }
}
