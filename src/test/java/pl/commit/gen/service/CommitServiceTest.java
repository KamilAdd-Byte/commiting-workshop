package pl.commit.gen.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.commit.translate.TranslateCommiting;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CommitServiceTest {

    @Mock
    private TranslateCommiting translateCommiting;

    @InjectMocks
    private CommitService commitService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateCommitValidType() {
        // given
        String major = "link/1.0.0";
        String type = "feat";
        String component = "UI";
        String changeDescription = "Add new button";
        String details = "Added a new button to the main page.";

        // when
        when(translateCommiting.translate(changeDescription, "EN")).thenReturn("Add new button");
        when(translateCommiting.translate(details, "EN")).thenReturn("Added a new button to the main page.");

        String commitMessage = commitService.generateCommit(major, type, component, changeDescription, details);

        // then
        assertNotNull(commitMessage);
        assertTrue(commitMessage.contains("feat"));
        assertTrue(commitMessage.contains("UI"));
        assertTrue(commitMessage.contains("Add new button"));
        assertTrue(commitMessage.contains("Added a new button to the main page."));
    }

    @Test
    void testGenerateCommitInvalidType() {
        // given
        String type = "invalidType";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            commitService.generateCommit(null, type, "UI", "Description", "Details");
        });

        // then
        assertEquals("Invalid commit type: invalidType", exception.getMessage());
    }

    @Test
    void testGenerateCommitEmptyDetails() {
        // given
        String major = "link/1.0.0";
        String type = "fix";
        String component = "Backend";
        String changeDescription = "Fix bug in payment module";
        String details = "";

        // when
        when(translateCommiting.translate(changeDescription, "EN")).thenReturn("Fix bug in payment module");

        String commitMessage = commitService.generateCommit(major, type, component, changeDescription, details);

        // then
        assertNotNull(commitMessage);
        assertFalse(commitMessage.contains("details"));
    }

    @Test
    void testGenerateCommitWithTaskNumber() {
        // given
        String major = "link/TEET-1234";
        String type = "feat";
        String component = "UI";
        String changeDescription = "Add new feature";
        String details = "Details of the task";

        // when
        when(translateCommiting.translate(changeDescription, "EN")).thenReturn("Add new feature");
        when(translateCommiting.translate(details, "EN")).thenReturn("Details of the task");


        String commitMessage = commitService.generateCommit(major, type, component, changeDescription, details);

        // then
        assertNotNull(commitMessage);
        assertTrue(commitMessage.contains("TEET-1234"));
    }
}
