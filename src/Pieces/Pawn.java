package Pieces;

import chess.Position;

public class Pawn extends Piece {

	public Pawn(Position p, String t) {
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
		if (team.equalsIgnoreCase("white")) str = "wp";
		else str = "bp";
		return str;
	}

}
