import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*Note: index % 8 where index is an the index of a position at a board, is invariant among every position in a column.
    Also, Math.floor(index/8) is invariant among every position in a row.*/

public class PgnReader {

    private static String board =
        "rnbqkbnrpppppppp00000000000000000000000000000000PPPPPPPPRNBQKBNR";
    public static String tagValue(String tagName, String game) {
        int startPos = game.indexOf('[' + tagName);
        if (startPos >= 0) {
            String gamePrime = game.substring(startPos);
            String value =
                gamePrime.substring(
                    gamePrime.indexOf('\"') + 1,
                gamePrime.indexOf(']') - 1);
            return value;
        }
        return "NOT GIVEN";
    }

    public static String finalPosition(String game) {
        //System.out.println("running finalPosition");
        int moveIndex = game.indexOf("1.");
        String moves = game.substring(moveIndex);
        //System.out.println(moves);
        String[] moveArray = moves.split(" ");
        /*for (String i:moveArray) {
            System.out.println(i);
        }*/
        for (int ele = 0; ele < moveArray.length; ele++) {
            move(moveArray, ele);
        }
        return printFen(board);
    }

    public static void move(String[] moveArray, int ele) {
        //System.out.println("iteration " + ele);
        if (ele % 3 == 1) {
            moveWhite(moveArray[ele]);
            /*System.out.println("Board: \n"
                + board.substring(0, 8) + "\n"
                + board.substring(8, 16) + "\n"
                + board.substring(16, 24) + "\n"
                + board.substring(24, 32) + "\n"
                + board.substring(32, 40) + "\n"
                + board.substring(40, 48) + "\n"
                + board.substring(48, 56) + "\n"
                + board.substring(56) + "\n");*/
        }
        if (ele % 3 == 2) {
            moveBlack(moveArray[ele]);
            /*System.out.println("Board: \n"
                + board.substring(0, 8) + "\n"
                + board.substring(8, 16) + "\n"
                + board.substring(16, 24) + "\n"
                + board.substring(24, 32) + "\n"
                + board.substring(32, 40) + "\n"
                + board.substring(40, 48) + "\n"
                + board.substring(48, 56) + "\n"
                + board.substring(56) + "\n");*/
        }
    }

    public static void moveWhite(String move) {
        int position = (Character.getNumericValue(move.charAt(1)) < 10)
            ? position(move) : position(move.substring(1));
        //System.out.println("moving white "
        //    + move + ", position: " + position);
        char piece = move.charAt(0);
        switch (piece) {
        case 'R':
            moveWhiteRook(move.substring(1));
            break;
        case 'N':
            moveWhiteKnight(move.substring(1));
            break;
        case 'B':
            moveWhiteBishop(move.substring(1));
            break;
        case 'Q':
            moveWhiteQueen(move.substring(1));
            break;
        case 'K':
            moveWhiteKing(move.substring(1));
            break;
        case 'O':
            castleWhite(move);
            break;
        default:
            moveWhitePawn(move);
        }
    }

    public static void moveBlack(String move) {
        int position = (Character.getNumericValue(move.charAt(1)) < 10)
            ? position(move) : position(move.substring(1));
        //System.out.println("moving black "
        //    + move + ", position: " + position);
        char piece = move.charAt(0);
        switch (piece) {
        case 'R':
            moveBlackRook(move.substring(1));
            break;
        case 'N':
            moveBlackKnight(move.substring(1));
            break;
        case 'B':
            moveBlackBishop(move.substring(1));
            break;
        case 'Q':
            moveBlackQueen(move.substring(1));
            break;
        case 'K':
            moveBlackKing(move.substring(1));
            break;
        case 'O':
            castleBlack(move);
        default:
            moveBlackPawn(move);
        }
    }

    public static int position(String move) {
        return (8 - Character.getNumericValue(move.charAt(1)))
            * 8 + Character.getNumericValue(move.charAt(0)) - 10;
    }

