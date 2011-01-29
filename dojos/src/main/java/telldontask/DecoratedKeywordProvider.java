package telldontask;

import java.util.List;

public class DecoratedKeywordProvider  {

    private final KeywordProvider keywordProvider;
    private final Filter filter;

    public DecoratedKeywordProvider(KeywordProvider keywordProvider, Filter filter) {
        this.keywordProvider = keywordProvider;
        this.filter = filter;
    }

    List<Keyword> getKeywords(KeywordReporter keywordReporter) {
        List<Keyword> keywords = keywordProvider.getKeywords();
        List<Keyword> filteredKeywords = filter.filter(keywords);
        return filteredKeywords;
    }


}
