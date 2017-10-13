/**
 * Represents each of the squares on the chessboard
 *
 * @author aramaswami32
 */
public class Square {
    private char file;
    private char rank;
    /**
     * Creates a square with a specific file and rank
     *
     * @param file a char representing the column letter
     * @param rank a char representing the row number
     */
    public Square(char file, char rank) {
        this.file = file;
        this.rank = rank;
    }
    /**
     * Creates a square with a specific file and rank
     *
     * @param name a String that is the file and rank
     *  concatenated
     */
    public Square(String name) {
        this.file = name.charAt(0);
        this.rank = name.charAt(1);
    }
    /**
     * Returns the file of the square
     */
    public char getFile() {
        return this.file;
    }
    /**
     * Returns the rank of the square
     */
    public char getRank() {
        return this.rank;
    }
    /**
     * Returns a String that is a concatenation of
     *  the file and rank
     */
    public String toString() {
        return "" + this.file + this.rank;
    }
    /**
     * Returns whether this square object
     *  is equal to another square object
     *
     * @param other the other object to be
     *  compared to square to check equality
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof Square)) {
            return false;
        }
        Square that = (Square) other;
        return (this.file == that.file
            && this.rank == that.rank);

    }
}