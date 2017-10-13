/**
 * Represents a pawn
 *
 * @author aramaswami32
*/
public class Pawn extends Piece {
    /**
     * Creates a queen with a specific color
     * @param color the color of the pawn
     */
    public Pawn(Color color) {
        super(color);
    }
    /**
     * Returns a String that is the algebraic name of the pawn
     */
    public String algebraicName() {
        return "P";
    }
    /**
     * Returns a String that is the FEN name of the pawn
     */
    public String fenName() {
        if (color == Color.WHITE) {
            return "P";
        } else {
            return "p";
        }
    }
    /**
     * Returns a Square[] that represents the possible locations
     *  the pawn can move to
     * @param square the current location of the pawn
     */
    public Square[] movesFrom(Square square) {
        char file = square.getFile();
        char rank = square.getRank();
        int len = 0;
        int index = 0;
        Square[] squares = new Square[1];

        if (color == Color.BLACK) {
            if (rank != '1') {
                len++;
                rank = (char)
                    (Character.getNumericValue(rank) - 1 + 48);
                squares[index] = new Square(file, rank);
            } else {
                squares = new Square[0];
            }
        }
        if (color == Color.WHITE) {
            if (rank != '8') {
                len++;
                rank = (char) (Character.getNumericValue(rank) + 1 + 48);
                squares[index] = new Square(file, rank);
            } else {
                squares = new Square[0];
            }
        }
        return squares;
    }
}