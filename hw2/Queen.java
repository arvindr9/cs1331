/**
 * Represents a queen
 *
 * @author aramaswami32
*/
public class Queen extends Piece {
    /**
     * Creates a queen with a specific color
     * @param color the color of the queen
     */
    public Queen(Color color) {
        super(color);
    }
    /**
     * Returns a String that is the algebraic name of the queen
     */
    public String algebraicName() {
        return "Q";
    }
    /**
     * Returns a String that is the FEN name of the queen
     */
    public String fenName() {
        if (color == Color.WHITE) {
            return "Q";
        } else {
            return "q";
        }
    }
    /**
     * Returns a Square[] that represents the possible locations
     *  the queen can move to
     * @param square the current location of the queen
     */
    public Square[] movesFrom(Square square) {
        Square[] squaresStraight = new Square[14];
        char file = square.getFile();
        char rank = square.getRank();
        int index = 0;
        for (int count = 1; count <= 8; count++) {
            if ((char) (count + 48) != rank) {
                squaresStraight[index] = new Square(file, (char) (count + 48));
                index++;
            }
        }
        for (int count = 1; count <= 8; count++) {
            if ((char) (count + 9 + 87) != file) {
                squaresStraight[index]
                    = new Square((char) (count + 9 + 87), rank);
                index++;
            }
        }
        Square[] squaresDiag = new Square[14];
        int minus = ((Character.getNumericValue(file) - 9)
            - (Character.getNumericValue(rank) + 48));
        int plus = ((Character.getNumericValue(file) - 9)
            + (Character.getNumericValue(rank) + 48));
        char file2;
        char rank2;
        index = 0;
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                file2 = (char) (i + 9 + 87);
                rank2 = (char) (j + 48);
                int minus2 = ((Character.getNumericValue(file2) - 9)
                    - (Character.getNumericValue(rank2) + 48));
                int plus2 = ((Character.getNumericValue(file2) - 9)
                    + (Character.getNumericValue(rank2) + 48));
                if ((plus2 == plus && minus2 != minus)
                    || (plus2 != plus && minus2 == minus)) {
                    squaresDiag[index]
                        = new Square((char) (Character.getNumericValue(file2)
                        - 9 + 96), (char) (Character.getNumericValue(rank2)
                        + 48));
                    index++;
                }
            }
        }
        Square[] squares = new Square[14 + index];
        for (int i = 0; i < 14; i++) {
            squares[i] = squaresStraight[i];
        }
        for (int j = 14; j < squares.length; j++) {
            squares[j] = squaresDiag[j - 14];
        }
        return squares;
    }
}