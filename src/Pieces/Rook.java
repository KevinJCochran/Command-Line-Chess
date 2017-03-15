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
		// TODO write popMoves Rook
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
