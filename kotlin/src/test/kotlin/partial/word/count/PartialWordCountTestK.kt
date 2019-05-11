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
import kotlin.test.Test
import kotlin.test.assertEquals

class PartialWordCountTestK {

    @Test
    fun `test read input when words are count then output word with their count` () {
        val inputFile = "src/test/resources/input.txt"

        val result = partialWordCount(inputFile)

        assertEquals("A: 1\n" +
                "material: 1\n" +
                "maybe: 2\n" +
                "right: 1", result)
    }

    private fun partialWordCount(inputFile: String): String {
        val words = BufferedReader(FileReader(File(inputFile))).lines()
        val wordCount : MutableMap<String, Int> = HashMap()

        words.forEach {
            if (wordCount[it] != null) {
                wordCount[it] = wordCount[it]!!.plus(1)
            } else {
                wordCount[it] = 1
            }
        }

        val resultString = StringBuilder()
        wordCount.entries.forEach {
            resultString.append("${it.key}: ${it.value}\n")
        }

        return resultString.toString()
    }

}