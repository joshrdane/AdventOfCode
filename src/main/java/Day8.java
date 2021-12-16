import java.io.FileNotFoundException;
import java.util.Arrays;

public class Day8 {

    public static int partOne(String[][][] data) {
        int result = 0;
        for (int i = 0; i < data.length; i++) {
            for (int k = 0; k < data[i][1].length; k++) {
                switch (data[i][1][k].length()) {
                    case 2:
                    case 3:
                    case 4:
                    case 7:
                        result++;
                        break;
                }
            }
        }
        return result;
    }

    private static String alphabetize(String string) {
        char[] characters = string.toCharArray();
        Arrays.sort(characters);
        return new String(characters);
    }

    private static void swap(String[] array, int alpha, int beta) {
        String temp = array[alpha];
        array[alpha] = array[beta];
        array[beta] = temp;
    }

    public static int partTwo(String[][][] data) {
        int result = 0;
        for (int i = 0; i < data.length; i++) {
            for (int k = 0; k < data[i][1].length; k++) {
                data[i][0][k] = alphabetize(data[i][0][k]);
                switch (data[i][0][k].length()) {
                    case 2: // 1
                        swap(data[i][0], k, 1);
                        if (k != 1) {
                            k--;
                        }
                        break;
                    case 3: // 7
                        swap(data[i][0], k, 7);
                        if (k != 7) {
                            k--;
                        }
                        break;
                    case 4: // 4
                        swap(data[i][0], k, 4);
                        if (k != 4) {
                            k--;
                        }
                        break;
                    case 7: // 8
                        swap(data[i][0], k, 8);
                        if (k != 8) {
                            k--;
                        }
                        break;
                }
            }
        }
        System.out.println(Arrays.deepToString(data));
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String[] input = Util.getInputAsArray("day8.txt");
        String[][][] data = new String[input.length][2][];
        for (int i = 0; i < input.length; i++) {
            String[] temp = input[i].split(" \\| ");
            for (int j = 0; j < temp.length; j++) {
                data[i][j] = temp[j].split(" ");
            }
        }
        System.out.println(partOne(data));
        System.out.println(partTwo(data));
    }
}
