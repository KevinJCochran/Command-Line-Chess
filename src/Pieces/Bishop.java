package Pieces;

import chess.Position;

public class Bishop extends Piece {

    public Bishop(Position p, String t) {
        super(p, t);
    }

    @Override
    public void popMoves() {

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
