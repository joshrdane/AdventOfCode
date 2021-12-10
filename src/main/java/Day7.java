import java.io.FileNotFoundException;
import java.util.HashMap;

public class Day7 {
    public static int partOne(int[] positions) {
        int result = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int crabPosition : positions) {
            max = Integer.max(max, crabPosition);
        }
        for (int i = 0; i < max; i++) {
            result = Integer.min(result, linearFuelCost(i, positions));
        }
        return  result;
    }

    public static int partTwo(int[] positions) {
        int result = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int crabPosition : positions) {
            max = Integer.max(max, crabPosition);
        }
        for (int i = 0; i <= max; i++) {
            result = Integer.min(result, additiveFuelCost(i, positions));
        }
        return  result;
    }

    public static int linearFuelCost(int position, int[] positions) {
        int result = 0;
        for (int crabPosition : positions) {
            int difference = position - crabPosition;
            if (difference < 0) {
                difference *= -1;
            }
            result += difference;
        }
        return result;
    }

    public static int additiveFuelCost(int position, int[] positions) {
        int result = 0;
        for (int crabPosition : positions) {
            int difference = position - crabPosition;
            if (difference < 0) {
                difference *= -1;
            }
            result += (difference + ((difference % 2) == 0 ? 1: 0)) * ((difference + 1) / 2);
        }
        return result;
    }

    public static int mean(int[] positions) {
        int total = 0;
        for (int position : positions) {
            total += position;
        }
        return (int) Math.round((double) (total) / positions.length);
    }

    public static int median(int[] positions) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int position : positions) {
            if (!map.containsKey(position)) {
                map.put(position, 0);
            }
            map.put(position, map.get(position) + 1);
        }
        int[] median = new int[] {0, Integer.MIN_VALUE};
        for (int key : map.keySet()) {
            if (map.get(key) > median[1]) {
                median[0] = key;
            }
        }
        return median[0];
    }

    public static void main(String[] args) throws FileNotFoundException {
        String[] input = Util.getInputAsArray("day7.txt");
        input = input[0].split(",");
        int[] positions = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            positions[i] = Integer.parseInt(input[i]);
        }
        System.out.println(partOne(positions));
        System.out.println(partTwo(positions));
    }
}
