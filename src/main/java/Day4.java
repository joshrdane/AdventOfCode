import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Day4 {

    public static int partOne(String[] numbers, BingoBoard[] boards) {
        for (String string : numbers) {
            int number = Integer.parseInt(string);
            for (BingoBoard board : boards) {
                board.mark(number);
                if (board.checkBingo()) {
                    return board.getScore(number);
                }
            }
        }
        return -1;
    }

    public static int partTwo(String[] numbers, BingoBoard[] boardArray) {
        ArrayList<BingoBoard> boards = new ArrayList<>(List.of(boardArray));

        for (String string : numbers) {
            int number = Integer.parseInt(string);
            ArrayList<BingoBoard> removeBoards = new ArrayList<>();
            for (BingoBoard board : boards) {
                board.mark(number);
                if (board.checkBingo()) {
                    if (boards.size() == 1) {
                        return board.getScore(number);
                    }
                    removeBoards.add(board);
                }
            }
            boards.removeAll(removeBoards);
        }
        return -1;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String[] input = Util.getInputAsArray("day4.txt");
        String[] numbers = input[0].split(",");
        BingoBoard[] boards = new BingoBoard[(input.length - 1) / 6];
        for (int i = 0; i < boards.length; i++) {
            int[][] boardInfo = new int[5][5];
            for (int j = 0; j < 5; j++) {
                int k = 0;
                for (String number : input[2 + i * 6 + j].split(" ")) {
                    if (!number.equals("")) {
                        boardInfo[j][k++] = Integer.parseInt(number);
                    }
                }
            }
            boards[i] = new BingoBoard(boardInfo);
        }
        System.out.println(partOne(numbers, boards));
        System.out.println(partTwo(numbers, boards));
    }
}

class BingoBoard {
    int[][] numberMap;
    boolean[][] bitMap;

    BingoBoard(int[][] numberMap) {
        this.numberMap = numberMap;
        bitMap = new boolean[numberMap.length][numberMap.length];
    }

    public void mark(int number) {
        for (int row = 0; row < numberMap.length; row++) {
            for (int col = 0; col < numberMap[row].length; col++) {
                if (numberMap[row][col] == number) {
                    bitMap[row][col] = true;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int[] rows : numberMap) {
            for (int col : rows) {
                result.append(col);
                result.append(' ');
            }
            result.append('\n');
        }
        return result.toString();
    }

    public String toBitString() {
        StringBuilder result = new StringBuilder();
        for (boolean[] rows : bitMap) {
            for (boolean col : rows) {
                result.append(col ? 1 : 0);
                result.append(' ');
            }
            result.append('\n');
        }
        return result.toString();
    }

    public boolean checkBingo() {
        boolean win;
        for (boolean[] row : bitMap) {
            win = true;
            for (int col = 0; col < bitMap.length; col++) {
                win &= row[col];
            }
            if (win) {
                return true;
            }
        }
        for (int col = 0; col < bitMap.length; col++) {
            win = true;
            for (boolean[] row : bitMap) {
                win &= row[col];
            }
            if (win) {
                return true;
            }
        }
        return false;
    }

    public int getScore(int last) {
        int score = 0;
        for (int row = 0; row < bitMap.length; row++) {
            for (int col = 0; col < bitMap.length; col++) {
                if (!bitMap[row][col]) {
                    score += numberMap[row][col];
                }
            }
        }
        return score * last;
    }
}
