package Pieces;

import chess.Position;

public class Queen extends Piece {

	public Queen(Position p, String t) {
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
		if (team.equalsIgnoreCase("white")) str = "wQ";
		else str = "bQ";
		return str;
	}

}