    public static void moveWhiteRook(String move) {
        if (move.charAt(0) == 'x') {
            move = move.substring(1);
        }
        int pos = position(move);
        int index = board.indexOf('R');
        if (!checkRook(index, pos)) {
            String newBoard =
                board.substring(0, index) + "0" + board.substring(index + 1);
            index = newBoard.indexOf('R');
        }
        board = board.substring(0, index) + "0" + board.substring(index + 1);
        board = board.substring(0, pos) + "R" + board.substring(pos + 1);
    }

    public static void moveWhiteKnight(String move) {
        if (move.charAt(0) == 'x') {
            move = move.substring(1);
        }
        int pos = position(move);
        int index = board.indexOf('N');
        if (Math.abs(index - pos) != 6 && Math.abs(index - pos) != 10
            && Math.abs(index - pos) != 15
            && Math.abs(index - pos) != 17) {
            String newBoard = board.substring(0, index)
                + "0" + board.substring(index + 1);
            index = newBoard.indexOf('N');
        }
        board = board.substring(0, index) + "0" + board.substring(index + 1);
        board = board.substring(0, pos) + "N" + board.substring(pos + 1);
    }

    public static void moveWhiteBishop(String move) {
        if (move.charAt(0) == 'x') {
            move = move.substring(1);
        }
        int pos = position(move);
        int index = board.indexOf('B');

        if (!checkBishop(index, pos)) {
            String newBoard = board.substring(0, index)
                + "0" + board.substring(index + 1);
            index = newBoard.indexOf('B');
        }

        board = board.substring(0 , index) + "0" + board.substring(index + 1);
        board = board.substring(0 , pos) + "B" + board.substring(pos + 1);
    }

    public static void moveWhiteQueen(String move) {
        if (move.charAt(0) == 'x') {
            move = move.substring(1);
        }
        int pos = position(move);
        int index = board.indexOf('Q');

        if (!checkQueen(index, pos)) {
            String newBoard = board.substring(0, index)
                + "0" + board.substring(index + 1);
            index = newBoard.indexOf('Q');
        }

        board = board.substring(0, index) + "0" + board.substring(index + 1);
        board = board.substring(0, pos) + "Q" + board.substring(pos + 1);
    }

    public static void moveWhiteKing(String move) {
        if (move.charAt(0) == 'x') {
            move = move.substring(1);
        }
        int pos = position(move);
        int index = board.indexOf('K');
        board = board.substring(0, index) + "0" + board.substring(index + 1);
        board = board.substring(0, pos) + "K" + board.substring(pos + 1);
    }

    public static void moveWhitePawn(String move) {
        int pos;
        if (move.charAt(1) == 'x') {
            pos = position(move.substring(2));
            if (Character.getNumericValue(move.charAt(0))
                < Character.getNumericValue(move.charAt(2))) {
                board = board.substring(0, pos) + "P"
                    + board.substring(pos + 1, pos + 7) + "0"
                    + board.substring(pos + 8);
            }   else {
                board = board.substring(0, pos) + "P"
                    + board.substring(pos + 1, pos + 9)
                    + "0" + board.substring(pos + 10);
            }
            if (board.charAt(pos + 8) == 'p' && move.charAt(3) == '6') {
                board = board.substring(0, pos + 8)
                    + "0" + board.substring(pos + 9);
            }
        }   else {
            pos = position(move);

            if (board.charAt(pos + 8) == 'P') {
                board = board.substring(0, pos + 8)
                    + "0" + board.substring(pos + 9);
                board = board.substring(0, pos)
                    + "P" + board.substring(pos + 1);
            }   else if (board.charAt(pos + 16) == 'P') {
                board = board.substring(0, pos + 16)
                    + "0" + board.substring(pos + 17);
                board = board.substring(0, pos)
                    + "P" + board.substring(pos + 1);
            }
        }
        if (move.indexOf('=') != -1) {
            int equals = move.indexOf('=');
            char type = move.charAt(equals + 1);
            board = board.substring(0, pos)
                + type + board.substring(pos + 1);
        }
    }

    public static void moveBlackRook(String move) {
        if (move.charAt(0) == 'x') {
            move = move.substring(1);
        }
        int pos = position(move);
        int index = board.indexOf('r');
        if (!checkRook(index, pos)) {
            String newBoard = board.substring(0, index)
                + "0" + board.substring(index + 1);
            index = newBoard.indexOf('r');
        }
        board = board.substring(0, index) + "0" + board.substring(index + 1);
        board = board.substring(0, pos) + "r" + board.substring(pos + 1);
    }

