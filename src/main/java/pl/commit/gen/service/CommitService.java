package pl.commit.gen.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.commit.gen.pattern.CommitModelPattern;
import pl.commit.translate.TranslateCommiting;

@Service
@RequiredArgsConstructor
public class CommitService {
    private final TranslateCommiting translateCommiting;

    public String generateTranslateCommit(String major, String type, String component, String changeDescription, String details, boolean wholeGitCommand) {
        if (isValidType(type)) {
            throw new IllegalArgumentException("Invalid commit type: " + type);
        }

        MajorNumber majorNumber = MajorNumberPreparer.of(major).getMajorNumber();
        String changeDescriptionTranslated = getChangeDescriptionTranslated(changeDescription);
        String detailsTranslated = !details.isEmpty() ? getChangeDescriptionTranslated(details) : "";
        String pattern = CommitModelPattern.getPattern(wholeGitCommand, component, detailsTranslated);

        return String.format(
                pattern,
                majorNumber != null ? majorNumber.issueNumber() : "",
                type,
                component.isEmpty() ? changeDescriptionTranslated : component,
                changeDescriptionTranslated,
                detailsTranslated
        ).trim();
    }

    public String generateFlowCommit(String major, String type, String component, String changeDescription, String details, boolean wholeGitCommand) {
        if (isValidType(type)) {
            throw new IllegalArgumentException("Invalid commit type: " + type);
        }

        MajorNumber majorNumber = MajorNumberPreparer.of(major).getMajorNumber();
        String detailsFlow = details.isEmpty() ? StringUtils.trimAllWhitespace(details) : details;
        String pattern = CommitModelPattern.getPattern(wholeGitCommand, component, detailsFlow);

        return String.format(
                pattern,
                majorNumber != null ? majorNumber.issueNumber() : "",
                type,
                component,
                changeDescription,
                detailsFlow
        ).trim();
    }

    private String getChangeDescriptionTranslated(String changeDescription) {
        return translateCommiting.translate(changeDescription, CommitModelPattern.getTargetLanguage());
    }

    private boolean isValidType(String type) {
        return !CommitType.isValidType(type);
    }
}
