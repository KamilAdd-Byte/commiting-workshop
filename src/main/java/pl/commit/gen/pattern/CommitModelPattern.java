package pl.commit.gen.pattern;

public final class CommitModelPattern extends BasicModelPattern {
    private static final String GIT_COMMAND = "git commit -m";
    private static final String COMMITING_WORK_PATTERN = "%s \"%s %s(%s): %s\n\n%s\"";

    public static String getCommittingWorkPattern() {
        return COMMITING_WORK_PATTERN;
    }

    public static String getGitCommandPattern() {
        return GIT_COMMAND;
    }
}
