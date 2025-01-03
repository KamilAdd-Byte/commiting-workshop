package pl.commit.gen.controller;

import org.springframework.web.bind.annotation.*;
import pl.commit.gen.service.CommitService;

@RestController
@RequestMapping("/api/commit-translate")
public class CommitTranslateController {

    private final CommitService commitService;

    public CommitTranslateController(CommitService commitService) {
        this.commitService = commitService;
    }

    @PostMapping("/generate")
    public String generateCommit(@RequestBody CommitTranslateRequest commitTranslateRequest) {
        return commitService.generateTranslateCommit(
                commitTranslateRequest.major(),
                commitTranslateRequest.type(),
                commitTranslateRequest.component(),
                commitTranslateRequest.changeDescription(),
                commitTranslateRequest.details(),
                commitTranslateRequest.wholeGitCommand()
        );
    }
}
