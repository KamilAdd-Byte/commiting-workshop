package pl.commit.gen.pattern;

sealed class BasicModelPattern permits CommitModelPattern, QuickModelPattern {
    private static final String TARGET_LANG = "EN";

    public static String getTargetLanguage() {
        return TARGET_LANG;
    }
}
