package agilecup;

import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

import java.util.EmptyStackException;

import org.junit.Test;


public class ConnectionTest {
    public static final String key = "vCYVFJtCTDkaLdlZqfUecw%3D%3D";
    public String uri = "http://beta.agilecup.org/problem/01?key="+key;
    public MazeFinder mazeFinder = new MazeFinder();
    public MazePublisher mazePublisher = new MazePublisher();
    
    @Test
    public void canConnectAndDownloadJson() throws Exception {
        String result = mazeFinder.getRawMaze(uri);
        assertThat(result.split("\"")[1], equalTo("#I#\\n#O#"));
    }
    
    @Test
    public void removeApostrofsAndSplitsAtNewLine() throws Exception {
        String[] result = mazeFinder.mazeRows(uri);
        assertThat(result, arrayContaining("#I#", "#O#"));
    }
    
    String responseUri = "http://beta.agilecup.org/solution/01";
    String solution = "S";
    private MazePublisher publisher = new MazePublisher();

    @Test public void canPostTheSolutionToMaze1() throws Exception {
        String result = mazePublisher.getRawSolutionResult(responseUri, solution);
        assertThat(result, startsWith("{\"success\":\"ok\""));
    }

    @Test public void theTokenOfASuccessfulResultIsNonEmpty() throws Exception {
        String expiryToken = publisher.expiryToken(responseUri, solution);
        assertThat(expiryToken.length(), greaterThan(0));
    }
    
    @Test public void theTokenOfABadSolutionIsEmpty() throws Exception {
        String expiryToken = publisher.expiryToken(responseUri, "W");
        assertThat(expiryToken.length(), is(0) );
    }
    @Test
    public void canGetTheSecondMaze() throws Exception {
        String expiryToken = publisher.expiryToken(responseUri, solution);

        String[] result = mazeFinder.mazeRows("http://beta.agilecup.org/problem/02?key="+key+"&token="+expiryToken);
        printMaze(result);
        assertThat(result, not(emptyArray()));
    }

    private void printMaze(String[] mazeRows) {
        for (String row : mazeRows) {
            System.out.println(row);
        }
    }
    
}
