import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2 {

    public static void main(String[] args) throws FileNotFoundException {
        enum Part {
            ONE,
            TWO
        }

        for (Part part : Part.values()) {
            long depth = 0;
            long position = 0;
            long aim = 0;

            File file = new File("out/production/AdventOfCode/day2.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] command = scanner.nextLine().trim().split(" ");
                int number = Integer.parseInt(command[1]);
                switch (command[0]) {
                    case "forward":
                        position += number;
                        if (part == Part.TWO) {
                            depth += aim * number;
                        }
                        break;
                    case "down":
                        if (part == Part.ONE) {
                            depth += number;
                        } else if (part == Part.TWO) {
                            aim += number;
                        }
                        break;
                    case "up":
                        if (part == Part.ONE) {
                            depth -= number;
                        } else if (part == Part.TWO) {
                            aim -= number;
                        }
                        break;
                }
            }
            scanner.close();
            System.out.println(depth * position);
        }
    }
}
