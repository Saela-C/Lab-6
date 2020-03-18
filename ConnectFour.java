import java.util.Scanner;

public class ConnectFour {

    public static void main(String[] args) {

        int rowSize;
        int columnSize;
        char board[][];
        boolean winState = false;
        int p1Column;
        int p2Column;
        int p1Row;
        int p2Row;
        int topRowFill;


        Scanner scnr = new Scanner(System.in);


        //Ask player for board size (rows and columns)

        System.out.println("What would you like the height of the board to be?");
        rowSize = scnr.nextInt();
        System.out.println("What would you like the length of the board to be?");
        columnSize = scnr.nextInt();

        board = new char[rowSize][columnSize];

        initializeBoard(board);

        printBoard(board);

        System.out.println("Player 1: x");
        System.out.println("Player 2: o");

        //Looping through player moves and checking

        while (winState == false) {
            System.out.print("Player 1: Which column would you like to choose?");
            p1Column = scnr.nextInt();
            p1Row = insertChip(board, p1Column, 'x');
            printBoard(board);

            if (checkIfWinner(board, p1Column, p1Row, 'x') == true) {
                System.out.println("Player 1 won the game!");
                break;
            }

            //If every entry on the top row is not a '-' and nobody's won yet, it's a draw.
            topRowFill = 0;
            for (int j = 0; j < columnSize; j++) {
                if (board[rowSize - 1][j] != '-')
                    topRowFill += 1;
            }
            if (topRowFill == rowSize){
                System.out.println("Draw. Nobody wins.");
                break;
            }

            System.out.print("Player 2: Which column would you like to choose?");
            p2Column = scnr.nextInt();
            p2Row = insertChip(board, p2Column, 'o');
            printBoard(board);
            if (checkIfWinner(board, p2Column, p2Row, 'o') == true) {
                System.out.println("Player 2 won the game!");
                break;
            }

            //If every entry on the top row is not a '-' and nobody's won yet, it's a draw.
            topRowFill = 0; 
            for (int j = 0; j < columnSize; j++) {
                if (board[rowSize - 1][j] != '-')
                    topRowFill += 1;
            }
            if (topRowFill == rowSize){
                System.out.println("Draw. Nobody wins.");
                break;
            }

        }
    }

    public static void initializeBoard(char[][] board) {

        for (int i = 0; i < board.length; i++) {

            for (int j = 0; j < board[i].length; j++)
                board[i][j] = '-';

        }

    }

    public static void printBoard(char[][] board) {

        for (int i = board.length - 1; i >= 0; i--) {

            for (int j = 0; j < board[i].length; j++) {

                System.out.print(board[i][j]);
                System.out.print(' ');
            }
            System.out.print('\n');

        }
    }

    public static int insertChip(char[][] board, int col, char chipType) {

        //Looping through each entry, and making each entry '-'

        int row = 0;
        for (int i = 0; i < board.length; i++) {

            if (board[i][col] == '-') {
                board[i][col] = chipType;
                row = i;
                break;
            }

        }

        return row;

    }

    public static boolean checkIfWinner(char[][] board, int col, int row, char chipType) {

        //Checking if winner by ROW by going down that row's column

        int chipCount = 0;

        for (int i = row; i >= 0; i--){
            if (board[i][col] == chipType)
                chipCount++;
            else
                break;
        }

        if (chipCount >= 4)
            return true;

        //Checking if winner by COL by going left as much as possible, then right as much as possible

        chipCount = 0;

        for (int jLeft = col; jLeft >= 0; jLeft--) {
            if (board[row][jLeft] == chipType)
                chipCount++;
            else
                break;
        }
        for (int jRight = col + 1; jRight <= board[row].length - 1; jRight++ ){
            if (board[row][jRight] == chipType)
                chipCount++;
        }

        if (chipCount >= 4)
            return true;
        else
            return false;


    }
}



