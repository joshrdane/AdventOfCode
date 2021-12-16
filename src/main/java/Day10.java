import java.io.FileNotFoundException;
import java.util.Stack;

public class Day10 {

    public static int partOne(String line) {
        Stack<Character> bracketStack = new Stack<>();
        int result = 0;
        char[] data = line.toCharArray();
        for (int i = 0; i < data.length; i++) {
            switch (data[i]) {
                case '(', '[', '{', '<' -> bracketStack.push(data[i]);
                case ')', ']', '}', '>' -> {
                    char expected = bracketStack.pop();
                    expected = switch (expected) {
                        case '(' -> ')';
                        case '[' -> ']';
                        case '{' -> '}';
                        case '<' -> '>';
                        default -> expected;
                    };
                    if (data[i] != expected) {
                        result += switch (data[i]) {
                            case ')' -> 3;
                            case ']' -> 57;
                            case '}' -> 1197;
                            case '>' -> 25137;
                            default -> 0;
                        };
                        System.out.printf("Expected %c but found %c instead.%n", expected, data[i]);
                        i = data.length;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String[] input = Util.getInputAsArray("day10.txt");
        int result = 0;
        for (String line : input) {
            result += partOne(line);
        }
        System.out.println(result);
    }
}
