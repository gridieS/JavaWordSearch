import java.util.*;

public class WordSearch {
    private final static int MINIMUM_SIZE = 3;
    public CharGrid charGrid;
    public HashMap<String,int[][]> latestSolution;
    public int x;
    public int y;

    public WordSearch(CharGrid charGrid) {
        this.charGrid = charGrid;
        if (this.charGrid.size() < MINIMUM_SIZE || this.charGrid.get(0).size() < MINIMUM_SIZE) {
            System.out.println(String.format("Invalid grid size, grid must be greater than %d",MINIMUM_SIZE-1));
            throw new Error();
        }
        this.x = charGrid.size();
        this.y = charGrid.get(0).size();
    }
    public WordSearch(String str, int x, int y) {
        this(new CharGrid(str,x,y));
    }
    public HashMap<String,int[][]> solve(List<String> wordList) {

        this.latestSolution = new HashMap<String,int[][]>();
        return latestSolution;
    }
}

