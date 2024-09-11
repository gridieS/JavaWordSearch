import java.util.*;

public class WordSearch extends Grid {
    private final static int MINIMUM_SIZE = 3;
    public List<List<String>> wordGrid;
    public int x;
    public int y;

    public WordSearch(List<List<String>> wordGrid) {
        this.wordGrid = wordGrid;
        if (this.wordGrid.size() < MINIMUM_SIZE || this.wordGrid.get(0).size() < MINIMUM_SIZE) {
            System.out.println(String.format("Invalid grid size, grid must be greater than %d",MINIMUM_SIZE-1));
            throw new Error();
        }
        this.x = wordGrid.size();
        this.y = wordGrid.get(0).size();
        System.out.println(String.format("%s,%i,%i",Arrays.toString(wordGrid.toArray()),x,y));
    }
    public WordSearch(String str, int x, int y) {
    }
    protected HashMap<String,int[][]> solve() {

        return new HashMap<String,int[][]>();
    }
}

