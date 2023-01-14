import java.util.*;

public class Main {

    public static void main(String[] args) {
        Board game = new Board(5, 2, 1);
        game.displayBoard();
        Scanner input = new Scanner(System.in);
        char[][] completedBoardChar = new char[5][5];
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (row - col < 0){
                    completedBoardChar[row][col] = ' ';
                }
                else {
                    completedBoardChar[row][col] = '.';
                }
            }
        }
        Board completedBoard = new Board(completedBoardChar);
        completedBoard.displayBoard();
        /**
        System.out.println("input the start and end coordinates you want to move, separated with spaces");
        String strLocation = input.nextLine();
        String[] arrLocation = strLocation.split("\\s+");
        int[] intArrLocation = new int[4];
        for (int i = 0; i < 4; i++){
            intArrLocation[i] = Integer.parseInt(arrLocation[i]);
        }
        if (game.validMove(game.getBoard(), intArrLocation[0], intArrLocation[1], intArrLocation[2], intArrLocation[3])){
            game.setBoard(game.makeMove(game.getBoard(), intArrLocation[0], intArrLocation[1], intArrLocation[2], intArrLocation[3]));
        }
        else {
            System.out.println("invalid move");
        }
        game.displayBoard();
         **/


        Search search = new Search(game);
        HashMap<Board, Board> values = search.DFS();
        System.out.println(values.size());
    }
}