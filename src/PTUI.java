import java.lang.Math;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class PTUI {

    private final int base;
    private char[][] board;

    private final int startRow;
    private final int starCol;


    public PTUI(int base, int startRow, int starCol){
        this.starCol = starCol;
        this.startRow = startRow;
        this.base = base;
        this.board = new char[base][base];
    }

    public void CreateBoard(){
        for (int row = 0; row < base; row++){
            for (int col = 0; col < base; col++){
                if (row == startRow && col == starCol){
                    board[row][col] = '.';
                }
                else {
                    if (row - col < 0){
                        board[row][col] = ' ';
                    }
                    else {
                        board[row][col] = 'x';
                    }
                }
            }
        }
    }

    public char[][] getBoard() {
        return board;
    }
    public void setBoard(char[][] newBoard){
        this.board = newBoard;
    }
    public void displayBoard(char[][] board){
        for (int row = 0; row < base; row++) {
            System.out.print(row + " ");
            for (int col = 0; col < base; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println("");
        }
        System.out.print("  ");
        for (int i = 0; i < base; i++){
            System.out.print(i + " ");
        }
        System.out.println("");
    }
    public boolean validMove(char[][] board, int initRow, int initCol, int endRow, int endCol){
        int overRow = (initRow + endRow) / 2;
        int overCol = (initCol + endCol) / 2;
        if (initRow < 0 || initCol < 0 || initRow >= base || initCol >= base || endRow < 0 || endCol < 0 || endRow >= base || endCol >= base){
            //System.out.println("out of bounds");
            return false;
        }
        if (board[overRow][overCol] != 'x'){
            //System.out.println("must jump over a piece");
            return false;
        }
        if (board[initRow][initCol] != 'x'){
            //System.out.println("must move a piece");
            return false;
        }
        if (board[endRow][endCol] != '.'){
            //System.out.println("must land on an empty square");
            return false;
        }
        return true;
    }
    public char[][] makeMove(char[][] board, int initRow, int initCol, int endRow, int endCol) {
        char[][] newBoard = new char[base][base];
        for (int row = 0; row < base; row++) {
            for (int col = 0; col < base; col++) {
                newBoard[row][col] = board[row][col];
            }
        }
        int overRow = (initRow + endRow) / 2;
        int overCol = (initCol + endCol) / 2;
        newBoard[initRow][initCol] = '.';
        newBoard[overRow][overCol] = '.';
        newBoard[endRow][endCol] = 'x';
        return newBoard;
    }

    public boolean isEnd(char[][] board){
        for (int row = 0; row < base; row++) {
            for (int col = 0; col < base; col++) {
                if (board[row][col] == 'x') {
                    return false;
                }
            }
        }
        return true;
    }

    public ArrayList<char[][]> getNeighbors(char[][] board){
        ArrayList<char[][]> neighbors = new ArrayList<>();
        for (int row = 0; row < base; row++) {
            for (int col = 0; col < base; col++) {
                if (validMove(board, row, col, row+2, col)){
                   neighbors.add(makeMove(board, row, col, row+2, col));
                }
                if (validMove(board, row, col, row-2, col)){
                    neighbors.add(makeMove(board, row, col, row-2, col));
                }
                if (validMove(board, row, col, row, col+2)){
                    neighbors.add(makeMove(board, row, col, row, col+2));
                }
                if (validMove(board, row, col, row, col-2)){
                    neighbors.add(makeMove(board, row, col, row, col-2));
                }
                if (validMove(board, row, col, row+2, col+2)){
                    neighbors.add(makeMove(board, row, col, row+2, col+2));
                }
                if (validMove(board, row, col, row-2, col-2)){
                    neighbors.add(makeMove(board, row, col, row-2, col-2));
                }
                if (validMove(board, row, col, row-2, col+2)){
                    neighbors.add(makeMove(board, row, col, row-2, col+2));
                }
                if (validMove(board, row, col, row+2, col-2)){
                    neighbors.add(makeMove(board, row, col, row+2, col-2));
                }
            }
        }

        return neighbors;
    }
    public HashMap<char[][], char[][]> search(char[][] board){
        HashMap<char[][], char[][]> visited = new HashMap<>();
        ArrayList<char[][]> queue = new ArrayList<>();

        queue.add(board);
        while (!queue.isEmpty()){
            char[][] current = queue.remove(0);
            if (isEnd(current)){
                System.out.println("found end");
            }
            if (!visited.containsKey(current)){
                for (char[][] c : getNeighbors(current)){
                    visited.put(current, c);
                    queue.add(c);
                }
            }
        }
        return visited;
    }

}
