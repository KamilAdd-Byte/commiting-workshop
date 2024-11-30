package pl.commit.gen.controller;

public record CommitRequest(
        String major,
        String type,
        String component,
        String changeDescription,
        String details
) {}
