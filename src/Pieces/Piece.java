package Pieces;

import chess.Board;
import chess.Position;
import java.util.List;

public abstract class Piece {

    protected List<Position> validMoves;
    protected Position current;
    protected String team;

    public Piece(Position p, String t) {
        this.current = p;
        this.team = t;
    }
    // TODO write popMoves
    public abstract void popMoves(List<Board.Square> board);
    public abstract void setCurrent(Position p);

    public String toString() {
        return "??";
    }
}
