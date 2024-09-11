import java.util.*;

public abstract class Grid extends ArrayList<List<String>> {
    public Grid(int x, int y) {
        if (x*y != str.length()) {
            System.out.println("Error: string length does not match x times y.");
            throw new Error();
        }
        Grid emptyGrid = Grid.emptyGrid(x, y);
        for (int i = 0;i < str.length();i++) {
            
        }
    }
    public static Grid emptyGrid(int x, int y) {
        List<List<String>> tempGrid = new ArrayList<List<String>>();
        for (int i = 0; i < x; i++) {
            tempGrid.add(new ArrayList<String>());
            for (int j = 0; j < y; j++) {
                tempGrid.get(i).add("");
            }
        }
        for (List<String> lis : tempGrid) {
            for (String str : lis) {
                System.out.println(str);
            }
        }
        return tempGrid;
    }
}
