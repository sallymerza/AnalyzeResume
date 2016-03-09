package questions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * @author Sally Al
 *         1. create a map of words and their count from a file
 *         2. rank words based on frequency
 *         3. remove special chars from words in a file
 **/
public class AnalyzeCV {
    private Map<String, Integer> map = new LinkedHashMap<String, Integer>();
    private List<String> ignoreList = new ArrayList<>();

    public static void main (String[] args) {
        AnalyzeCV c = new AnalyzeCV();
        c.createIgnoreList();
        c.readFile();
        c.sortMapByValue();
        c.printMap();

    }

    private void sortMapByValue () {
        List<Entry<String, Integer>> lst = new LinkedList<>(map.entrySet());
        Collections.sort(lst, new Comparator<Entry<String, Integer>>() {
            @Override
            public int compare (Entry<String, Integer> o1, Entry<String, Integer> o2) {
                return -(o1.getValue() - o2.getValue());
            }
        });

        map.clear();
        for (Entry<String, Integer> entry : lst) {
            map.put(entry.getKey(), entry.getValue());
        }

    }

    private void printMap () {
        for (String s : map.keySet()) {
            System.out.println(s + ": " + map.get(s));
        }
    }

    private void readFile () {
        try {
            File file =
                    new File("resume.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split(" ");
                for (String word : words) {
                    word = word.trim();
                    if(ignoreList.contains(word))continue;
                    if (map.containsKey(word)) {
                        map.put(word, map.get(word) + 1);
                    }
                    else {
                        map.put(word, 1);
                    }
                }
            }

        }
        catch (Exception e) {
            System.out.println(e);
        }

    }

    private void createIgnoreList () {
        ignoreList.add("the");
        ignoreList.add("to");
        ignoreList.add("for");
        ignoreList.add("with");
        ignoreList.add("and");
        ignoreList.add("of");
        ignoreList.add("in");
        ignoreList.add("(");
        ignoreList.add(")");
        ignoreList.add("{");
        ignoreList.add("}");
        ignoreList.add("[");
        ignoreList.add("]");
        ignoreList.add("a");
        ignoreList.add("on");
        ignoreList.add("as");
        ignoreList.add("within");
        ignoreList.add("up");

    }

}
