package Pieces;

import chess.Board;
import chess.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

    protected List<Position> validMoves;
    protected Position current;
    protected String team;

    public Piece(Position p, String t) {
        this.current = p;
        this.team = t;
        validMoves = new ArrayList<>();

    }
    public abstract void popMoves(List<Board.Square> board);
    public abstract void setCurrent(Position p);

    public boolean isValid(Position p, List<Board.Square> board) {
        popMoves(board);
        return validMoves.contains(p);
    }

    protected void addValid(List<Board.Square> board, Position list[]) {
        Board.Square temp = null;
        if (list[0] != null) {
            for (int i = 0; i < 7; i++) {
                for (Board.Square s : board) {
                    if (s.position == list[i]) temp = s;
                }
                if (temp == null) return; // Ensure that a square was found
                if (temp.piece == null) {
                    validMoves.add(list[i]);
                } else if (!temp.piece.team.equals(this.team)) {
                    validMoves.add(list[i]);
                    break;
                } else {
                    break;
                }
            }
        }
    }

    public String toString() {
        return "??";
    }
}
