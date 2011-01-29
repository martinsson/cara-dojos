package telldontask;

import java.util.List;

public class DecoratedKeywordProvider implements KeywordProvider {

    private final KeywordProvider keywordProvider;
    private final Filter filter;

    public DecoratedKeywordProvider(KeywordProvider keywordProvider, Filter filter) {
        this.keywordProvider = keywordProvider;
        this.filter = filter;
    }

    public List<Keyword> getKeywords() {
        List<Keyword> keywords = keywordProvider.getKeywords();
        List<Keyword> filteredKeywords = filter.filter(keywords);
        return filteredKeywords;
    }



}
