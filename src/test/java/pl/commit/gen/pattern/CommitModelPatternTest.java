package pl.commit.gen.pattern;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommitModelPatternTest {
    @Test
    void testGetPatternWithGitCommandWithoutComponentAndDetails() {
        String result = CommitModelPattern.getPattern(true, "", "");
        String expected = "git commit -m \"%s %s: %s\"";
        assertEquals(expected, result);
    }

    @Test
    void testGetPatternWithGitCommandWithoutComponent() {
        String result = CommitModelPattern.getPattern(true, "", "Some details");
        String expected = "git commit -m \"%s %s: %s\n\n%s\"";
        assertEquals(expected, result);
    }

    @Test
    void testGetPatternWithGitCommandWithoutDetails() {

        String result = CommitModelPattern.getPattern(true, "Component", "");
        String expected = "git commit -m \"%s %s(%s): %s\"";  // GIT_COMMAND_COMMITING_WORK_PATTERN_WITHOUT_DETAILS
        assertEquals(expected, result);
    }

    @Test
    void testGetPatternWithGitCommand() {
        String result = CommitModelPattern.getPattern(true, "Component", "Some details");
        String expected = "git commit -m \"%s %s(%s): %s\n\n%s\"";  // GIT_COMMAND_COMMITING_WORK_PATTERN
        assertEquals(expected, result);
    }

    @Test
    void testGetPatternWithoutGitCommandWithoutComponentAndDetails() {
        // Gdy oba komponent i szczegóły są puste, oczekujemy wzorca bez komponentu i szczegółów (bez git)
        String result = CommitModelPattern.getPattern(false, "", "");
        String expected = "%s %s: %s";  // COMMITING_WORK_PATTERN_WITHOUT_COMPONENT_AND_DETAILS
        assertEquals(expected, result);
    }

    @Test
    void testGetPatternWithoutGitCommandWithoutComponent() {
        String result = CommitModelPattern.getPattern(false, "", "Some details");
        String expected = "%s %s: %s\n\n%s";  // COMMITING_WORK_PATTERN_WITHOUT_COMPONENT
        assertEquals(expected, result);
    }

    @Test
    public void testGetPatternWithoutGitCommandWithoutDetails() {
        // Gdy szczegóły są puste, ale komponent nie jest, oczekujemy wzorca bez szczegółów (bez git)
        String result = CommitModelPattern.getPattern(false, "Component", "");
        String expected = "%s %s(%s): %s";  // COMMITING_WORK_PATTERN_WITHOUT_DETAILS
        assertEquals(expected, result);
    }

    @Test
    public void testGetPatternWithoutGitCommand() {
        // Gdy komponent i szczegóły nie są puste, oczekujemy pełnego wzorca (bez git)
        String result = CommitModelPattern.getPattern(false, "Component", "Some details");
        String expected = "%s %s(%s): %s\n\n%s";  // COMMITING_WORK_PATTERN
        assertEquals(expected, result);
    }
}
