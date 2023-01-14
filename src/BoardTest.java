import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    Board test = new Board(5, 2, 1);

    @Test
    void getBoardState() {
        char[][] testDSP = new char[][] {{'x',' ',' ',' ',' ',}, {'x','x',' ',' ',' '}, {'x','.','x',' ',' '},
                {'x','x','x','x',' '}, {'x','x','x','x','x'}};
        for(int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (testDSP[row][col] != test.getBoardState()[row][col]) {
                    fail();
                }
            }
        }
    }

    @Test
    void displayBoard() {
    }

    @Test
    void validMove() {
        char[][] testVAL1 = new char[][] {{'x',' ',' ',' ',' ',}, {'x','x',' ',' ',' '}, {'x','x','.',' ',' '},
                {'x','x','x','x',' '}, {'x','x','x','x','x'}};
        char[][] testVAL2 = new char[][] {{'x',' ',' ',' ',' ',}, {'x','x',' ',' ',' '}, {'x','.','x',' ',' '},
                {'x','x','x','x',' '}, {'x','x','x','x','x'}};
    }

    @Test
    void makeMove() {
        char[][] testMOV = new char[][] {{'x',' ',' ',' ',' ',}, {'x','x',' ',' ',' '}, {'x','x','x',' ',' '},
                {'x','.','x','x',' '}, {'x','.','x','x','x'}};
        Board move = test.makeMove(4, 1, 2, 1);
        for(int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (testMOV[row][col] != move.getBoardState()[row][col]) {
                    fail();
                }
            }
        }

    }

    @Test
    void isEnd() {
        char[][] testEND = new char[][] {{'.',' ',' ',' ',' ',}, {'.','.',' ',' ',' '}, {'.','.','.',' ',' '},
                {'.','.','.','.',' '}, {'.','.','.','.','.'}};
        char[][] testNotEND = new char[][] {{'x',' ',' ',' ',' ',}, {'.','.',' ',' ',' '}, {'.','.','.',' ',' '},
                {'.','.','.','.',' '}, {'.','.','.','.','.'}};
        Board end = new Board(testEND);
        Board notEnd = new Board(testNotEND);
        if (!end.isEnd()){
            fail();
        }
        if (notEnd.isEnd()){
            fail();
        }
    }

    @Test
    void getNeighbors() {
    }

    @Test
    void testEquals() {
        Board eq1 = new Board(5, 1, 1);
        Board eq2 = new Board(5, 1, 1);
        Board neq = new Board(5, 2, 1);
        if (!(eq1.equals(eq2)) || eq1.equals(neq)){
            fail();
        }
    }

    @Test
    void compareTo() {
        char[][] moreCT = new char[][] {{'.',' ',' ',' ',' ',}, {'.','.',' ',' ',' '}, {'.','.','.',' ',' '},
                {'.','.','.','.',' '}, {'.','.','.','.','.'}};
        char[][] lessCT = new char[][] {{'x',' ',' ',' ',' ',}, {'.','.',' ',' ',' '}, {'.','.','.',' ',' '},
                {'.','.','.','.',' '}, {'.','.','.','.','.'}};
        Board less = new Board(lessCT);
        Board more = new Board(moreCT);
        if (more.compareTo(less) <= 0){
            fail();
        }
        if (more.compareTo(more) != 0){
            fail();
        }
        if (less.compareTo(more) >= 0){
            fail();
        }
    }
}