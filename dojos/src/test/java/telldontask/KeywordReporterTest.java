package telldontask;

import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;

import ch.lambdaj.Lambda;

public class KeywordReporterTest {
    
    public static class NoFilter implements Filter {
        public List<Keyword> filter(List<Keyword> keywordList) {
            return keywordList;
        }
    }

    public interface Filter {
        List<Keyword> filter(List<Keyword> keywordList);
    }

    public static class AlreadyPublishedKeywordsRemover implements Filter {
        public List<Keyword> filter(List<Keyword> keywordList) {
            List<Keyword> remainingKeywords = Lambda.select(keywordList, having(on(Keyword.class).isPublished(), equalTo("no")));
            return remainingKeywords;
        }
    }

    @Mock
    private KeywordProvider keywordProvider;

    public static class Keyword {

        private final String searchTerm;
        private final String isPublished;

        public Keyword(String searchTerm, String isPublished) {
            this.searchTerm = searchTerm;
            this.isPublished = isPublished;
        }

        public String getSearchTerm() {
            return searchTerm;
        }

        public String isPublished() {
            return isPublished;
        }
        
        @Override
        public String toString() {
            return searchTerm;
        }

    }

    public interface KeywordProvider {

        List<Keyword> getKeywords();

    }

    public static class KeywordReporter {

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
            PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream("report.csv")));
            List<Keyword> keywords = keywordProvider.getKeywords();
            List<Keyword> filteredKeywords = filter.filter(keywords);
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
    
    @Before
    public void initBeforeTest() throws Exception {
        initMocks(this);
    }

    @Test
    public void writesAReportToDisk() throws Exception {
        when(keywordProvider.getKeywords()).thenReturn(asList(new Keyword("Canon eos 5D", "yes")));
        KeywordReporter reporter = new KeywordReporter(keywordProvider);
        reporter.report();
        
        Scanner reportFile = new Scanner(new File("report.csv"));
        String header = reportFile.nextLine();
        String firstLine = reportFile.nextLine();
        assertThat(header, equalTo("Search term; published"));
        assertThat(firstLine, notNullValue());
    }
    
    @Test
    public void usesTheDataFromTheKeywords() throws Exception {
        when(keywordProvider.getKeywords()).thenReturn(asList(new Keyword("Macbook pro", "no")));
        KeywordReporter reporter = new KeywordReporter(keywordProvider);
        reporter.report();
        
        Scanner reportFile = new Scanner(new File("report.csv"));
        reportFile.nextLine();
        String firstLine = reportFile.nextLine();
        assertThat(firstLine, equalTo("Macbook pro; no"));
    }
    @Test
    public void handlesSeveralKeywords() throws Exception {
        List<Keyword> keywordList = asList(
                new Keyword("Macbook pro", "no"),
                new Keyword("Ipod Nano red", "yes"),
                new Keyword("Samsung LCD TV", "yes"));
        when(keywordProvider.getKeywords()).thenReturn(keywordList);
        KeywordReporter reporter = new KeywordReporter(keywordProvider);
        reporter.report();
        
        List<String> reportContent = getReportContent();
        assertThat(reportContent, hasItems(
                "Macbook pro; no",
                "Samsung LCD TV; yes",
                "Ipod Nano red; yes"));
    }
    
    @Test
    public void itFiltersTheKeywords() throws Exception {
        Filter publishedKeywords = new AlreadyPublishedKeywordsRemover();
        KeywordReporter reporter = new KeywordReporter(keywordProvider, publishedKeywords);
        List<Keyword> keywordList = asList(
                new Keyword("Macbook pro", "no"),
                new Keyword("Ipod Nano red", "no"),
                new Keyword("Samsung LCD TV", "yes"));
        when(keywordProvider.getKeywords()).thenReturn(keywordList);
        reporter.report();
    
        List<String> reportContent = getReportContent();
        assertThat(reportContent, contains(
                "Search term; published",
                "Macbook pro; no",
                "Ipod Nano red; no"));
        
    }
    
    @Test
    public void removesKeywordsThatArePublished() throws Exception {
        Filter filter = new AlreadyPublishedKeywordsRemover();
        Keyword kwdToRemain = new Keyword("toto", "no");
        Keyword kwdToBeFiltered = new Keyword("rototo", "yes");
        List<Keyword> keywordList = asList(
                kwdToRemain,
                kwdToBeFiltered);
        List<Keyword> remainingKeywords = filter.filter(keywordList);
        assertThat(remainingKeywords, contains(kwdToRemain));
    }

    private List<String> getReportContent() throws FileNotFoundException {
        Scanner reportFile = new Scanner(new File("report.csv"));
        List<String> reportContent = new ArrayList<String>();
        while (reportFile.hasNext()) {
            reportContent.add(reportFile.nextLine());
        }
        return reportContent;
    }
	}