    public static void moveBlackKnight(String move) {
        if (move.charAt(0) == 'x') {
            move = move.substring(1);
        }
        int pos = position(move);
        int index = board.indexOf('n');
        if (Math.abs(index - pos) != 6
            && Math.abs(index - pos) != 10
            && Math.abs(index - pos) != 15
            && Math.abs(index - pos) != 17) {
            String newBoard = board.substring(0, index)
                + "0" + board.substring(index + 1);
            index = newBoard.indexOf('n');
        }
        board = board.substring(0, index) + "0" + board.substring(index + 1);
        board = board.substring(0, pos) + "n" + board.substring(pos + 1);
    }

    public static void moveBlackBishop(String move) {
        if (move.charAt(0) == 'x') {
            move = move.substring(1);
        }
        int pos = position(move);
        int index = board.indexOf('b');

        if ((Math.floor(index / 8) + (index % 8)) % 2
            != (Math.floor(pos / 8) + (pos % 8)) % 2) {
            String newboard = board.substring(0, index)
                + "0" + board.substring(index + 1);
            index = newboard.indexOf('b');
        }

        board = board.substring(0, index) + "0" + board.substring(index + 1);
        board = board.substring(0, pos) + "b" + board.substring(pos + 1);
    }

    public static void moveBlackQueen(String move) {
        if (move.charAt(0) == 'x') {
            move = move.substring(1);
        }
        int pos = position(move);
        int index = board.indexOf('q');

        if (!checkQueen(index, pos)) {
            String newBoard = board.substring(0, index)
                + "0" + board.substring(index + 1);
            index = newBoard.indexOf('q');
        }

        board = board.substring(0, index) + "0" + board.substring(index + 1);
        board = board.substring(0, pos) + "q" + board.substring(pos + 1);
    }

    public static void moveBlackKing(String move) {
        if (move.charAt(0) == 'x') {
            move = move.substring(1);
        }
        int pos = position(move);
        int index = board.indexOf('k');
        board = board.substring(0, index) + "0" + board.substring(index + 1);
        board = board.substring(0, pos) + "k" + board.substring(pos + 1);
    }

    public static void moveBlackPawn(String move) {
        if (move.charAt(1) == 'x') {
            int pos = position(move.substring(2));
            if (Character.getNumericValue(move.charAt(0))
                < Character.getNumericValue(move.charAt(2))) {
                board = board.substring(0, pos - 9) + "0"
                    + board.substring(pos - 8, pos) + "p"
                    + board.substring(pos + 1);
            } else {
                board = board.substring(0, pos - 7)
                    + "0" + board.substring(pos - 6, pos)
                    + "p" + board.substring(pos + 1);
            }
            if (board.charAt(pos - 8) == 'P' && move.charAt(3) == '3') {
                board = board.substring(0, pos - 8)
                    + "0" + board.substring(pos - 7);
            }
        } else {
            int pos = position(move);
            if (board.charAt(pos - 8) == 'p') {
                board = board.substring(0, pos - 8) + "0"
                    + board.substring(pos - 7);
                board = board.substring(0, pos) + "p"
                    + board.substring(pos + 1);
            } else if (board.charAt(pos - 16) == 'p') {
                board = board.substring(0, pos - 16) + "0"
                    + board.substring(pos - 15);
                board = board.substring(0, pos) + "p"
                    + board.substring(pos + 1);
            }
        }
    }

    public static void castleWhite(String move) {
        if (move.equals("O-O")) {
            board = board.substring(0, 60) + "0KR0";
        } else {
            board = board.substring(56) + "0"
                + board.substring(57, 58) + "KR0"
                + board.substring(61);
        }
    }

    public static void castleBlack(String move) {
        if (move.equals("O-O")) {
            board = "0KR0" + board.substring(4);
        } else {
            board = board.substring(0, 3)
                + "0RK" + board.substring(6, 7)
                + "0" + board.substring(8);
        }
    }

