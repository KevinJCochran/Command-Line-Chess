package Pieces;

import chess.Position;
import java.util.List;

public abstract class Piece {

    protected List<Position> vaildMoves;
    protected Position current;
    protected String team;

    public abstract void popMoves();
    public abstract void setCurrent(Position p);
}
