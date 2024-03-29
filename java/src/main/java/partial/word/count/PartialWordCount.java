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

public class PartialWordCount {

    public static void main(String[] args) {
        new PartialWordCount().run(args[0]);
    }

    String run(String fileName) {
        List<String> words = readInFromInputFile(fileName);
        List<String> filteredWords = removePartialMatches(words);
        Map<String, Integer> wordCount = countWords(filteredWords);
        return formatOutputToDisplay(wordCount);
    }


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

    private Map<String, Integer> countWords(List<String> filteredWords){
        Map<String, Integer> wordCount = new LinkedHashMap<>();
        filteredWords.forEach(word -> wordCount.merge(word, 1, (a, b) -> a + b));
        return wordCount;
    }


    List<String> removePartialMatches(List<String> unfilteredWords) {
        List<String> filteredWords = new ArrayList<>(unfilteredWords);
        List<String> partialMatches = new ArrayList<>();
        for (String word : unfilteredWords) {
            if (unfilteredWords.stream()
                .filter(wordToCompareAgainst -> !wordToCompareAgainst.equals(word))
                .anyMatch(wordToCompareAgainst -> wordToCompareAgainst.contains(word))) {
                partialMatches.add(word);
            }
        }
        filteredWords.removeAll(partialMatches);
        return filteredWords;
    }

    private String formatOutputToDisplay(Map<String, Integer> wordCount) {
        StringJoiner stringJoiner = new StringJoiner("\n", "", "");
        wordCount.forEach((word, count) -> stringJoiner.add(word + ": " + count));
        return stringJoiner.toString();
    }
}
