package agilecup;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Matchers.startsWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;


public class SolutionIteratorTest {

    public static class MazeSolver {

        public String solve(String[] maze1) {
            // TODO Auto-generated method stub
            return null;
        }

    }

    public class SolutionIterator {

        private final MazeFinder mazeFinder;
        private final MazePublisher mazePublisher;
        private final MazeSolver mazeSolver;

        public SolutionIterator(MazeFinder mazeFinder, MazePublisher mazePublisher, MazeSolver mazeSolver) {
            this.mazeFinder = mazeFinder;
            // TODO Auto-generated constructor stub
            this.mazePublisher = mazePublisher;
            this.mazeSolver = mazeSolver;
        }

        public void iterate() throws ClientProtocolException, IOException {
            String[] mazeRows = mazeFinder.mazeRows("http://beta.agilecup.org/problem/"+"01");
            String solution = mazeSolver.solve(mazeRows);
            mazePublisher.expiryToken("http://beta.agilecup.org/solution/01", solution);
            
        }

    }

    @Test
    public void returns0WhenThereAreNoGoodResults() throws Exception {
        String[] maze1 = new String[]{"#I#", "#O#"};
        String[] maze2 = new String[]{"##","IO", "##"};
        
        MazeFinder mazeFinder = mock(MazeFinder.class);
        when(mazeFinder.mazeRows(startsWith("http://beta.agilecup.org/problem/01"))).thenReturn(maze1);
//        when(mazeFinder.mazeRows(startsWith("http://beta.agilecup.org/problem/02"))).thenReturn(maze2);
        
        MazeSolver mazeSolver = mock(MazeSolver.class);
        when(mazeSolver.solve(maze1)).thenReturn("S");
//        when(mazeSolver.solve(maze2)).thenReturn("E");
        
        MazePublisher mazePublisher = mock(MazePublisher.class);
        
        SolutionIterator solutionIterator = new SolutionIterator(mazeFinder, mazePublisher, mazeSolver );
        solutionIterator.iterate();
        
        verify(mazePublisher).expiryToken("http://beta.agilecup.org/solution/01", "S");
//        verify(mazePublisher.expiryToken("http://beta.agilecup.org/solution/02", "E"));
        
        List<String> asList = asList("{\"success\":\"error\"}");
//        SolutionIterator solutionIterator = new SolutionIterator(mazeFinder, mazeSolver);
//        solutionIterator.it
    }
    
    
}
