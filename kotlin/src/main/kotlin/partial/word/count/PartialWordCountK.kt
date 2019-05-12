/*
 * |-------------------------------------------------
 * | Copyright © 2019 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package partial.word.count

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.lang.StringBuilder
import java.util.HashMap

class PartialWordCountK {

    fun partialWordCount(inputFile: String): String {
        val lines = BufferedReader(FileReader(File(inputFile))).lines()
        val words = lines.findFirst().get().split(" ") as MutableList<String>

        removePartialMatches(words)

        val wordCount : MutableMap<String, Int> = HashMap()
        words.forEach {
            if (wordCount[it] != null) {
                wordCount[it] = wordCount[it]!!.plus(1)
            } else {
                wordCount[it] = 1
            }
        }

        return buildResultOutput(wordCount)
    }

    private fun removePartialMatches(words : MutableList<String>) {
        val partialMatches : MutableList<String> = ArrayList()
        for (word in words) {
            if (words.stream().filter { w -> w != word }.anyMatch { w -> w.contains(word)}) {
                partialMatches.add(word)
            }
        }

        words.removeAll(partialMatches)
    }

    private fun buildResultOutput(wordCount : MutableMap<String, Int>) : String {
        val resultString = StringBuilder()
        wordCount.entries.forEach {
            resultString.append("${it.key}: ${it.value}\n")
        }
        return resultString.toString()
    }

}
