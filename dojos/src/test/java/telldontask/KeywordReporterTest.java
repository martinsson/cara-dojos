package telldontask;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import telldontask.Filter.AlreadyPublishedKeywordsRemover;
import telldontask.Filter.NoFilter;

public class KeywordReporterTest {

    @Mock
    private KeywordProvider keywordProvider;

    @Before
    public void initBeforeTest() throws Exception {
        initMocks(this);
    }

    @Test
    public void writesAReportToDisk() throws Exception {
        when(keywordProvider.getKeywords()).thenReturn(asList(new Keyword("Canon eos 5D", "yes")));
        KeywordReporter reporter = newKeywordReporter();
        reporter.report();
        
        Scanner reportFile = new Scanner(new File("report.csv"));
        String header = reportFile.nextLine();
        String firstLine = reportFile.nextLine();
        assertThat(header, equalTo("Search term; published"));
        assertThat(firstLine, notNullValue());
    }

    private KeywordReporter newKeywordReporter() {
        return new KeywordReporter(new DecoratedKeywordProvider(keywordProvider, new NoFilter()));
    }
    
    @Test
    public void usesTheDataFromTheKeywords() throws Exception {
        when(keywordProvider.getKeywords()).thenReturn(asList(new Keyword("Macbook pro", "no")));
        KeywordReporter reporter = newKeywordReporter();
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
        KeywordReporter reporter = newKeywordReporter();
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
        KeywordReporter reporter = new KeywordReporter(new DecoratedKeywordProvider(keywordProvider, publishedKeywords));
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
        Keyword kwdToRemain = new Keyword("toto", "no");
        Keyword kwdToBeFiltered = new Keyword("rototo", "yes");
        List<Keyword> keywordList = asList(
                kwdToRemain,
                kwdToBeFiltered);
        Filter filter = new AlreadyPublishedKeywordsRemover();
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
