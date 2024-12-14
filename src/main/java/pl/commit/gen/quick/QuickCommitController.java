package pl.commit.gen.quick;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quick-commit")
@RequiredArgsConstructor
public class QuickCommitController {

    final QuickCommitService quickCommitService;

    @PostMapping("/generate")
    public String generateCommit(@RequestBody QuickCommitRequest quickCommitRequest) {
        //todo
        return "";
    }
}
