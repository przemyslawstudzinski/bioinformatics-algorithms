package bioinformatic.algorithms;

public class Algorithm {

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
        i = sequence1.getValues().size();
        j = sequence2.getValues().size();
        this.fmatrix = fmatrix.getMatrix();
        this.similarityMatrix = similarityMatrix;
        score = 0;
    }

    public void computeNeedlemanWunsch() {
        i = sequence1.getValues().size();
        j = sequence2.getValues().size();
        while (i > 0 && j > 0) {
            char seqA = sequence1.getValues().get(i - 1);
            char seqB = sequence2.getValues().get(j - 1);
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
            else if (left >= up) {
                alignmentA = "-" + alignmentA;
                alignmentB = seqB + alignmentB;
                j--;
            }
        }
        while (i != 0 || j != 0) {
            if(i == 0 && j > 0) {
                char seqB = sequence2.getValues().get(j - 1);
                alignmentA = "-" + alignmentA;
                alignmentB = seqB + alignmentB;
                j--;
            }
            if(this.j == 0 && i > 0) {
                char seqA = sequence1.getValues().get(i - 1);
                alignmentA = seqA + alignmentA;
                alignmentB = "-" + alignmentB;
                i--;
            }
        }
    }

    public void computeHirschberg(Sequence seqA, Sequence seqB) {
        if (seqA != null && seqB != null) {
            sequence1 = seqA;
            sequence2 = seqB;
        }

        if (sequence1.getValues().size() == 0)
        {
            for (int i = 0; i < sequence2.getValues().size(); i++)
            {
                alignmentA += "-";
                alignmentB = alignmentB + sequence2.getValues().get(i);
            }
        }
        else if (sequence2.getValues().size() == 0)
        {
            for(int i = 0; i < sequence1.getValues().size(); i++)
            {
                alignmentA = alignmentA + sequence1.getValues().get(i);
                alignmentB += "-";
            }
        }
        else if(sequence1.getValues().size() == 1 || sequence2.getValues().size() == 1)
        {
            computeNeedlemanWunsch();
        }
        else
        {
            int xLen = sequence1.getValues().size();
            int xMid =  xLen / 2;
            int yLen = sequence2.getValues().size();

            // ScoreL
            Sequence seqAFirstPart = new Sequence(sequence1.getContent().substring(0, xMid));
            int[] scoreL = NWScore(seqAFirstPart, sequence2);

            // ScoreR
            Sequence seqASecondPart = new Sequence(sequence1.getContent().substring(xMid, xLen));
            String seqASecondPartRev = new StringBuilder(seqASecondPart.getContent()).reverse().toString();
            Sequence seqASecondPartReverse = new Sequence(seqASecondPartRev);
            String sequence2Rev = new StringBuilder(sequence2.getContent()).reverse().toString();
            Sequence sequence2Reverse = new Sequence(sequence2Rev);
            int[] scoreR = NWScore(seqASecondPartReverse, sequence2Reverse);

            // reverse array
            for(int i = 0; i < scoreR.length / 2; i++)
            {
                int temp = scoreR[i];
                scoreR[i] = scoreR[scoreR.length - i - 1];
                scoreR[scoreR.length - i - 1] = temp;
            }
            // find ymid with arg max
            int yMid = 0;
            int maxVal = - Integer.MAX_VALUE;
            for (int i = 0; i < scoreL.length; i++)
            {
                if (maxVal < scoreL[i] + scoreR[i])
                {
                    maxVal = scoreL[i] + scoreR[i];
                    yMid = i;
                }
            }

            // recursive calls
            Sequence seqBFirstPart = new Sequence(sequence2.getContent().substring(0, yMid));
            Sequence seqBSecondPart = new Sequence(sequence2.getContent().substring(yMid, yLen));

            computeHirschberg(seqAFirstPart, seqBFirstPart);
            computeHirschberg(seqASecondPart, seqBSecondPart);
        }
    }

    public int[] NWScore(Sequence seq1, Sequence seq2)
    {
        Fmatrix fmatrix = new Fmatrix(seq1, seq2, similarityMatrix, 0);
        fmatrix.compute();
        int[] lastRow = fmatrix.getLastRow();
        return lastRow;
    }

    public void calculateScore() {
        for (int i = 0; i < alignmentA.length(); i++) {
            char A = alignmentA.charAt(i);
            char B = alignmentB.charAt(i);

            if (A == B) { // if a == b add 1 (from similarity matrix)
                score += similarityMatrix.getMatrix().get(A).get(B);
            } else if (A == '-' || B == '-') { // if gap add -1
                score += Fmatrix.gapPenalty;
            } else { // if a != b add -1 (from similarity matrix)
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
