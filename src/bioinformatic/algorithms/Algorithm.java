package bioinformatic.algorithms;

public abstract class Algorithm {

    protected Sequence sequence1;
    protected Sequence sequence2;

    protected String alignmentA;
    protected String alignmentB;
    protected int i;
    protected int j;

    protected int score;
    protected SimilarityMatrix similarityMatrix;
    protected int[][] fmatrix;

    public Algorithm(Sequence sequence1, Sequence sequence2, Fmatrix fmatrix, SimilarityMatrix similarityMatrix) {
        alignmentA = "";
        alignmentB = "";
        this.sequence1 = sequence1;
        this.sequence2 = sequence2;
        i = sequence1.values.size();
        j = sequence2.values.size();
        this.fmatrix = fmatrix.getMatrix();
        this.similarityMatrix = similarityMatrix;
        score = 0;
    }

    public abstract void compute();

    public void calculateScore() {
        for (int i = 0; i < alignmentA.length(); i++)
        {
            char A = alignmentA.charAt(i);
            char B = alignmentB.charAt(i);

            if (A == B)
            {
                score += similarityMatrix.getMatrix().get(A).get(B);
            }
        }
    }

    public String getAlignmentA() {
        return alignmentA;
    }

    public String getAlignmentB() {
        return alignmentB;
    }

    public int getScore() {
        return score;
    }
}
