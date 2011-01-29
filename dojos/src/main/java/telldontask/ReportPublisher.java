package telldontask;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

class ReportPublisher {

   static final String REPORT_FILE = "report.csv";

    void writeLines(PrintWriter writer, List<Keyword> filteredKeywords) {
        for (Keyword keyword : filteredKeywords) {
            writer.println(keyword.getSearchTerm() +"; " + keyword.isPublished());
        }
    }

    void writeHeader(PrintWriter writer) {
        writer.println("Search term; published");
    }

    public void printReport(List<Keyword> filteredKeywords) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(ReportPublisher.REPORT_FILE)));
        writeHeader(writer);
        writeLines(writer, filteredKeywords);
        writer.close();
    }
    
}