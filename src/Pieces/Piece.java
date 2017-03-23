package Pieces;

import chess.Board;
import chess.Position;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;

/**
 * Super class of all pieces with basic funtionality.
 * @author Kevin
 */
public abstract class Piece {

    public List<Position> validMoves;
    public Position current;
    public String team;

    /**
     * Create the piece.
     * @param p Where
     * @param t Team
     */
    public Piece(Position p, String t) {
        this.current = p;
        this.team = t;
        validMoves = new ArrayList<>();

    }

    /**
     * Populate moves of pawn in validMoves list.
     * @param board board to use.
     */
    public abstract void popMoves(List<Board.Square> board);
    /**
     * Set new position and sets firstMove to false.
     * @param p Where to move.
     */
    public abstract void setCurrent(Position p);

    /**
     * Set temp position for testing.
     * @param p where
     */
    public void tempCurrent(Position p) {
        this.current = p;
    }

    /**
     * true if valid
     * @param p positon
     * @param board board
     * @return true if valid
     */
    public boolean isValid(Position p, List<Board.Square> board) {
        popMoves(board);
        return validMoves.contains(p);
    }

    /**
     * Determine if this piece is attacking the passed position.
     * @param p Position to check
     * @return true if attacking
     */
    public boolean isAttacking(Position p) {
        if (this.validMoves.contains(p))
            return true;
        else
            return false;
    }

    /**
     * Provides help with adding
     */
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

    /**
     * Just toString
     */
    public String toString() {
        return "??";
    }
}
