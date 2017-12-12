package bioinformatic.algorithms;

public class NeedlemanWunsch extends Algorithm {

    public NeedlemanWunsch(Sequence sequence1, Sequence sequence2, Fmatrix fmatrix, SimilarityMatrix similarityMatrix) {
        super(sequence1, sequence2, fmatrix, similarityMatrix);
    }

    @Override
    public void compute() {
        while (i > 0 && j > 0) {
            char seqA = sequence1.values.get(i - 1);
            char seqB = sequence2.values.get(j - 1);
            int similarityValue = similarityMatrix.getMatrix().get(seqA).get(seqB);

            int diag = fmatrix[i - 1][j - 1];
            int up = fmatrix[i - 1][j];
            int left = fmatrix[i][j - 1];

            if (diag + similarityValue >= up && diag + similarityValue >= left ) {
                alignmentA = seqA + alignmentA;
                alignmentB = seqB + alignmentB;
                i--;
                j--;
            }
            else if (up > left) {
                alignmentA = seqA + alignmentA;
                alignmentB = "-" + alignmentB;
                i--;
            }
            else if (left > up) {
                alignmentA = "-" + alignmentA;
                alignmentB = seqB + alignmentB;
                j--;
            }

        }
    }


}
