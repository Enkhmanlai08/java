import java.io.*;
public class hw2{
    public static void main(String[] args) {
        try {
            int[][] matrix = readMatrix("input1.txt");
            if (sumMainDiagonal(matrix) == 5) {
                appendMatrixToFile(matrix, "input2.txt");
                System.out.println("диагональ нийлбэр нь 5тай тэнцүү Matrix-ийг input2.txt файлд нэмлээ.");
            }
        } catch (IOException e) {
        }
    }
    private static int[][] readMatrix(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int numRows = Integer.parseInt(br.readLine());
            int numCols = Integer.parseInt(br.readLine());
            int[][] matrix = new int[numRows][numCols];
            for (int i = 0; i < numRows; i++) {
                line = br.readLine();
                String[] values = line.split("\\s+");
                for (int j = 0; j < numCols; j++) {
                    matrix[i][j] = Integer.parseInt(values[j]);
                }
            }
            return matrix;
        }
    }
    private static int sumMainDiagonal(int[][] matrix) {
        int sum = 0;
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        int minDim = Math.min(numRows, numCols);
        for (int i = 0; i < minDim; i++) {
            sum += matrix[i][i];
        }
        return sum;
    }
    private static void appendMatrixToFile(int[][] matrix, String filename) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            int numRows = matrix.length;
            int numCols = matrix[0].length;
            bw.write(numRows + "\n");
            bw.write(numCols + "\n");
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    bw.write(matrix[i][j] + " ");
                }
                bw.write("\n");
            }
        }
    }
}
