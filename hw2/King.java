/**
 * Represents a king
 *
 * @author aramaswami32
*/
public class King extends Piece {
    /**
     * Creates a queen with a specific color
     * @param color the color of the king
     */
    public King(Color color) {
        super(color);
    }
    /**
     * Returns a String that is the algebraic name of the king
     */
    public String algebraicName() {
        return "K";
    }
    /**
     * Returns a String that is the FEN name of the king
     */
    public String fenName() {
        if (color == Color.WHITE) {
            return "K";
        } else {
            return "k";
        }
    }
    /**
     * Returns a Square[] that represents the possible locations
     *  the king can move to
     * @param square the current location of the king
     */
    public Square[] movesFrom(Square square) {
        Square[] squares = new Square[0];
        char file = square.getFile();
        char rank = square.getRank();
        int len = 0;
        int index = 0;
        if (file != 'h') {
            len++;
            if (rank != '1') {
                len++;
            }
            if (rank != '8') {
                len++;
            }
        }
        if (file != 'a') {
            len++;
            if (rank != '1') {
                len++;
            }
            if (rank != '8') {
                len++;
            }
        }
        if (rank != '1') {
            len++;
        }
        if (rank != '8') {
            len++;
        }

        squares = new Square[len];
        if (file != 'h') {
            squares[index]
                = new Square((char) (Character.getNumericValue(file)
                + 1 + 87), rank);
            index++;
            if (rank != '1') {
                squares[index]
                    = new Square((char) (Character.getNumericValue(file)
                    + 1 + 87)
                    , (char) (Character.getNumericValue(rank) - 1 + 48));
                index++;
            }
            if (rank != '8') {
                squares[index]
                    = new Square((char) (Character.getNumericValue(file)
                    + 1 + 87)
                    , (char) (Character.getNumericValue(rank) + 1 + 48));
                index++;
            }
        }
        if (file != 'a') {
            squares[index]
                = new Square((char) (Character.getNumericValue(file)
                - 1 + 87), rank);
            index++;
            if (rank != 1) {
                squares[index]
                    = new Square((char) (Character.getNumericValue(file)
                    - 1 + 87), (char) (Character.getNumericValue(rank)
                    - 1 + 48));
                index++;
            }
            if (rank != '8') {
                squares[index]
                    = new Square((char) (Character.getNumericValue(file)
                    - 1 + 87)
                    , (char) (Character.getNumericValue(rank) + 1 + 48));
                index++;
            }
        }
        if (rank != '1') {
            squares[index] = new Square(file
                , (char) (Character.getNumericValue(rank) - 1 + 48));
            index++;
        }
        if (rank != '8') {
            squares[index] = new Square(file
                , (char) (Character.getNumericValue(rank) + 1 + 48));
            index++;
        }
        return squares;
    }
}