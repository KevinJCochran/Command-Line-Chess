package Pieces;

import chess.Board;
import chess.Position;

import java.util.List;

public class Rook extends Piece{

	public Rook(Position p, String t) {
		super(p, t);
	}

	@Override
	public void popMoves(List<Board.Square> board) {
		validMoves.clear();
		/*
		Rook can move in 4 directions: up, down, (white's)left, (white's)right.
		The 4 arrays represent these directions and the rook can move at most 7 places.
		 */
		Position up[] = new Position[7];
        Position down[] = new Position[7];
        Position right[] = new Position[7];
        Position left[] = new Position[7];
        // Populate the possible positions
        for (Position p : Position.values()) {
            for (int i = 0; i < 7; i++) { // Fill up positions
                if (p.coordEquals(current.x, current.y+(i+1)))
                    up[i] = p;
            }
            for (int i = 0; i < 7; i++) { // Fill down positions
                if (p.coordEquals(current.x, current.y-(i+1)))
                    down[i] = p;
            }
            for (int i = 0; i < 7; i++) { // Fill left positions
                if (p.coordEquals(current.x+(i+1), current.y))
                    left[i] = p;
            }
            for (int i = 0; i < 7; i++) { // Fill right positions
                if (p.coordEquals(current.x-(i+1), current.y))
                    right[i] = p;
            }
        }
        // Add the valid positions to validMoves list
        addValid(board,up);
        addValid(board,down);
        addValid(board,left);
        addValid(board,right);
    }

	@Override
	public void setCurrent(Position p) {
        current = p;
	}

	public String toString() {
		String str = null;
		if (team.equalsIgnoreCase("white")) str = "wR";
		else str = "bR";
		return str;
	}
	

}
