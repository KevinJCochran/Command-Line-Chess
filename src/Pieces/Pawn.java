package Pieces;

import chess.Board;
import chess.Position;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private boolean firstMove;

	public Pawn(Position p, String t) {
		super(p, t);
		firstMove = true;
	}

	@Override
	public void popMoves(List<Board.Square> board) {
	    // TODO handle En passant move
	    validMoves.clear();
        /*
        First make list of valid positions based on piece(pawn).
        plist[0] represents the position 1 forward and [1]
        represents the position 2 forward. [2] and [3] represent
        attack left and right, respectively.
        */
        Position plist[] = { null, null, null, null};
        for (Position p : Position.values()) {
            if (team.equals("black")) {
                if (p.coordEquals(current.x, current.y - 1)) plist[0] = p; // Forward
                if (p.coordEquals(current.x - 1, current.y - 1)) plist[3] = p; // Attack right
                if (p.coordEquals(current.x + 1, current.y - 1)) plist[2] = p; // Attack left
                if (firstMove) {
                    if (p.coordEquals(current.x, current.y - 2)) plist[1] = p; // Double forward
                }
            } else if (team.equals("white")) {
                if (p.coordEquals(current.x, current.y + 1)) plist[0] = p; // Forward
                if (p.coordEquals(current.x + 1, current.y + 1)) plist[3] = p; // Attack right
                if (p.coordEquals(current.x - 1, current.y + 1)) plist[2] = p; // Attack left
                if (firstMove) {
                    if (p.coordEquals(current.x, current.y + 2)) plist[1] = p; // Double forward
                }
            }
        }
        // Next remove invalid positions based on current state of board
        for (Board.Square s : board) {
            if (s.position == plist[0] && s.piece == null) { // Can move forward
                validMoves.add(plist[0]);
            }
            if (s.position == plist[2] && s.piece != null) { // Can attack left
                if (!s.piece.team.equals(this.team)) {
                    validMoves.add(plist[2]);
                }
            }
            if (s.position == plist[3] && s.piece != null) { // Can attack right
                if (!s.piece.team.equals(this.team)) {
                    validMoves.add(plist[3]);
                }
            }
        }
        if (firstMove) {
            for (Board.Square s : board) {
                if (s.position == plist[1] && s.piece == null) { // Can double move
                    validMoves.add(plist[1]);
                }
            }
        }
    }

	@Override
	public void setCurrent(Position p) {
	    firstMove = false;
	    current = p;
	}

	@Override
	public boolean isAttacking(Position p) {
	    if (p == null) return false;
	    if (this.team.equals("white")) {
            if (p.coordEquals(current.x + 1, current.y + 1))
                return true;
            if (p.coordEquals(current.x - 1, current.y + 1))
                return true;
        } else {
            if (p.coordEquals(current.x+1,current.y-1))
                return true;
            if (p.coordEquals(current.x-1,current.y-1))
                return true;
        }
        return false;
    }

	public String toString() {
		String str = null;
		if (team.equalsIgnoreCase("white")) str = "wp";
		else str = "bp";
		return str;
	}

}
