package telldontask;

import java.io.IOException;
import java.util.List;

public class KeywordReporter {

    private final ReportPublisher publisher = new ReportPublisher();
    private final KeywordProvider decoratedKwdProvider;
    
    public KeywordReporter(KeywordProvider decoratedKeywordProvider) {
        this.decoratedKwdProvider = decoratedKeywordProvider;
    }

    public void report() throws IOException {
        List<Keyword> filteredKeywords = decoratedKwdProvider.getKeywords();
        publisher.printReport(filteredKeywords);
    }

}