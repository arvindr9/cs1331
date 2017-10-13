/**
 * Represents a bishop
 *
 * @author aramaswami32
*/
public class Bishop extends Piece {
    /**
     * Creates a queen with a specific color
     * @param color the color of the bishop
     */
    public Bishop(Color color) {
        super(color);
    }
    /**
     * Returns a String that is the algebraic name of the bishop
     */
    public String algebraicName() {
        return "B";
    }
    /**
     * Returns a String that is the FEN name of the bishop
     */
    public String fenName() {
        if (color == Color.WHITE) {
            return "B";
        } else {
            return "b";
        }
    }
    /**
     * Returns a Square[] that represents the possible locations
     *  the bishop can move to
     * @param square the current location of the bishop
     */
    public Square[] movesFrom(Square square) {
        Square[] squares = new Square[14];
        char file = square.getFile();
        char rank = square.getRank();
        int minus = ((Character.getNumericValue(file) - 9)
            - (Character.getNumericValue(rank) + 48));
        int plus = ((Character.getNumericValue(file) - 9)
            + (Character.getNumericValue(rank) + 48));
        char file2;
        char rank2;
        int index = 0;
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
                    squares[index] = new
                        Square((char) (Character.getNumericValue(file2)
                            - 9 + 96)
                        , (char) (Character.getNumericValue(rank2) + 48));
                    index++;
                }
            }
        }
        Square[] newSquares = new Square[index];
        for (int i = 0; i < index; i++) {
            newSquares[i] = squares[i];
        }
        return newSquares;
    }
}