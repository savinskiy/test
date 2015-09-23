/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncedu.java.tasks.wordcounter;

import java.io.PrintStream;
import java.util.*;

/**
 *
 * @author DimaZ
 */
public class MyWordCounter implements WordCounter {

    private String text;

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Map<String, Long> getWordCounts() {
        text = text.replaceAll("[.,:;-]", "");
        Long current = 0l;
        Map<String, Long> result = new HashMap<String, Long>();
        StringTokenizer st = new StringTokenizer(text, "\t\n\r ");
        while (st.hasMoreTokens()) {
            String word = st.nextToken().toLowerCase();
            if (result.containsKey(word)) {
                current = result.get(word);
            } else {
                current = 0l;
            }
            result.put(word, ++current);
        }
        return result;
    }

    @Override
    public List<Map.Entry<String, Long>> getWordCountsSorted() {
        return sortWordCounts(getWordCounts());
    }

    @Override
    public List<Map.Entry<String, Long>> sortWordCounts(Map<String, Long> orig) {
        ArrayList<Map.Entry<String, Long>> result = new ArrayList<Map.Entry<String, Long>>();
        for (Map.Entry<String, Long> item : orig.entrySet()) {
            result.add(item);
        }
        Collections.sort(result, Collections.reverseOrder(new Comparator<Map.Entry<String, Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                if (o1.getValue() != o2.getValue()) {
                    return o1.getValue().compareTo(o2.getValue());
                } else {
                    return -o1.getKey().compareTo(o2.getKey());
                }
            }
        }));
        return result;
    }

    @Override
    public void printWordCounts(PrintStream ps) {
        Map<String, Long> words = getWordCounts();
        for (Map.Entry<String, Long> item : words.entrySet()) {
            ps.println(item.getKey() + " " + item.getValue());
        }
    }

    @Override
    public void printWordCountsSorted(PrintStream ps) {
        List<Map.Entry<String, Long>> wordsSorted = getWordCountsSorted();
        for (Map.Entry<String, Long> item : wordsSorted) {
            ps.println(item.getKey() + " " + item.getValue());
        }
    }
}
