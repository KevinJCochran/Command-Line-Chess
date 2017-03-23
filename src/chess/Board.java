package chess;

import Pieces.*;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Maintains the game board with all its pieces.
 * @author Kevin
 */
public class Board {

    private ArrayList<Square> board;
    public ArrayList<ChessMove> validInCheckMoves;
    public boolean whiteInCheck = false;
    public boolean blackInCheck = false;
    public boolean checkmate = false;
    public Piece savedPiece;
    public String turn;

    /**
     * Class to help populate list of valid moves if in check
     * @author Kevin
     */
    public static class ChessMove {
        public Position from;
        public Position to;

        public ChessMove(Position from, Position to) {
            this.from = from;
            this.to = to;
        }
    }

    /**
     * Inner class representing a square on the chess board.
     * Each square has a position, piece, and string representing the color of square when blank.
     * @author Kevin
     */
    public class Square {
        public Position position;
        public Piece piece;
        public String blank;

        /**
         * Square constructor.
         * @param p Position of square
         * @param piece Piece on square.
         */
        private Square(Position p, Piece piece) {
            this.position = p;
            this.piece = piece;
        }

        /**
         * Standard toString. Prints blank if piece is null.
         * @return String
         */
        @Override
        public String toString() {
            String str;
            if (piece == null) {
                str = blank;
            }
            else str = piece.toString();
            return str;
        }
    }

    /**
     * Builds the board and initializes all squares.
     */
    public Board() {
        board = new ArrayList<>();
        validInCheckMoves = new ArrayList<>();
        for (Position p : Position.values()) {
            switch (p) {
                // Place white pawns
                case A2: case B2: case C2: case D2: case E2: case F2: case G2: case H2:
                    board.add(new Square(p, new Pawn(p,"white")));
                    break;

                // Place black pawns
                case A7: case B7: case C7: case D7: case E7: case F7: case G7: case H7:
                    board.add(new Square(p, new Pawn(p,"black")));
                    break;

                // Place white rook
                case A1: case H1:
                    board.add(new Square(p, new Rook(p,"white")));
                    break;

                // Place black rooks
                case A8: case H8:
                    board.add(new Square(p, new Rook(p,"black")));
                    break;

                // Place white knights
                case B1: case G1:
                    board.add(new Square(p, new Knight(p,"white")));
                    break;

                // Place black knights
                case B8: case G8:
                    board.add(new Square(p, new Knight(p,"black")));
                    break;

                // Place white bishops
                case C1: case F1:
                    board.add(new Square(p, new Bishop(p,"white")));
                    break;

                // Place black bishops
                case C8: case F8:
                    board.add(new Square(p, new Bishop(p,"black")));
                    break;

                // Place white queen
                case D1:
                    board.add(new Square(p, new Queen(p,"white")));
                    break;

                // Place black queen
                case D8:
                    board.add(new Square(p, new Queen(p,"black")));
                    break;

                // Place white king
                case E1:
                    board.add(new Square(p, new King(p,"white")));
                    break;

                // Place black king
                case E8:
                    board.add(new Square(p, new King(p,"black")));
                    break;

                // Empty spots
                default:
                    board.add(new Square(p,null));
                    break;
            }
        }
        // Sort the board in order of enum
        board.sort(Comparator.comparing(s -> s.position));

        // Set color that each square will be when blank
        int i = 0;
        int j = 1;
        for (Square s : board) {
            if (i % 2 == 0) s.blank = "  ";
            else s.blank = "##";
            if (j % 8 != 0) i++;
            j++;
        }

        // Populate the valid moves of all pieces
        for (Square s : board) {
            if (s.piece != null) s.piece.popMoves(board);
        }
        turn = "white";
    }

    /**
     * Move piece at p1 to p2.
     * @param p1 from
     * @param p2 to
     * @return true if valid, false if invalid, check, checkmate
     */
    public boolean move(Position p1, Position p2) {
        King king = null;
        // Find p1 and p2 squares
        Square s1 = null, s2 = null;
        for (Square s : board) {
            if (s.position == p1) s1 = s;
            if (s.position == p2) s2 = s;
        }
        if (s1.piece == null) return false;
        if (!turn.equals(s1.piece.team)) return false;
        // Determine if p2 is on list on valid moves for p1 piece
        if (s1.piece.isValid(p2, board)) {
            s2.piece = s1.piece;
            s1.piece = null;

            // ----- Hacky way of making King class work ------
            if (s2.piece instanceof King) {
                king = (King) (s2.piece);
                king.setCurrent(p2, board);
            } else
                s2.piece.setCurrent(p2);
            // ------------------------------------------------

            s2.piece.popMoves(board);
            // Now determine if board in check/checkmate and set flags accordingly
            King whiteKing = null;
            King blackKing = null;
            for (Square s : board) {
                if (s.piece != null) {
                    if (s.piece instanceof King && s.piece.team.equals("white")) {
                        whiteKing = (King)s.piece;
                    } else if (s.piece instanceof King && s.piece.team.equals("black")) {
                        blackKing = (King)s.piece;
                    }
                }
            }
            if (whiteKing != null && blackKing != null) {
                whiteInCheck = whiteKing.inCheck(board);
                blackInCheck = blackKing.inCheck(board);
                checkmate = (whiteKing.inCheckmate(board,this) || blackKing.inCheckmate(board,this));
            }
            if (whiteInCheck || blackInCheck || checkmate) {
                if (turn.equals("white")) {
                    turn = "black";
                }
                else if (turn.equals("black")) {
                    turn = "white";
                }
                return false;
            }
        } else {
            return false;
        }
        if (turn.equals("white")) {
            turn = "black";
        }
        else if (turn.equals("black")) {
            turn = "white";
        }
        return true;
    }

    /**
     * Used with checkmate function to test situation if piece was at p.
     * @param p Position to place piece
     * @param piece Piece to place
     */
    public void testMove(Position p, Piece piece) {
        // Find square of p
        Square square = null;
        for (Square s : board) {
            if (s.position == p) square = s;
        }
        // Save the old piece
        savedPiece = square.piece;
        square.piece = piece;
        //square.piece.tempCurrent(p);
    }

    /**
     * Un-do what testMove did.
     * @param p where to undo.
     */
    public void revert(Position p) {
        // Find square of p
        Square square = null;
        for (Square s : board) {
            if (s.position == p) square = s;
        }
        // Revert Square piece
        square.piece = savedPiece;
        savedPiece = null;
    }

    /**
     * Prints the board in current state.
     * @return String representing board.
     */
    @Override
    public String toString() {
        String str = "\n";
        int i = 1;
        int row = 8;
        for (Square s : board) {
            str += s.toString() + " ";
            if (i % 8 == 0) {
                str += Integer.toString(row) + "\n";
                row--;
            }
            i++;
        }
        str += " a  b  c  d  e  f  g  h\n";
        return str;
    }
}
