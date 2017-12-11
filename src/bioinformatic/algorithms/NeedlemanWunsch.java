package bioinformatic.algorithms;

public class NeedlemanWunsch extends Algorithm {

    public NeedlemanWunsch(Sequence sequence1, Sequence sequence2, Fmatrix fmatrix, SimilarityMatrix similarityMatrix) {
        super(sequence1, sequence2, fmatrix, similarityMatrix);
    }

    @Override
    public void compute() {
        while (this.i > 0 || this.j > 0) {
            char seqA = sequence1.values.get(i - 1);
            char seqB = sequence2.values.get(j - 1);

            int similarityValue = similarityMatrix.getMatrix().get(seqA).get(seqB);
            if (this.i > 0 && this.j > 0 && (fmatrix[i][j] == fmatrix[i - 1][j - 1] + similarityValue)) {
                alignmentA = seqA + alignmentA;
                alignmentB = seqB + alignmentB;
                i  = i - 1;
                j  = j - 1;
            } else if (i > 0 && fmatrix[i][j] == (fmatrix[i - 1][j] + Fmatrix.gapPenalty)) {
                alignmentA = seqA + alignmentA;
                alignmentB = "-" + alignmentB;
                i = i - 1;
            } else {
                alignmentA = "-" + alignmentA;
                alignmentB = seqB + alignmentB;
                j = j - 1;
            }
        }
    }


}
