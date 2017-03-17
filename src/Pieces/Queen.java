package Pieces;

import chess.Board;
import chess.Position;

import java.util.List;

public class Queen extends Piece {

	public Queen(Position p, String t) {
		super(p, t);
	}

	@Override
	public void popMoves(List<Board.Square> board) {
	    validMoves.clear();
	    /*
	    The Queen is a combonation of Rook and Bishop class. It has 8 directions
	    it can move in: up, down, left, right, upLeft, upRight, downLeft, downRight
	     */
        Position up[] = new Position[7];
        Position down[] = new Position[7];
        Position right[] = new Position[7];
        Position left[] = new Position[7];
        Position upRight[] = new Position[7];
        Position upLeft[] = new Position[7];
        Position downRight[] = new Position[7];
        Position downLeft[] = new Position[7];
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
        addValid(board,up);
        addValid(board,down);
        addValid(board,left);
        addValid(board,right);
        addValid(board,upLeft);
        addValid(board,upRight);
        addValid(board,downLeft);
        addValid(board,downRight);
	}

	@Override
	public void setCurrent(Position p) {
	    this.current = p;
	}

	public String toString() {
		String str = null;
		if (team.equalsIgnoreCase("white")) str = "wQ";
		else str = "bQ";
		return str;
	}

}
