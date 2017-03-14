package Pieces;

import chess.Board;
import chess.Position;

import java.util.List;

public class Bishop extends Piece {

    public Bishop(Position p, String t) {
        super(p, t);
    }

    @Override
    public void popMoves(List<Board.Square> board) {

    }

    @Override
    public void setCurrent(Position p) {

    }

    @Override
    public String toString() {
        String str = null;
        if (team.equalsIgnoreCase("white")) str = "wB";
        else str = "bB";
        return str;
    }
}
