package pl.commit.gen.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.commit.translate.TranslateCommiting;

import static org.assertj.core.api.Assertions.assertThat;
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
    void testGenerateTranslateCommitValidType() {
        // given
        String major = "link/1.0.0";
        String type = "feat";
        String component = "UI";
        String changeDescription = "Add new button";
        String details = "Added a new button to the main page.";

        // when
        when(translateCommiting.translate(changeDescription, "EN")).thenReturn("Add new button");
        when(translateCommiting.translate(details, "EN")).thenReturn("Added a new button to the main page.");

        String commitMessage = commitService.generateTranslateCommit(major, type, component, changeDescription, details, commitTranslateRequest.wholeGitCommand());

        // then
        assertNotNull(commitMessage);
        assertTrue(commitMessage.contains("feat"));
        assertTrue(commitMessage.contains("UI"));
        assertTrue(commitMessage.contains("Add new button"));
        assertTrue(commitMessage.contains("Added a new button to the main page."));
    }

    @Test
    void testGenerateTranslateCommitInvalidType() {
        // given
        String type = "invalidType";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            commitService.generateTranslateCommit(null, type, "UI", "Description", "Details", commitTranslateRequest.wholeGitCommand());
        });

        // then
        assertEquals("Invalid commit type: invalidType", exception.getMessage());
    }

    @Test
    void testGenerateTranslateCommitEmptyDetails() {
        // given
        String major = "link/1.0.0";
        String type = "fix";
        String component = "Backend";
        String changeDescription = "Fix bug in payment module";
        String details = "";

        // when
        when(translateCommiting.translate(changeDescription, "EN")).thenReturn("Fix bug in payment module");

        String commitMessage = commitService.generateTranslateCommit(major, type, component, changeDescription, details, commitTranslateRequest.wholeGitCommand());

        // then
        assertNotNull(commitMessage);
        assertFalse(commitMessage.contains("details"));
    }

    @Test
    void testGenerateTranslateCommitWithTaskNumber() {
        // given
        String major = "link/TEET-1234";
        String type = "feat";
        String component = "UI";
        String changeDescription = "Add new feature";
        String details = "Details of the task";

        // when
        when(translateCommiting.translate(changeDescription, "EN")).thenReturn("Add new feature");
        when(translateCommiting.translate(details, "EN")).thenReturn("Details of the task");


        String commitMessage = commitService.generateTranslateCommit(major, type, component, changeDescription, details, commitTranslateRequest.wholeGitCommand());

        // then
        assertNotNull(commitMessage);
        assertTrue(commitMessage.contains("TEET-1234"));
    }

    @Test
    void testGenerateFlowCommitWithTaskNumber() {
        // given
        String major = "link/TEET-1234";
        String type = "fix";
        String component = "Report";
        String changeDescription = "Add new feature";
        String details = "";
        boolean wholeGitCommand = true;

        String commitMessage = commitService.generateFlowCommit(major, type, component, changeDescription, details, wholeGitCommand);

        // then
        assertNotNull(commitMessage);
        assertTrue(commitMessage.contains("TEET-1234"));
        assertThat(commitMessage).isEqualTo("git commit -m \"TEET-1234 fix(Report): Add new feature\"");
    }
}
