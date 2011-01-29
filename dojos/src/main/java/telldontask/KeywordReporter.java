package telldontask;

import java.io.IOException;
import java.util.List;

import telldontask.Filter.NoFilter;

public class KeywordReporter {

    private final KeywordProvider keywordProvider;
    private final Filter filter;
    private final ReportPublisher publisher = new ReportPublisher();
    
    public KeywordReporter(KeywordProvider keywordProvider) {
        this(keywordProvider, new NoFilter());
    }

    public KeywordReporter(KeywordProvider keywordProvider, Filter publishedKeywords) {
        this.keywordProvider = keywordProvider;
        this.filter = publishedKeywords;
    }

    public void report() throws IOException {
        List<Keyword> keywords = keywordProvider.getKeywords();
        List<Keyword> filteredKeywords = filter.filter(keywords);
        publisher.printReport(filteredKeywords);
    }

}