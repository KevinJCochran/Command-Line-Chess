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
        validMoves.clear();
		/*
		Bishop can move in 4 directions: upLeft, upRight, downLeft, downRight.
		The 4 arrays represent these directions and the Bishop can move at most 7 places.
		 */
        Position upRight[] = new Position[7];
        Position upLeft[] = new Position[7];
        Position downRight[] = new Position[7];
        Position downLeft[] = new Position[7];
        // Populate the possible positions
        for (Position p : Position.values()) {
            for (int i = 0; i < 7; i++) { // Fill upRight positions
                if (p.coordEquals(current.x+(i+1), current.y+(i+1)))
                    upRight[i] = p;
            }
            for (int i = 0; i < 7; i++) { // Fill upLeft positions
                if (p.coordEquals(current.x-(i+1), current.y+(i+1)))
                    upLeft[i] = p;
            }
            for (int i = 0; i < 7; i++) { // Fill downRight positions
                if (p.coordEquals(current.x+(i+1), current.y-(i+1)))
                    downRight[i] = p;
            }
            for (int i = 0; i < 7; i++) { // Fill downLeft positions
                if (p.coordEquals(current.x-(i+1), current.y-(i+1)))
                    downLeft[i] = p;
            }
        }
        // Add the valid positions to validMoves list
        addValid(board,upLeft);
        addValid(board,upRight);
        addValid(board,downLeft);
        addValid(board,downRight);
    }

    @Override
    public void setCurrent(Position p) {
        this.current = p;
    }

    @Override
    public String toString() {
        String str = null;
        if (team.equalsIgnoreCase("white")) str = "wB";
        else str = "bB";
        return str;
    }
}
