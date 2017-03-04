package Pieces;

import chess.Position;

public class Knight extends Piece{

    public Knight(Position p, String t) {
        super(p, t);
    }

    @Override
    public void popMoves() {

    }

    @Override
    public void setCurrent(Position p) {

    }

    public String toString() {
        String str = null;
        if (team.equalsIgnoreCase("white")) str = "wN";
        else str = "bN";
        return str;
    }
}
