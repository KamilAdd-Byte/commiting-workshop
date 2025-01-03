package pl.commit.gen.controller;

public record CommitTranslateRequest(
        String major,
        String type,
        String component,
        String changeDescription,
        String details,
        boolean wholeGitCommand
) {}
