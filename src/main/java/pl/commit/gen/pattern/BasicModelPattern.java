package pl.commit.gen.pattern;

sealed class BasicModelPattern permits CommitModelPattern {
    private static final String TARGET_LANG = "EN";

    public static String getTargetLanguage() {
        return TARGET_LANG;
    }
}
