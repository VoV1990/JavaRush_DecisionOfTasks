package com.javarush.task.task22.task2209;

import java.io.*;
import java.util.*;

/* 
Составить цепочку слов
*/
public class ChainOfWords {
    public static void main(String[] args) throws IOException {
        //...
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        reader = new BufferedReader(new FileReader(fileName));
        List<String> list = new ArrayList<>();
        String[] arrayOfStrings = null;
        while (reader.ready()) {
            String[] words = reader.readLine().split(" ");
            list.addAll(Arrays.asList(words));
        }
        reader.close();
        arrayOfStrings = list.toArray(new String[list.size()]);
        StringBuilder result = getLine(arrayOfStrings);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        StringBuilder str = new StringBuilder();
        if(words.length == 1) str.append(words[0]);
        if(words.length > 1) {
            List<String> list = new ArrayList<>(Arrays.asList(words));
            Comparator<String> wordsComparator = new WordsComparator();
            list.sort(wordsComparator);
            
                for(int i = 0; i < list.size() - 1; ) {
                    int j = i + 1;
                    String lastLetter1 = list.get(i).substring(list.get(i).length() - 1);
                    String firstLetter1 = list.get(j).substring(0,1);
                    if(!lastLetter1.equalsIgnoreCase(firstLetter1)) {
                        Collections.shuffle(list);
                        list.sort(wordsComparator);
                        i = 0;
                    } else i++;
                }
            for(String s : list) str.append(s).append(" ");
        }
        return str;
    }

    public static class WordsComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            String lastLetter1 = o1.substring(o1.length() - 1);
            String firstLetter1 = o2.substring(0,1);
            String lastLetter2 = o2.substring(o2.length() - 1);
            String firstLetter2 = o1.substring(0,1);
            if(!lastLetter1.equalsIgnoreCase(firstLetter1) && lastLetter2.equalsIgnoreCase(firstLetter2))
                return 1;
            else if(!lastLetter1.equalsIgnoreCase(firstLetter1) && !lastLetter2.equalsIgnoreCase(firstLetter2))
                return 0;
            else return -1;
        }
    }
}
