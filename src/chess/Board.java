package chess;

import Pieces.*;

public class Board {

    private Node head;

    private class Node {
        private Position position;
        private Piece piece;
        private Node next;

        private Node(Position p, Piece piece, Node next) {
            this.position = p;
            this.piece = piece;
            this.next = next;
        }
    }

    public Board() {

        Node temp = null;
        head = new Node(Position.A1, new Rook(Position.A1,"white"), null);
        for (Position p : Position.values()) {
            switch (p) {
                case A1:
                    break; // Taken care of in initialization of head

                // Place white pawns
                case A2: case B2: case C2: case D2: case E2: case F2: case G2: case H2:
                    temp = new Node(p, new Pawn(p,"white"), head.next);
                    head = temp;
                    break;

                // Place black pawns
                case A7: case B7: case C7: case D7: case E7: case F7: case G7: case H7:
                    temp = new Node(p, new Pawn(p,"black"), head.next);
                    head = temp;
                    break;

                // Place white rook
                case H1:
                    new Node(p, new Rook(p,"white"), head.next);
                    head = temp;
                    break;

                // Place black rooks
                case A8: case H8:
                    new Node(p, new Rook(p,"black"), head.next);
                    head = temp;
                    break;

                // Place white knights
                case B1: case G1:
                    new Node(p, new Knight(p,"white"), head.next);
                    head = temp;
                    break;

                // Place black knights
                case B8: case G8:
                    new Node(p, new Knight(p,"black"), head.next);
                    head = temp;
                    break;

                // Place white bishops
                case C1: case F1:
                    new Node(p, new Bishop(p,"white"), head.next);
                    head = temp;
                    break;

                // Place black bishops
                case C8: case F8:
                    new Node(p, new Bishop(p,"black"), head.next);
                    head = temp;
                    break;

                // Place white queen
                case D1:
                    new Node(p, new Queen(p,"white"), head.next);
                    head = temp;
                    break;

                // Place black queen
                case D8:
                    new Node(p, new Queen(p,"black"), head.next);
                    head = temp;
                    break;

                // Place white king
                case E1:
                    new Node(p, new King(p,"white"), head.next);
                    head = temp;
                    break;

                // Place black king
                case E8:
                    new Node(p, new King(p,"black"), head.next);
                    head = temp;
                    break;

                // Empty spots
                default:
                    temp = new Node(p,null, head.next);
                    head = temp;
                    break;
            }
        }
    }

    // TODO write method to print current state of board
}
