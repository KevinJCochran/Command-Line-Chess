package chess;


import java.util.Scanner;

public class Chess {
    public static void main(String[] argv) {
        Board board = new Board();
        System.out.println(board);

        Scanner stdin = new Scanner(System.in);
        System.out.print("White's move: ");
        String sFrom = stdin.next();
        String sTo = stdin.next();

        Position from = toPosition(sFrom);
        Position to = toPosition(sTo);

        System.out.println("From: "+from+", To: "+to);
    }
    public static void drawBoardStart() {
        System.out.println("bR bN bB bQ bK bB bN bR 8"); // 1
        System.out.println("bp bp bp bp bp bp bp bp 7"); // 2
        System.out.println("   ##    ##    ##    ## 6"); // 3
        System.out.println("##    ##    ##    ##    5"); // 4
        System.out.println("   ##    ##    ##    ## 4"); // 5
        System.out.println("##    ##    ##    ##    3"); // 6
        System.out.println("wp wp wp wp wp wp wp wp 2"); // 7
        System.out.println("wR wN wB wQ wK wB wN wR 1"); // 8
        System.out.println(" a  b  c  d  e  f  g  h  "); // 9
    }

    public static Position toPosition(String s) {
        for (Position p : Position.values()) {
            if (s.equals(p.getValue())) return p;
        }
        return null;
    }
}