    public static boolean checkBishop(int index, int pos) { //Checks if the move is legal diagonally from index to pos
        if (Math.floor(index / 8) + (index % 8)  //Motivation: Diagonal lines follow equations x + y = c, x - y = c
            == Math.floor(pos / 8) + (pos % 8)) {
            if (index < pos) {
                for (int i = index; i < pos; i += 7) {
                    if (board.charAt(i) != '0' && i != index) {
                        //System.out.println("not a bishop p1");
                        return false;
                    }
                }
                return true;
            }
            if (index > pos) {
                for (int i = index; i > pos; i -= 7) {
                    if (board.charAt(i) != '0' && i != index) {
                        //System.out.println("index: "
                        //    + index + ", position: " + pos);
                        //System.out.println("not a bishop p2");
                        return false;
                    }
                }
                return true;
            }
        }
        if (Math.floor(index / 8) - (index % 8)
            == Math.floor(pos / 8) - (pos % 8)) {
            if (index < pos) {
                for (int i = index; i < pos; i += 9) {
                    if (board.charAt(i) != '0' && i != index) {
                        //System.out.println("not a bishop p3"
                        //    + "index: " + i + "pos: " + pos);
                        return false;
                    }
                }
                return true;
            }
            if (index > pos) {
                for (int i = index; i > pos; i -= 9) {
                    if (board.charAt(i) != '0' && i != index) {
                        //System.out.println("not a bishop p4");
                        return false;
                    }
                }
                return true;
            }
        }
        //System.out.println("not a bishop p5");
        return false;
    }

    public static boolean checkRook(int index, int pos) { //Checks if the move is legal laterally from index to pos
        if (Math.floor(index / 8) == Math.floor(pos / 8)) {
            if (index < pos) {
                for (int i = index; i < pos; i++) {
                    if (board.charAt(i) != '0' && i != index) {
                        //System.out.println("not a rook p1");
                        return false;
                    }
                }
                return true;
            }
            if (index > pos) {
                for (int i = index; i > pos; i--) {
                    if (board.charAt(i) != '0' && i != index) {
                        //System.out.println("not a rook p2");
                        return false;
                    }
                }
                return true;
            }
        }
        if ((index % 8) == (pos % 8)) {
            if (index < pos) {
                for (int i = index; i < pos; i += 8) {
                    if (board.charAt(i) != '0' && i != index) {
                        //System.out.println("not a rook p3");
                        return false;
                    }
                }
                return true;
            }
            if (index > pos) {
                for (int i = index; i > pos; i -= 8) {
                    if (board.charAt(i) != '0' && i != index) {
                        //System.out.println("not a rook p4");
                        return false;
                    }
                }
                return true;
            }
        }
        //System.out.println("not a rook p5");
        return false;

    }

    public static boolean checkQueen(int index, int pos) { 
        return (checkBishop(index, pos) || checkRook(index, pos));
    }

    public static String printFen(String board) {
        StringBuilder fen = new StringBuilder();
        int consec = 0;
        for (int i = 0; i < 64; i++) {
            if (board.charAt(i) != '0') {
                if (consec != 0) {
                    fen.append(consec);
                    consec = 0;
                }
                fen.append(board.charAt(i));
            } else {
                consec++;
            }
            if (i % 8 == 7) {
                if (consec != 0) {
                    fen.append(consec);
                    consec = 0;
                }
                if (i != 63) {
                    fen.append('/');
                }
            }
        }
        return fen.toString();
    }
    public static String fileContent(String path) {
        Path file = Paths.get(path);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                // Add the \n that's removed by readline()
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
            System.exit(1);
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        String game = fileContent(args[0]);
        System.out.format("Event: %s%n", tagValue("Event", game));
        System.out.format("Site: %s%n", tagValue("Site", game));
        System.out.format("Date: %s%n", tagValue("Date", game));
        System.out.format("Round: %s%n", tagValue("Round", game));
        System.out.format("White: %s%n", tagValue("White", game));
        System.out.format("Black: %s%n", tagValue("Black", game));
        System.out.format("Result: %s%n", tagValue("Result", game));
        System.out.println("Final Position:");
        System.out.println(finalPosition(game));
    }
}
