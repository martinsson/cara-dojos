import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import org.junit.Test;


public class GoGameTest {
   int nbLibertiesByDefault = 4;
   
   // a stone can
   @Test
   public void aStoneCanBeTakenIfItHasIOneLiberty() {
      int nbCornersTaken = 3;
      boolean canBeTaken = canBeTaken(nbCornersTaken);
      assertThat(canBeTaken, equalTo(true));
   }

   public boolean canBeTaken(int nbCornersTaken) {
      int nbLiberties = liberties(nbCornersTaken);
      return (nbLiberties==1);
   }

   private int liberties(int nbCornersTaken) {
      int nbLiberties = nbLibertiesByDefault-nbCornersTaken;
      return nbLiberties;
   }

   @Test
   public void aStoneCannotBeTakenIfItHasITwoLiberties() {
      int nbCornersTaken = 2;
      assertThat(canBeTaken(nbCornersTaken), equalTo(false));
   }
   
   @Test 
   public void theNumberLibertiesOfAGroupOfTwoIsTheSumOfAllStonesLiberties() {
      List<String> fieldsTaken = list("b2", "c2");
      List<String> cornersForB2 = list("a2", "b1", "c2", "b3");
      List<String> cornersForC2 = list("b2", "c1", "d2", "c3" );
      Set<String> corners = new TreeSet<String>(cornersForB2);
      corners.addAll(cornersForC2);
      corners.removeAll(fieldsTaken);
      int totalLiberties = corners.size();
      assertThat(totalLiberties, equalTo(6));
   }
   
   @Test 
   public void theNumberLibertiesOfAGroupOfThreeIsTheSumOfAllStonesLiberties() {
      List<String> fieldsTaken = list("b2", "c2", "d2");
      int cornersTakenForB2 = 1;
      int cornersTakenForc2 = 2;
      int cornersTakenFord2 = 1;
      int totalLiberties = liberties(cornersTakenForB2) + liberties(cornersTakenForc2) + liberties(cornersTakenFord2) ;
      assertThat(totalLiberties, equalTo(8));
   }
   
   private List<String> list(String...fields) {
      List<String > list = new ArrayList<String>();
      for (String field : fields) {
         list.add(field);
      }
      return list;
   }

   @Test
   public void aGroupCanBeTakenIfItHasOnlyOneLIberty() {
      
   }
   
   //theGameISOverWhenAPlayerCapturesAtleastOneStone
   // aPlayerCanPlayOnAFieldThatISntTakenAlready
   ///...
   
}
