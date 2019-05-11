/*
 * |-------------------------------------------------
 * | Copyright © 2019 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package partial.word.count

import kotlin.test.Test
import kotlin.test.assertEquals

class PartialWordCountTestK {

    private val partialWordCount : PartialWordCountK = PartialWordCountK()

    @Test
    fun `test read input when words are count then output word with their count` () {
        val inputFile = "src/test/resources/input.txt"

        val result = partialWordCount.partialWordCount(inputFile)

        assertEquals("A: 1\n" +
                "material: 1\n" +
                "maybe: 2\n" +
                "right: 1\n", result)
    }

}