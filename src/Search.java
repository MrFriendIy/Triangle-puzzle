import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedMap;

public class Search {
    private Board board;
    public Search(Board board){
        this.board = board;
    }

    public HashMap<Board, Board> DFS(){
        ArrayList<Board> visited = new ArrayList();
        ArrayList<Board> queue = new ArrayList<>();
        HashMap<Board, Board> boardMap = new HashMap<>();
        queue.add(board);
        while (!queue.isEmpty()){
            Board current = queue.remove(0);
            visited.add(current);
            ArrayList<Board> neighbors = current.getNeighbors();
            for (Board b : neighbors){
                if (!visited.contains(b)){
                    queue.add(b);
                    boardMap.put(current, b);

                }
            }
        }
    return boardMap;
    }

}
