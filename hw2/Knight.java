/**
 * Represents a knight
 *
 * @author aramaswami32
*/
public class Knight extends Piece {
    /**
     * Creates a queen with a specific color
     * @param color the color of the knight
     */
    public Knight(Color color) {
        super(color);
    }
    /**
     * Returns a String that is the algebraic name of the knight
     */
    public String algebraicName() {
        return "N";
    }
    /**
     * Returns a String that is the FEN name of the knight
     */
    public String fenName() {
        if (color == Color.WHITE) {
            return "N";
        } else {
            return "n";
        }
    }
    /**
     * Returns a Square[] that represents the possible locations
     *  the knight can move to
     * @param square the current location of the knight
     */
    public Square[] movesFrom(Square square) {
        Square[] squares = new Square[8];
        char file = square.getFile();
        char rank = square.getRank();
        int index = 0;
        if (Character.getNumericValue(file) - 9 + 2 <= 8
            && Character.getNumericValue(rank) + 1 <= 8) {
            squares[index] = new
                Square((char) (Character.getNumericValue(file)
                + 87 + 2), (char) (Character.getNumericValue(rank)
                + 48 + 1));
        }
        if (Character.getNumericValue(file) - 9 + 1 <= 8
            && Character.getNumericValue(rank) + 2 <= 8) {
            squares[index] = new
            Square((char) (Character.getNumericValue(file)
            + 87 + 1), (char) (Character.getNumericValue(rank)
            + 48 + 2));
        }
        if (Character.getNumericValue(file) - 9 - 2 >= 1
            && Character.getNumericValue(rank) + 1 <= 8) {
            squares[index] = new
                Square((char) (Character.getNumericValue(file)
                + 87 - 2), (char) (Character.getNumericValue(rank)
                + 48 + 1));
        }
        if (Character.getNumericValue(file) - 9 - 1 >= 1
            && Character.getNumericValue(rank) + 2 <= 8) {
            squares[index] = new
                Square((char) (Character.getNumericValue(file)
                + 87 - 1), (char) (Character.getNumericValue(rank)
                + 48 + 2));
            index++;
        }
        if (Character.getNumericValue(file) - 9 + 2 <= 8
            && Character.getNumericValue(rank) - 1 >= 1) {
            squares[index] = new
                Square((char) (Character.getNumericValue(file)
                + 87 + 2), (char) (Character.getNumericValue(rank)
                + 48 - 1));
        }
        if (Character.getNumericValue(file) - 9 + 1 <= 8
            && Character.getNumericValue(rank) - 2 >= 1) {
            squares[index] = new
                Square((char) (Character.getNumericValue(file)
                + 87 + 1), (char) (Character.getNumericValue(rank)
                + 48 - 2));
        }
        if (Character.getNumericValue(file) - 9 - 2 >= 1
            && Character.getNumericValue(rank) - 1 >= 1) {
            squares[index] = new
                Square((char) (Character.getNumericValue(file)
                + 87 - 2), (char) (Character.getNumericValue(rank)
                + 48 - 1));
        }
        if (Character.getNumericValue(file) - 9 - 1 >= 1
            && Character.getNumericValue(rank) - 2 >= 1) {
            squares[index] = new
                Square((char) (Character.getNumericValue(file)
                + 87 - 1), (char) (Character.getNumericValue(rank)
                + 48 - 2));
        }
        Square[] newSquares = new Square[index];
        for (int i = 0; i < index; i++) {
            newSquares[i] = squares[i];
        }
        return newSquares;
    }
}