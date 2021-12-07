import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Util {
    public static String[] getInputAsArray(String fileName) throws FileNotFoundException {
        ArrayList<String> result = getInputAsArrayList(fileName);
        return result.toArray(new String[result.size()]);
    }

    public static ArrayList<String> getInputAsArrayList(String fileName) throws FileNotFoundException {
        ArrayList<String> result = new ArrayList<>();
        File file = new File("out/production/AdventOfCode/" + fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            result.add(scanner.nextLine());
        }
        return result;
    }
}
