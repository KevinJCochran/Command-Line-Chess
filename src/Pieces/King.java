package Pieces;

import chess.Board;
import chess.Position;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class King extends Piece{

    private boolean firstMove = true;

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
                    if (!s.piece.team.equals(this.team) && s.piece.isAttacking(plist[i])) {
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
        // Handle castling move
        Rook leftRook = null, rightRook = null;
        boolean canCastle = true;
        if (firstMove) {
            // Find Rooks
            for (Board.Square s : board) {
                if (s.piece != null) {
                    if (s.piece instanceof Rook && s.position.coordEquals(1,current.y)) {
                        leftRook = (Rook)s.piece;
                    }
                    if (s.piece instanceof Rook && s.position.coordEquals(8,current.y)) {
                        rightRook = (Rook)s.piece;
                    }
                }
            }
            if (leftRook.firstMove) {
                // Check if positions are free
                for (Board.Square s : board) {
                    if (s.position.coordEquals(2,current.y) && s.piece != null) {
                        canCastle = false;
                    }
                    if (s.position.coordEquals(3,current.y) && s.piece != null) {
                        canCastle = false;
                    }
                    if (s.position.coordEquals(4,current.y) && s.piece != null) {
                        canCastle = false;
                    }
                }
                // Check if positions are under attack
                for (Board.Square s : board) {
                    if (s.piece != null) {
                        if (this.team.equals("white") && s.piece.team.equals("black")) {
                            if (s.piece.isAttacking(Position.C1) || s.piece.isAttacking(Position.D1)) {
                                canCastle = false;
                            }
                        } else if (this.team.equals("black") && s.piece.team.equals("white")){
                            if (s.piece.isAttacking(Position.C8) || s.piece.isAttacking(Position.D8)) {
                                canCastle = false;
                            }
                        }
                    }
                }
            }
            // If still true, then castle possible
            if (canCastle) {
                if (this.team.equals("white")) {
                    validMoves.add(Position.C1);
                } else {
                    validMoves.add(Position.C8);
                }
            }
            // Now check right rook
            canCastle = true;
            if (rightRook.firstMove) {
                // Check if positions are free
                for (Board.Square s : board) {
                    if (s.position.coordEquals(6,current.y) && s.piece != null) {
                        canCastle = false;
                    }
                    if (s.position.coordEquals(7,current.y) && s.piece != null) {
                        canCastle = false;
                    }
                }
                // Check if positions are under attack
                for (Board.Square s : board) {
                    if (s.piece != null) {
                        if (this.team.equals("white") && s.piece.team.equals("black")) {
                            if (s.piece.isAttacking(Position.F1) || s.piece.isAttacking(Position.G1)) {
                                canCastle = false;
                            }
                        } else if (this.team.equals("black") && s.piece.team.equals("white")){
                            if (s.piece.isAttacking(Position.F8) || s.piece.isAttacking(Position.G8)) {
                                canCastle = false;
                            }
                        }
                    }
                }
            }
            // If still true, then castling possible
            if (canCastle) {
                if (this.team.equals("white")) {
                    validMoves.add(Position.G1);
                } else {
                    validMoves.add(Position.G8);
                }
            }
        }
	}

	public void setCurrent(Position p, List<Board.Square> board) {
	    if (this.team.equals("white") && firstMove) {
	        // if castling king side
	        if (p == Position.G1) {
                Board.Square s1 = null ,s2 = null;
                for (Board.Square s : board) {
                    if (s.position == Position.H1) s1 = s;
                    if (s.position == Position.F1) s2 = s;
                }
                s2.piece = s1.piece;
                s1.piece = null;
                s2.piece.setCurrent(Position.F1);
                s2.piece.popMoves(board);
            }
            // if castling Queen side
            if (p == Position.C1) {
                Board.Square s1 = null ,s2 = null;
                for (Board.Square s : board) {
                    if (s.position == Position.A1) s1 = s;
                    if (s.position == Position.D1) s2 = s;
                }
                s2.piece = s1.piece;
                s1.piece = null;
                s2.piece.setCurrent(Position.D1);
                s2.piece.popMoves(board);
            }
        } else if (this.team.equals("black") && firstMove){
            // if castling king side
            if (p == Position.G8) {
                Board.Square s1 = null ,s2 = null;
                for (Board.Square s : board) {
                    if (s.position == Position.H8) s1 = s;
                    if (s.position == Position.F8) s2 = s;
                }
                s2.piece = s1.piece;
                s1.piece = null;
                s2.piece.setCurrent(Position.F8);
                s2.piece.popMoves(board);
            }
            // if castling Queen side
            if (p == Position.C8) {
                Board.Square s1 = null ,s2 = null;
                for (Board.Square s : board) {
                    if (s.position == Position.A8) s1 = s;
                    if (s.position == Position.D8) s2 = s;
                }
                s2.piece = s1.piece;
                s1.piece = null;
                s2.piece.setCurrent(Position.D8);
                s2.piece.popMoves(board);
            }
        }
		this.current = p;
		firstMove = false;
	}

    public boolean inCheck(List<Board.Square> board) {
        for (Board.Square s : board) {
            if (s.piece != null && !(s.piece instanceof King)) {
                s.piece.popMoves(board);
                if (s.piece.isAttacking(current)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean inCheckmate(List<Board.Square> board, Board b) {
	    // First check if king can move to a valid position
	    boolean kingCanMove = true;
	    boolean canBeSaved = true;
	    this.popMoves(board);
        kingCanMove = !validMoves.isEmpty();
        // Next check if a piece can move and save the king.
        // The king is saved if moving another piece causes the
        // king to no longer be in check.
        for (Board.Square s : board) {
            if (s.piece != null && s.piece.team.equals(this.team)) {
                s.piece.popMoves(board);
                ArrayList<Position> validListCopy = new ArrayList<>(s.piece.validMoves);
                for (Position p : validListCopy) {
                    if (p != null) {
                        b.testMove(p, s.piece);
                        if (this.inCheck(board)) {
                            b.revert(p);
                            canBeSaved = false;
                        } else {
                            b.revert(p);
                            canBeSaved = true;
                            b.validInCheckMoves.add(new Board.ChessMove(s.position,p));
                        }
                    }
                }
            }
        }
        if (kingCanMove || canBeSaved) {
	        return false;
        } else {
	        return true;
        }
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
