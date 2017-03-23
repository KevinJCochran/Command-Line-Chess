package chess;


import java.util.Scanner;

public class Chess {
    public static void main(String[] argv) {

        // TODO write main game loop
        // TODO only allow valid moves while in check

        // Create board and initialize it
        Board board = new Board();

        // Create scanner to read from command line
        Scanner stdin = new Scanner(System.in);

        // Start loop
        System.out.println(board);
        boolean invalidMove;
        while (true) {
            invalidMove = true;
            while (invalidMove) {
                System.out.print("White's move: ");
                Position from = toPosition(stdin.next());
                Position to = toPosition(stdin.next());
                if (!board.move(from,to)) {
                    if (board.checkmate) {
                        System.out.println("Checkmate\nWhite wins");
                        return;
                    } else if (board.blackInCheck) {
                        System.out.println(board);
                        System.out.println("Check\n");
                        invalidMove = false;
                    } else
                        System.out.println("Illegal move, try again");
                } else {
                    invalidMove = false;
                    System.out.println(board);
                }
            }
            invalidMove = true;
            while (invalidMove) {
                System.out.print("Black's move: ");
                Position from = toPosition(stdin.next());
                Position to = toPosition(stdin.next());
                if (!board.move(from,to)) {
                    if (board.checkmate) {
                        System.out.println("Checkmate\nBlack wins");
                        return;
                    } else if (board.whiteInCheck) {
                        System.out.println(board);
                        System.out.println("Check\n");
                        invalidMove = false;
                    } else
                        System.out.println("Illegal move, try again");
                } else {
                    invalidMove = false;
                    System.out.println(board);
                }
            }
        }

    }
    public static String drawBoardStart() {
        // TODO check that board is correctly drawn
        String str =
        "bR bN bB bQ bK bB bN bR 8\n"+
        "bp bp bp bp bp bp bp bp 7\n"+
        "   ##    ##    ##    ## 6\n"+
        "##    ##    ##    ##    5\n"+
        "   ##    ##    ##    ## 4\n"+
        "##    ##    ##    ##    3\n"+
        "wp wp wp wp wp wp wp wp 2\n"+
        "wR wN wB wQ wK wB wN wR 1\n"+
        " a  b  c  d  e  f  g  h  \n";
        return str;
    }

    public static Position toPosition(String s) {
        for (Position p : Position.values()) {
            if (s.equals(p.getValue())) return p;
        }
        return null;
    }
}
