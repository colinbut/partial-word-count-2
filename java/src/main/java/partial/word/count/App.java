/*
 * |-------------------------------------------------
 * | Copyright © 2019 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package partial.word.count;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class App {

    private List<String> readInFromInputFile(String fileName) {
        List<String> words = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName)))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                words.addAll(Arrays.asList(line.split(" ")));
            }

        } catch (IOException ex) {
            System.err.print(ex.getLocalizedMessage());
        }
        return words;
    }

    String countWords(String fileName) {
        List<String> words = readInFromInputFile(fileName);
        List<String> filteredWords = removePartialMatches(words);

        Map<String, Integer> wordCount = new LinkedHashMap<>();
        filteredWords.forEach(word -> wordCount.merge(word, 1, (a, b) -> a + b));

        StringJoiner stringJoiner = new StringJoiner("\n", "", "");
        wordCount.forEach((word, count) -> stringJoiner.add(word + ": " + count));

        return stringJoiner.toString();
    }

    List<String> removePartialMatches(List<String> unfilteredWords) {
        List<String> filteredWords = new ArrayList<>(unfilteredWords);
        List<String> partialMatches = new ArrayList<>();
        for (String word : unfilteredWords) {
            for (String wordToCompareAgainst : unfilteredWords) {

                if (wordToCompareAgainst.equals(word)) {
                    continue;
                }

                if (wordToCompareAgainst.contains(word)) {
                    partialMatches.add(word);
                    break;
                }
            }
        }
        filteredWords.removeAll(partialMatches);
        return filteredWords;
    }
}
