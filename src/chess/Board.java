package chess;

import Pieces.*;

import java.util.ArrayList;
import java.util.Comparator;

public class Board {

    private ArrayList<Square> board;

    public class Square {
        public Position position;
        public Piece piece;
        public String blank;

        private Square(Position p, Piece piece) {
            this.position = p;
            this.piece = piece;
        }

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

    public Board() {
        board = new ArrayList<Square>();
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
    }

    public boolean move(Position p1, Position p2) {
        // Find p1 info
        Square s1 = null ,s2 = null;
        for (Square s : board) {
            if (s.position == p1) s1 = s;
            if (s.position == p2) s2 = s;
        }
        // Determine if p2 is on list on valid moves for p1 piece
        if (s1.piece.isValid(p2, board)) {
            s2.piece = s1.piece;        // move piece
            s1.piece = null;            // set old square to null
            s2.piece.setCurrent(p2);    // update piece
            s2.piece.popMoves(board);   // Populate new list of valid moves
            return true;
        }
        else return false;
    }

    public boolean isInCheck() {
        // TODO implement check
        // TODO implement checkmate
        return false;
    }

    @Override
    public String toString() {
        String str = "";
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
