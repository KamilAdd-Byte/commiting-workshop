package pl.commit.gen.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.commit.gen.pattern.CommitModelPattern;
import pl.commit.translate.TranslateCommiting;

@Service
@RequiredArgsConstructor
public class CommitService {
    private final TranslateCommiting translateCommiting;

    public String generateCommit(String major, String type, String component, String changeDescription, String details) {
        if (!isValidType(type)) {
            throw new IllegalArgumentException("Invalid commit type: " + type);
        }
        MajorNumber majorNumber = MajorNumberPreparer.of(major).getMajorNumber();
        String changeDescriptionTranslated = getChangeDescriptionTranslated(changeDescription);
        String detailsTranslated = !details.isEmpty() ? getChangeDescriptionTranslated(details) : "";
        return String.format(
                CommitModelPattern.getCommittingWorkPattern(),
                CommitModelPattern.getGitCommandPattern(),
                majorNumber != null ? majorNumber.issueNumber() : "",
                type,
                component,
                changeDescriptionTranslated,
                detailsTranslated.isEmpty() ? "" : detailsTranslated
        ).trim();
    }

    private String getChangeDescriptionTranslated(String changeDescription) {
        return translateCommiting.translate(changeDescription, CommitModelPattern.getTargetLanguage());
    }

    private boolean isValidType(String type) {
        return CommitType.isValidType(type);
    }
}
