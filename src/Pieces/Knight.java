package Pieces;

import chess.Board;
import chess.Position;

import java.util.List;

public class Knight extends Piece{

    public Knight(Position p, String t) {
        super(p, t);
    }

    @Override
    public void popMoves(List<Board.Square> board) {

    }

    @Override
    public void setCurrent(Position p) {

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        String str = null;
        if (team.equalsIgnoreCase("white")) str = "wN";
        else str = "bN";
        return str;
    }
}
