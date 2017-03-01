package chess;

public class Location {
    String piece;
    boolean occupied;

    public Location() {
        piece = null;
        occupied = false;
    }

    public Location(String piece) {
        this.piece = piece;
        this.occupied = true;
    }

    public Location(String piece, boolean occupied) {
        this.piece = piece;
        this.occupied = occupied;
    }
}
