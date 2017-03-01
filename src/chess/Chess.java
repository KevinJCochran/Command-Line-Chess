package chess;


public class Chess {
    public static void main(String[] argv) {
        drawBoardStart();
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

        hiFarah();
    }

    public static void hiFarah() {
        System.out.println("Hi Farah!");
    }
}
