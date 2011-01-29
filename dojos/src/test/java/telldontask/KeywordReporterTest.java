package telldontask;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.*;

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
import org.junit.Test;
import org.mockito.Mock;

public class KeywordReporterTest {
    
    public interface Filter {

    }

    public static class AlreadyPublishedKeywordsRemover implements Filter {

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

    }

    public interface KeywordProvider {

        List<Keyword> getKeywords();

    }

    public static class KeywordReporter {

        private final KeywordProvider keywordProvider;

        public KeywordReporter(KeywordProvider keywordProvider) {
            this.keywordProvider = keywordProvider;
        }

        public KeywordReporter(KeywordProvider keywordProvider2, Filter publishedKeywords) {
            // TODO Auto-generated constructor stub
        }

        public void report() throws IOException {
            PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream("report.csv")));
            writer.println("Search term; published");
            List<Keyword> keywords = keywordProvider.getKeywords();
            for (Keyword keyword : keywords) {
                writer.println(keyword.getSearchTerm() +"; " + keyword.isPublished());
            }
            writer.close();
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
