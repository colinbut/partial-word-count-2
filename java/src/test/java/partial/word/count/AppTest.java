package partial.word.count;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {

    private App app = new App();

    @Test
    public void testReadInput_whenWordsAreCount_thenOutputWordWithTheirCount() {
        String file = "src/test/resources/input.txt";

        String result = app.countWords(file);

        assertEquals("A: 1\n" +
            "mate: 1\n" +
            "material: 1\n" +
            "may: 1\n" +
            "maybe: 2\n" +
            "right: 1", result);
    }

    @Test
    public void testPartialWordMatchesShouldBeRemovedFromCount() {
        String file = "src/test/resources/input.txt";

        String result = app.countWords(file);

        assertEquals(
            "material: 1\n" +
            "maybe: 2\n" +
            "right: 1", result);
    }

}
