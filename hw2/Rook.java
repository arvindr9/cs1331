/**
 * Represents a rook
 *
 * @author aramaswami32
*/
public class Rook extends Piece {
    /**
     * Creates a queen with a specific color
     * @param color the color of the rook
     */
    public Rook(Color color) {
        super(color);
    }
    /**
     * Returns a String that is the algebraic name of the rook
     */
    public String algebraicName() {
        return "R";
    }
    /**
     * Returns a String that is the FEN name of the rook
     */
    public String fenName() {
        if (color == Color.WHITE) {
            return "R";
        } else {
            return "r";
        }
    }
    /**
     * Returns a Square[] that represents the possible locations
     *  the rook can move to
     * @param square the current location of the rook
     */
    public Square[] movesFrom(Square square) {
        Square[] squares = new Square[14];
        char file = square.getFile();
        char rank = square.getRank();
        int index = 0;
        for (int count = 1; count <= 8; count++) {
            if ((char) (count + 48) != rank) {
                squares[index] = new Square(file, (char) (count + 48));
                index++;
            }

        }
        for (int count = 1; count <= 8; count++) {
            if ((char) (count + 9 + 87) != file) {
                squares[index] = new Square((char) (count + 9 + 87), rank);
                index++;
            }
        }
        return squares;
    }
}