package pl.commit.gen.controller;

import org.springframework.web.bind.annotation.*;
import pl.commit.gen.service.CommitService;

@RestController
@RequestMapping("/api/commit")
public class CommitController {

    private final CommitService commitService;

    public CommitController(CommitService commitService) {
        this.commitService = commitService;
    }

    @PostMapping("/generate")
    public String generateCommit(@RequestBody CommitRequest commitRequest) {
        return commitService.generateCommit(
                commitRequest.major(),
                commitRequest.type(),
                commitRequest.component(),
                commitRequest.changeDescription(),
                commitRequest.details()
        );
    }
}
