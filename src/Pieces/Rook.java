package Pieces;

import chess.Position;

public class Rook extends Piece{

	public Rook(Position p, String t) {
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
		if (team.equalsIgnoreCase("white")) str = "wR";
		else str = "bR";
		return str;
	}
	

}
