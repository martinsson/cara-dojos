package agilecup;

import static org.junit.Assert.*;

import org.junit.Test;


public class MazeSolverTest {

    @Test
    public void itSolvesTheFirstMaze() throws Exception {
        String[] mazeLines = new MazeFinder().mazeLines("#I#\n#O#");
    }
}
