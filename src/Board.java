import java.util.ArrayList;

public class Board implements Comparable<Board>{
    private int base;
    private char[][] boardState;
    public Board(int base, int startRow, int startCol){
        this.base = base;
        this.boardState = new char[base][base];
        for (int row = 0; row < base; row++){
            for (int col = 0; col < base; col++){
                if (row == startRow && col == startCol){
                    boardState[row][col] = '.';
                }
                else {
                    if (row - col < 0){
                        boardState[row][col] = ' ';
                    }
                    else {
                        boardState[row][col] = 'x';
                    }
                }
            }
        }
    }

    public Board(char[][] boardState){
        this.boardState = boardState;
        this.base = boardState[0].length;
    }
    public char[][] getBoardState(){
        return boardState;
    }
    public void displayBoard(){
        for (int row = 0; row < base; row++) {
            System.out.print(row + " ");
            for (int col = 0; col < base; col++) {
                System.out.print(boardState[row][col] + " ");
            }
            System.out.println("");
        }
        System.out.print("  ");
        for (int i = 0; i < base; i++){
            System.out.print(i + " ");
        }
        System.out.println("");
    }
    /* TODO
    implement some sort of message system. I want it to give the errors when you manually try and make a move, but
    not when it is run thousands of times for the search algorithm.
     */
    public boolean validMove(int initRow, int initCol, int endRow, int endCol){
        int overRow = (initRow + endRow) / 2;
        int overCol = (initCol + endCol) / 2;
        if (initRow < 0 || initCol < 0 || initRow >= base || initCol >= base || endRow < 0 || endCol < 0 || endRow >= base || endCol >= base){
            //System.out.println("out of bounds");
            return false;
        }
        if (boardState[overRow][overCol] != 'x'){
            //System.out.println("must jump over a piece");
            return false;
        }
        if (boardState[initRow][initCol] != 'x'){
            //System.out.println("must move a piece");
            return false;
        }
        if (boardState[endRow][endCol] != '.'){
            //System.out.println("must land on an empty square");
            return false;
        }
        return true;
    }

    public Board makeMove(int initRow, int initCol, int endRow, int endCol) {
        Board newBoard = new Board(boardState);
        int overRow = (initRow + endRow) / 2;
        int overCol = (initCol + endCol) / 2;
        newBoard.boardState[initRow][initCol] = '.';
        newBoard.boardState[overRow][overCol] = '.';
        newBoard.boardState[endRow][endCol] = 'x';
        return newBoard;
    }

    public boolean isEnd(){
        for (int row = 0; row < base; row++) {
            for (int col = 0; col < base; col++) {
                if (boardState[row][col] == 'x') {
                    return false;
                }
            }
        }
        return true;
    }

    public ArrayList<Board> getNeighbors(){
        ArrayList<Board> neighbors = new ArrayList<>();
        for (int row = 0; row < base; row++) {
            for (int col = 0; col < base; col++) {
                if (validMove(row, col, row+2, col)){
                    neighbors.add(makeMove(row, col, row+2, col));
                }
                if (validMove(row, col, row-2, col)){
                    neighbors.add(makeMove(row, col, row-2, col));
                }
                if (validMove(row, col, row, col+2)){
                    neighbors.add(makeMove(row, col, row, col+2));
                }
                if (validMove(row, col, row, col-2)){
                    neighbors.add(makeMove(row, col, row, col-2));
                }
                if (validMove(row, col, row+2, col+2)){
                    neighbors.add(makeMove(row, col, row+2, col+2));
                }
                if (validMove(row, col, row-2, col-2)){
                    neighbors.add(makeMove(row, col, row-2, col-2));
                }
                if (validMove(row, col, row-2, col+2)){
                    neighbors.add(makeMove(row, col, row-2, col+2));
                }
                if (validMove(row, col, row+2, col-2)){
                    neighbors.add(makeMove(row, col, row+2, col-2));
                }
            }
        }
        return neighbors;
    }


    @Override
    public boolean equals(Object o){
        if (! (o instanceof  Board)){
            return false;
        }
        else {
            for (int row = 0; row < base; row++){
                for (int col = 0; col < base; col++){
                    if (boardState[row][col] != ((Board) o).getBoardState()[row][col]){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    @Override
    public int hashCode(){
        String state = "";
        for (int row = 0; row < base; row++) {
            for (int col = 0; col < base; col++) {
                state = state + boardState[row][col];
            }
        }
        return state.hashCode();
    }

    @Override
    public int compareTo(Board o) {
        int myVal = 0;
        int otherVal = 0;
        for (int row = 0; row < base; row++){
            for (int col = 0; col < base; col++){
                if (boardState[row][col] == '.'){
                    myVal++;
                }
                if (o.boardState[row][col] == '.'){
                    otherVal++;
                }
            }
        }
        return myVal - otherVal;
    }
}
