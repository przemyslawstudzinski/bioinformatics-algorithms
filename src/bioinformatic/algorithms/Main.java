package bioinformatic.algorithms;

public class Main {

    public static void main(String[] args) {
        FileReader seq1 = new FileReader("Sequence1");
        seq1.readFile();
        System.out.println(seq1.getContent());
        Sequence sequence1 = new Sequence(seq1.getContent());

        FileReader seq2 = new FileReader("Sequence2");
        seq2.readFile();
        System.out.println(seq2.getContent());
        Sequence sequence2 = new Sequence(seq1.getContent());

        FileReader seq3 = new FileReader("similarity_matrix");
        seq3.readFile();
        System.out.println(seq3.getContent());
        SimilarityMatrix similarityMatrix = new SimilarityMatrix();
        similarityMatrix.createMatrix(seq3.getContent());
    }
}
