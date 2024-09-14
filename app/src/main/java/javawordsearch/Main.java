package javawordsearch;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.List;
import java.util.ArrayList;

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int charPixelSize = 0;
        String gridString = "";
        List<String> wordList = new ArrayList<String>();
        int gridX = 0;
        int gridY = 0;
        JSONParser parser = new JSONParser();
        String curPath = System.getProperty("user.dir");
        if (curPath.substring(curPath.length()-3, curPath.length()).compareTo("app") != 0) {
            curPath += "/app";
        }
        try {
            JSONObject fileArray = (JSONObject) parser.parse(new FileReader(curPath+"/src/main/resources/customizations.json"));
            gridString = (String)((((JSONObject) fileArray.get("grid")).get("string")));
            gridX = (int)(long)(((JSONObject) fileArray.get("grid")).get("x"));
            gridY = (int)(long)(((JSONObject) fileArray.get("grid")).get("y"));
            charPixelSize = (int)(long)((fileArray.get("square_pixel_size")));
            Object[] tempWordList = (((JSONArray) fileArray.get("words"))).toArray();
            for (int i = 0; i < tempWordList.length; i++) {
                if (tempWordList[i] instanceof String) {
                    wordList.add((String)tempWordList[i]);
                }
            }
        }
        catch (FileNotFoundException e)   {
            e.printStackTrace();
        }
        catch (IOException e)   {
            e.printStackTrace();
        }
        catch (ParseException e)   {
            e.printStackTrace();
        }
        WordSearch instance = new WordSearchUI(charPixelSize,gridString,gridX,gridY);
        instance.solve(wordList);
    }
}