package Pieces;

import chess.Board;
import chess.Position;

import java.util.List;

public class King extends Piece{

	public King(Position p, String t) {
		super(p, t);
	}

	@Override
	public void popMoves(List<Board.Square> board) {
	    validMoves.clear();
		/*
		The king can move in 8 directions but only one square at a time.
		Also, the king cannot move to a square that is being attacked. Therefore,
		it is necessary to check all eight positions
		 */
        Position[] plist = new Position[8];
        // Add surrounding positions to list
        for (Position p : Position.values()) {
            if (p.coordEquals(current.x+1,current.y+1))
                plist[0] = p;
            if (p.coordEquals(current.x+1,current.y))
                plist[1] = p;
            if (p.coordEquals(current.x+1,current.y-1))
                plist[2] = p;
            if (p.coordEquals(current.x,current.y-1))
                plist[3] = p;
            if (p.coordEquals(current.x-1,current.y-1))
                plist[4] = p;
            if (p.coordEquals(current.x-1,current.y))
                plist[5] = p;
            if (p.coordEquals(current.x-1,current.y+1))
                plist[6] = p;
            if (p.coordEquals(current.x,current.y+1))
                plist[7] = p;
        }
        // remove locations that are under attack
        for (int i = 0; i < 8; i++) {
            for (Board.Square s : board) {
                if (s.piece != null) {
                    if (!s.piece.team.equals(this.team) && s.piece.validMoves.contains(plist[i])) {
                        // TODO handle pawn situation
                        plist[i] = null;
                    }
                }
            }
        }
        // Add valid moves to valid list
        for (Position p : plist) {
            for (Board.Square s : board) {
                if (s.piece == null && s.position == p) {
                    validMoves.add(p);
                }
            }
        }
	}

	@Override
	public void setCurrent(Position p) {
		this.current = p;
	}

	public String toString() {
		String str = null;
		if (team.equalsIgnoreCase("white")) str = "wK";
		else str = "bK";
		return str;
	}

	// TODO Handle castling move

}
