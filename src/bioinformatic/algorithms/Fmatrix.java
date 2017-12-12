package bioinformatic.algorithms;

public class Fmatrix {

    public static int gapPenalty;

    private Sequence sequence1;
    private Sequence sequence2;

    private int[][] matrix;
    private SimilarityMatrix similarityMatrix;

    public Fmatrix(Sequence sequence1, Sequence sequence2, SimilarityMatrix similarityMatrix, int gapPenalty) {
        this.sequence1 = sequence1;
        this.sequence2 = sequence2;
        this.similarityMatrix = similarityMatrix;
        matrix = new int[sequence1.getValues().size() + 1][sequence2.getValues().size() + 1];
        this.gapPenalty = gapPenalty;
    }

    public void compute() {
        //initialize edge values
        for (int i = 0; i < sequence1.getValues().size() + 1; i++) {
            matrix[i][0] = gapPenalty * i;
        }

        for (int j = 0; j < sequence2.getValues().size() + 1; j++) {
            matrix[0][j] = gapPenalty * j;
        }

        //other values
        for (int i = 1; i < sequence1.getValues().size() + 1; i++) {
            for (int j = 1; j < sequence2.getValues().size() + 1; j++) {
                char seq1 = sequence1.getValues().get(i - 1);
                char seq2 = sequence2.getValues().get(j - 1);

                int match = matrix[i - 1][j - 1] + similarityMatrix.getMatrix().get(seq1).get(seq2);
                int delete = matrix[i - 1][j] + gapPenalty;
                int insert = matrix[i][j - 1] + gapPenalty;
                matrix[i][j] = Math.max(Math.max(delete, insert), match);
            }
        }
    }

    public int[] getLastRow() {
        int rowLength = matrix[0].length;
        int[] result = new int[rowLength];
        for (int i = 0; i < rowLength; i++)
        {
            result[i] = matrix[matrix.length - 1][i];
        }
        return result;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < sequence1.getValues().size() + 1; i++) {
            for (int j = 0; j < sequence2.getValues().size() + 1; j++) {
                output += String.valueOf(matrix[i][j]) + "\t";

            }
            output += " \n";
        }
        return output;
    }
}


