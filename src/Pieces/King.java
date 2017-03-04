package Pieces;

import chess.Position;

public class King extends Piece{

	public King(Position p, String t) {
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
		if (team.equalsIgnoreCase("white")) str = "wK";
		else str = "bK";
		return str;
	}

}
