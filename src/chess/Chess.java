package chess;


import java.util.Scanner;

public class Chess {
    public static void main(String[] argv) {

        // Create board and initialize it
        Board board = new Board();

        // Create scanner to read from command line
        Scanner stdin = new Scanner(System.in);

        // Start loop
        while (true) {
            System.out.println(board);
            System.out.print("White's move: ");
            Position from = toPosition(stdin.next());
            Position to = toPosition(stdin.next());
            if (!board.move(from,to)) System.out.println("Invalid move");
            // TODO write main game loop
        }

    }
    public static String drawBoardStart() {
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
