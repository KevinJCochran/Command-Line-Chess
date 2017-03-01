package Pieces;

import chess.Position;
import java.util.List;

public abstract class Piece {

    protected List<Position> vaildMoves;
    protected Position current;
    protected String team;

    public Piece(Position p, String t) {
        this.current = p;
        this.team = t;
        popMoves();
    }
    // TODO write popMoves
    public abstract void popMoves();
    public abstract void setCurrent(Position p);

    // TODO write toString methods
}
