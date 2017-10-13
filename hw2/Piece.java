/**
 * Blueprint for classes of the pieces
 *
 * @author aramaswami32
 */

public abstract class Piece {
    protected Color color;
    /**
     * Creates a piece with a specific color
     * @param color the color of the piece
     */
    public Piece(Color color) {
        this.color = color;
    }
    /**
     * Returns the color of the piece
     */
    public Color getColor() {
        return this.color;
    }
    /**
     * Returns a String that is the algebraic name of the piece
     */
    public abstract String algebraicName();
    /**
     * Returns a String that is the FEN name of the piece
     */
    public abstract String fenName();
    /**
     * Returns a Square[] that represents the possible locations
     *  a piece can move to
     */
    public abstract Square[] movesFrom(Square square);
}