import java.util.*;

public class Main {
    public static void main(String[] args) {
        WordSearch instance = new WordSearch("helloworldhelloworld",5,4);
        String[] tempWordList = {"world","hello","odod","dlrow","wer"};
        System.out.println(instance.solve(tempWordList));
        System.out.println(Arrays.toString(instance.solution.get("wer")));
    }
}