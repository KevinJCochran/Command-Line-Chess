package Pieces;

import chess.Board;
import chess.Position;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private boolean firstMove = true;

	public Pawn(Position p, String t) {
		super(p, t);
	}

	@Override
	public void popMoves(List<Board.Square> board) {
        /*
        First make list of valid positions based on piece(pawn).
        plist[0] represents the position 1 forward and [1]
        represents the position 2 forward
        */
        Position plist[] = { null, null };
        for (Position p : Position.values()) {
            if (team.equals("black")) {
                if (p.coordEquals(current.x, current.y - 1)) plist[0] = p;
                if (firstMove) {
                    if (p.coordEquals(current.x, current.y - 2)) plist[1] = p;
                }
            } else if (team.equals("white")) {
                if (p.coordEquals(current.x, current.y + 1)) plist[0] = p;
                if (firstMove) {
                    if (p.coordEquals(current.x, current.y - 2)) plist[1] = p;
                }
            }
        }
        // Next remove invalid positions based on current state of board
        for (Board.Square s : board) {
            if (s.position == plist[0] && s.piece == null) validMoves.add(plist[0]);
            break;
        }
        if (firstMove) {
            for (Board.Square s : board) {
                if (s.position == plist[1] && s.piece == null) validMoves.add(plist[1]);
                break;
            }
        }
    }

	@Override
	public void setCurrent(Position p) {
	    firstMove = false;
	}

	public String toString() {
		String str = null;
		if (team.equalsIgnoreCase("white")) str = "wp";
		else str = "bp";
		return str;
	}

}
