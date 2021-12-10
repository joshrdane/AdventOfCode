import java.io.FileNotFoundException;

public class Day5 {

    private static void swap(int[][] array, int alpha, int beta) {
        int[] temp = array[alpha];
        array[alpha] = array[beta];
        array[beta] = temp;
    }

    private static void orderPoints(int[][] points) {
        if (points[0][0] + points[0][1] > points[1][0] + points[1][1]) {
            swap(points, 0, 1);
        }
    }

    public static int partOne(int[][][] lines, int[] size) {
        int[][] map = new int[size[0]+1][size[1]+1];
        for (int[][] line : lines) {
            if (line[0][0] == line[1][0]) {
                // Horizontal
                for (int i = line[0][1]; i <= line[1][1]; i++) {
                    map[line[0][0]][i]++;
                }
            } else if (line[0][1] == line[1][1]) {
                // Vertical
                for (int i = line[0][0]; i <= line[1][0]; i++) {
                    map[i][line[0][1]]++;
                }
            }
        }
        int result = 0;
        for (int[] row : map) {
            for (int col : row) {
                if (col > 1) {
                    result++;
                }
            }
        }
        return result;
    }

    public static int partTwo(int[][][] lines, int[] size) {
        int[][] map = new int[size[0] + 1][size[1] + 1];
        for (int[][] line : lines) {
            if (line[0][0] == line[1][0]) {
                // Horizontal
                for (int i = line[0][1]; i <= line[1][1]; i++) {
                    map[line[0][0]][i]++;
                }
            } else if (line[0][1] == line[1][1]) {
                // Vertical
                for (int i = line[0][0]; i <= line[1][0]; i++) {
                    map[i][line[0][1]]++;
                }
            } else {
                // Diagonal
                int mod = 1;
                if (line[0][0] + line[0][1] == line[1][0] + line[1][1]) {
                    if (line[0][0] > line[1][0]) {
                        swap(line, 0, 1);
                    }
                    if (line[0][1] > line[1][1]) {
                        mod = -1;
                    }
                }
                for (int i = 0; i <= line[1][0] - line[0][0]; i++) {
                    map[line[0][0] + i][line[0][1] + i * mod]++;
                }
            }
        }
        int result = 0;
        for (int[] row : map) {
            for (int col : row) {
                if (col > 1) {
                    result++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String[] input = Util.getInputAsArray("day5.txt");
        int[] max = new int[2];
        int[][][] lines = new int[input.length][2][2];
        for (int i = 0; i < input.length; i++) {
            String[] values;
            String[] points = input[i].split(" -> ");
            for (int j = 0; j < 2; j++) {
                values = points[j].split(",");
                for (int k = 0; k < 2; k++) {
                    lines[i][j][k] = Integer.parseInt(values[k]);
                    if (lines[i][j][k] > max[k]) {
                        max[k] = lines[i][j][k];
                    }
                }
            }
            orderPoints(lines[i]);
        }
        System.out.println(partOne(lines, max));
        System.out.println(partTwo(lines, max));
    }
}
