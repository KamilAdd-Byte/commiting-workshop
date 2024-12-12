package pl.commit.gen.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.commit.translate.TranslateCommiting;

@Service
@RequiredArgsConstructor
public class CommitService {
    public static final String TARGET_LANG = "EN";
    private static final String GIT_COMMAND = "git commit -m";
    public static final String COMMITING_WORK_PATTERN = "%s \"%s %s(%s): %s\n\n%s\"";
    private final TranslateCommiting translateCommiting;

    public String generateCommit(String major, String type, String component, String changeDescription, String details) {
        if (!isValidType(type)) {
            throw new IllegalArgumentException("Invalid commit type: " + type);
        }
        MajorNumber majorNumber = MajorNumberPreparer.of(major).getMajorNumber();
        String changeDescriptionTranslated = getChangeDescriptionTranslated(changeDescription);
        String detailsTranslated = !details.isEmpty() ? getChangeDescriptionTranslated(details) : "";
        return String.format(
                COMMITING_WORK_PATTERN,
                GIT_COMMAND,
                majorNumber != null ? majorNumber.issueNumber() : "",
                type,
                component,
                changeDescriptionTranslated,
                detailsTranslated.isEmpty() ? "" : detailsTranslated
        ).trim();
    }

    private String getChangeDescriptionTranslated(String changeDescription) {
        return translateCommiting.translate(changeDescription, TARGET_LANG);
    }

    private boolean isValidType(String type) {
        return CommitType.isValidType(type);
    }
}
