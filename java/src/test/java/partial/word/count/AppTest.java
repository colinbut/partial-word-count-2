/*
 * |-------------------------------------------------
 * | Copyright © 2019 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package partial.word.count;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AppTest {

    private App app = new App();

    @Test
    public void testReadInput_whenWordsAreCount_thenOutputWordWithTheirCount() {
        String file = "src/test/resources/input.txt";

        String result = app.run(file);

        assertEquals("A: 1\n" +
            "material: 1\n" +
            "maybe: 2\n" +
            "right: 1", result);
    }

    @Test
    public void testPartialWordMatchesShouldBeRemovedFromWordsList() {
        List<String> unfilteredWords = Arrays.asList("A", "mate", "material", "may", "maybe", "right", "maybe");

        List<String> result = app.removePartialMatches(unfilteredWords);

        assertEquals(Arrays.asList("A", "material", "maybe", "right", "maybe"), result);
    }

}
