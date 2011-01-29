package telldontask;

import java.io.IOException;
import java.util.List;

import telldontask.Filter.NoFilter;

public class KeywordReporter {

    private final ReportPublisher publisher = new ReportPublisher();
    private final DecoratedKeywordProvider decoratedKwdProvider;
    public KeywordReporter(KeywordProvider keywordProvider) {
        this(keywordProvider, new NoFilter());
    }

    public KeywordReporter(KeywordProvider keywordProvider, Filter publishedKeywords) {
        decoratedKwdProvider = new DecoratedKeywordProvider(keywordProvider, publishedKeywords);
    }

    public void report() throws IOException {
        List<Keyword> filteredKeywords = decoratedKwdProvider.getKeywords(this);
        publisher.printReport(filteredKeywords);
    }

}