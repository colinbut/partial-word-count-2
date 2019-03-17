package partial.word.count;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {

    App app = new App();

    @Test
    public void testWordShouldBeRemovedIfPartialMatchOfAnotherWord() {
        String word = "may";
        String line = "may maybe";

        String processedLine = app.process(line, word);

        assertEquals("maybe", processedLine);
    }
}
