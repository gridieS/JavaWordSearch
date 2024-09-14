package javawordsearch;
import java.util.*;

public class WordSearch {
    private final static int MINIMUM_SIZE = 3;
    public CharGrid charGrid;
    public HashMap<String,int[][]> solution;
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

    @Override
    public String toString() {
        return this.charGrid.toString();
    }
    int[] getPositionOnDirection(int direcX, int direcY, int[] position, int steps) {
        steps -= 1;
        return new int[]{position[0] + (direcX * steps),position[1] + (direcY * steps)};
    }
    String getStringInDirection(int direcX,int direcY,int[] position) {
        int[] curPosition = position.clone();
        int maxX = this.charGrid.size();
        int maxY = this.charGrid.get(0).size();
        String result = "";
        if (direcX == 0 && direcY == 0) return "";
        while ((curPosition[0] >= 0 && curPosition[1] >= 0) && (curPosition[0] < maxX && curPosition[1] < maxY)) {
            result += this.charGrid.get(curPosition[0]).get(curPosition[1]);
            curPosition[0] += direcX;
            curPosition[1] += direcY;
        }
        return result;
    }
    public HashMap<String,int[][]> solve(List<String> wordList) {
        this.solution = new HashMap<String,int[][]>();

        for (int x = 0; x < this.charGrid.size(); x++) {
            for (int y = 0; y < this.charGrid.get(x).size(); y++) {
                int[] curPosition = new int[] {x,y};
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) { // Can also recursivly check for each curChar in that direction, but it will be O(2^n) instad of O(n)
                        if (j == 0 && i == 0) continue;
                        String curStrInDirection = getStringInDirection(i, j,curPosition);
                        for (String str : wordList) {
                            if (curStrInDirection.length() < str.length()) continue;
                            if (curStrInDirection.substring(0,str.length()).equals(str)) {
                                this.solution.put(str, new int[][] {curPosition,getPositionOnDirection(i, j, curPosition,str.length())});
                            }
                        }
                    }
                }
            }
        }

        return solution;
    }
    public HashMap<String,int[][]> solve(String[] wordList) {
        return this.solve(Arrays.asList(wordList));
    }
}

