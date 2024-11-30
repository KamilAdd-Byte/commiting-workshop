package pl.commit.translate;

import org.springframework.stereotype.Service;

@Service
public interface TranslateCommiting {
    String translate(String text, String targetLang);
}
