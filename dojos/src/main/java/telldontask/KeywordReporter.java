package telldontask;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import telldontask.Filter.NoFilter;

public class KeywordReporter {

    private static final String REPORT_FILE = "report.csv";
    private final KeywordProvider keywordProvider;
    private final Filter filter;

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
        printReport(filteredKeywords);
    }

    private void printReport(List<Keyword> filteredKeywords) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(REPORT_FILE)));
        writeHeader(writer);
        writeLines(writer, filteredKeywords);
        writer.close();
    }

    private void writeLines(PrintWriter writer, List<Keyword> filteredKeywords) {
        for (Keyword keyword : filteredKeywords) {
            writer.println(keyword.getSearchTerm() +"; " + keyword.isPublished());
        }
    }

    private void writeHeader(PrintWriter writer) {
        writer.println("Search term; published");
    }

}