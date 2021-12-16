import java.io.FileNotFoundException;
import java.util.Arrays;

public class Day9 {

    public static int partOne(int[][] data) {
        int result = 0;
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[row].length; col++) {
                boolean lowPoint = true;
                if (row - 1 >= 0) {
                    lowPoint &= data[row][col] < data[row - 1][col];
                }
                if (row + 1 < data.length) {
                    lowPoint &= data[row][col] < data[row + 1][col];
                }
                if (col - 1 >= 0) {
                    lowPoint &= data[row][col] < data[row][col - 1];
                }
                if (col + 1 < data[row].length) {
                    lowPoint &= data[row][col] < data[row][col + 1];
                }
                if (lowPoint) {
                    System.out.printf("LP %d %d : %d%n", row, col, data[row][col]);
                    result += data[row][col] + 1;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String[] input = Util.getInputAsArray("day9.txt");
        int[][] data = new int[input.length][];
        for (int row = 0; row < input.length; row++) {
            char[] array = input[row].toCharArray();
            data[row] = new int[array.length];
            for (int col = 0; col < array.length; col++) {
                data[row][col] = array[col] - '0';
            }
        }
        System.out.println(partOne(data));
    }
}
