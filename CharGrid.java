import java.util.*;

public class CharGrid extends ArrayList<List<Character>> {
    public final int x;
    public final int y;

    private void emptyGrid(int x, int y) {
        this.clear();
        for (int i = 0; i < x; i++) {
            this.add(new ArrayList<Character>());
        }
    }

    public CharGrid(String str,int x, int y) {
        if (x*y != str.length()) {
            System.out.println("Error: string length does not match x times y.");
            throw new Error();
        }
        this.emptyGrid(x, y);
        for (int i = 0;i < str.length();i++) {
            this.get(i % x).add(str.charAt(i));
        }
        this.x = x;
        this.y = y;
    }
}
