import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Day3 {

    enum Rating {
        O2Generator,
        CO2Scrubber
    }

    public static int RatingCalculator(Rating rating, ArrayList<String> input) {
        char[] equivalencyChoice;
        if (rating == Rating.O2Generator) {
            equivalencyChoice = new char[] {'1', '0'};
        } else {
            equivalencyChoice = new char[] {'0', '1'};
        }

        ArrayList<String> ratings = (ArrayList<String>) input.clone();
        int position = 0;
        while (ratings.size() > 1) {
            int[] occurrence = new int[2];
            for (String string : ratings) {
                if (string.charAt(position) == '0') {
                    occurrence[0]++;
                } else {
                    occurrence[1]++;
                }
            }
            ArrayList<String> removeList = new ArrayList<>();
            for (String string : ratings) {
                if (occurrence[0] == occurrence[1]) {
                    if (string.charAt(position) != equivalencyChoice[0]) {
                        removeList.add(string);
                    }
                } else {
                    char occurrenceType = occurrence[0] > occurrence[1] ? equivalencyChoice[1] : equivalencyChoice[0];
                    if (string.charAt(position) != occurrenceType) {
                        removeList.add(string);
                    }
                }
            }
            ratings.removeAll(removeList);
            position++;
        }
        int result = 0;
        for (int i = 0; i < ratings.get(0).length(); i++) {
            result <<= 1;
            if (ratings.get(0).charAt(i) == '1') {
                result |= 1;
            }
        }
        return result;
    }

    public static int partOne(String[] input) {
        int[][] data = new int[input[0].length()][2];
        for (String string : input) {
            for (int i = 0; i < string.length(); i++) {
                switch (string.charAt(i)) {
                    case '0' -> data[i][0]++;
                    case '1' -> data[i][1]++;
                }
            }
        }
        int gamma = 0;
        int epsilon = 0;
        for (int[] datum : data) {
            gamma <<= 1;
            epsilon <<= 1;
            if (datum[1] > datum[0]) {
                gamma |= 1;
            } else {
                epsilon |= 1;
            }
        }
        return gamma * epsilon;
    }

    public static int partTwo(ArrayList<String> input) {
        return RatingCalculator(Rating.O2Generator, input) * RatingCalculator(Rating.CO2Scrubber, input);
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> input = Util.getInputAsArrayList("day3.txt");
        System.out.println(partOne(input.toArray(new String[input.size()])));
        System.out.println(partTwo(input));
    }
}
