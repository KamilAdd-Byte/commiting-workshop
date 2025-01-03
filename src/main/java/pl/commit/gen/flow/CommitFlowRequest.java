package pl.commit.gen.flow;

record CommitFlowRequest(
        String major,
        String type,
        String component,
        String changeDescription,
        String details,
        boolean wholeGitCommand
) {}

