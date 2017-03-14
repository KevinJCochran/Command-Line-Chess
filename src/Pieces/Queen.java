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
