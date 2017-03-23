package Pieces;

import chess.Board;
import chess.Position;

import java.util.List;

/**
 * Knight class
 * @author Kevin
 */
public class Knight extends Piece{

    /**
     * Create the piece.
     * @param p Where
     * @param t Team
     */
    public Knight(Position p, String t) {
        super(p, t);
    }

    /**
     * Populate moves of pawn in validMoves list.
     * @param board board to use.
     */
    @Override
    public void popMoves(List<Board.Square> board) {
        validMoves.clear();
        /*
        There are 8 possible moves for the knight:
        [0]: x+1, y+2   [4]: x-1, y-2
        [1]: x+2, y+1   [5]: x-2, y-1
        [2]: x+2, y-1   [6]: x-2, y+1
        [3]: x+1, y-2   [7]: x-1, y+2
         */
        Position plist[] = {null, null, null, null, null, null, null, null};
        for (Position p : Position.values()) {
            if (p.coordEquals(current.x+1, current.y+2))
                plist[0] = p;
            if (p.coordEquals(current.x+2, current.y+1))
                plist[1] = p;
            if (p.coordEquals(current.x+2, current.y-1))
                plist[2] = p;
            if (p.coordEquals(current.x+1, current.y-2))
                plist[3] = p;
            if (p.coordEquals(current.x-1, current.y-2))
                plist[4] = p;
            if (p.coordEquals(current.x-2, current.y-1))
                plist[5] = p;
            if (p.coordEquals(current.x-2, current.y+1))
                plist[6] = p;
            if (p.coordEquals(current.x-1, current.y+2))
                plist[7] = p;
        }
        // Fill list with valid moves
        for (Board.Square s : board) {
            for (Position p : plist) {
                if (s.piece == null) {
                    if (s.position == p)
                        validMoves.add(p);
                }else if (s.position == p && s.piece.team != this.team)
                    validMoves.add(p);
            }
        }
    }

    /**
     * Set new position and sets firstMove to false.
     * @param p Where to move.
     */
    @Override
    public void setCurrent(Position p) {
        this.current = p;
    }

    /**
     * Create string rep of piece.
     * @return String
     */
    public String toString() {
        String str = null;
        if (team.equalsIgnoreCase("white")) str = "wN";
        else str = "bN";
        return str;
    }
}
