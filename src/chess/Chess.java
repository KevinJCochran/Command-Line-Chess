package chess;


import java.util.Scanner;

/**
 * Main class that is run. controls the turns, prompts the user, and handles input.
 *
 * @author Kevin Cochran
 */
public class Chess {
    /**
     * Main function that is run initially.
     * @param argv NO ARGS
     */
    public static void main(String[] argv) {

        // Create board and initialize it
        Board board = new Board();

        // Create scanner to read from command line
        Scanner stdin = new Scanner(System.in);

        // Start loop
        System.out.println(board);
        String arg, first;
        boolean invalidMove, draw = false;
        while (true) {
            invalidMove = true;
            while (invalidMove) {
                System.out.print("White's move: ");
                first = stdin.next();
                if (first.equals("resign")) {
                    return;
                }if (first.equals("draw?")) {
                    first = stdin.next();
                }
                Position from = toPosition(first);
                if (from == null) {
                    System.out.println("\nDraw\n");
                    return;
                }
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
                first = stdin.next();
                if (first.equals("resign")) {
                    return;
                }if (first.equals("draw?")) {
                    first = stdin.next();
                }
                Position from = toPosition(first);
                if (from == null) return;
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

    /**
     * Will take a string and convert it to position if available.
     * @param s String to convert.
     * @return Position if possible.
     */
    public static Position toPosition(String s) {
        for (Position p : Position.values()) {
            if (s.equals(p.getValue())) return p;
        }
        return null;
    }
}
