package exec;

import core.GameCore;
import entity.LotteryTicket;
import entity.Player;
import entity.Pot;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;

import java.math.BigDecimal;

import static  org.junit.Assert.*;


/**
 * Created by Salman on 2/3/2016.
 */
public class GamePanelTest {


    @Test
    public void givenPurchaseCommandThenATicketIsProducedForTheGivenPlayer(@Mocked final GameCore core) {

        final LotteryTicket mockedTicket = new LotteryTicket(1);

        new Expectations(){
            {
                core.processPurchase((Player) any);
                result = mockedTicket;
            }
        };

        LotteryTicket ticket = GamePanel.sellTicket("Bob" , core);
        assertEquals(mockedTicket, ticket);
    }

    @Test
    public void givenDrawCommandWhenAlreadyDrawnThenDrawShouldNotProgress(@Mocked final GameCore core) {
        new Expectations(){
            {
                core.isDrawn();
                result = true;
            }
        };

        boolean result = GamePanel.draw(core);
        assertFalse(result);
    }

    @Test
    public void givenDrawCommandWhenNotDrawnThenDrawShouldProgress(@Mocked final GameCore core) {
        new Expectations(){
            {
                core.isDrawn();
                result = false;

                core.processDraw();
            }
        };

        boolean result = GamePanel.draw(core);
        assertTrue(result);
    }

    @Test
    public void givenWinnersCommandWhenNotDrawnThenWinnersShouldNotBeCalculated(@Mocked final GameCore core) {
        new Expectations(){
            {
                core.isDrawn();
                result = false;
            }
        };

        Player[] result = GamePanel.getWinners(core);
        assertNull(result);
    }

    @Test
    public void givenWinnersCommandWhenDrawnThenWinnersShouldBeCalculated(@Mocked final GameCore core) {
        Player player1 = new Player("Bob");
        Player player2 = new Player("Don");
        Player player3 = new Player("Jon");
        final Player[] winners = {player1 , player2 , player3};
        new Expectations(){
            {
                core.isDrawn();
                result = true;

                core.processWinners();

                core.getDrawnPlayers();
                result = winners;
            }
        };

        Player[] result = GamePanel.getWinners(core);
        assertNotNull(result);
    }

    @Test
    public void givenPotAndThreeWinnersToCalculatePrizesThreePrizesAreProducedAccordingly() {
        Pot pot = new Pot();
        Player player1 = new Player("Bob");
        Player player2 = new Player("Don");
        Player player3 = new Player("Jon");
        Player[] winners = {player1 , player2 , player3};
        BigDecimal[] result = GamePanel.calculatePrizes(winners , pot);
        assertEquals(new BigDecimal(100) , pot.getTotalMoney());
        assertEquals(new BigDecimal(75), result[0]);
        assertEquals(new BigDecimal(15), result[1]);
        assertEquals(new BigDecimal(10), result[2]);
    }

    @Test
    public void givenPotAndTwoWinnersToCalculatePrizesThreePrizesAreProducedAccordingly() {
        Pot pot = new Pot();
        Player player2 = new Player("Don");
        Player player3 = new Player("Jon");
        Player[] winners = {null , player2 , player3};
        BigDecimal[] result = GamePanel.calculatePrizes(winners , pot);
        assertEquals(new BigDecimal(175) , pot.getTotalMoney());
        assertEquals(new BigDecimal(15), result[1]);
        assertEquals(new BigDecimal(10), result[2]);
    }

    @Test
    public void givenPotAndOneWinnersToCalculatePrizesThreePrizesAreProducedAccordingly() {
        Pot pot = new Pot();
        Player player3 = new Player("Jon");
        Player[] winners = {null , null , player3};
        BigDecimal[] result = GamePanel.calculatePrizes(winners , pot);
        assertEquals(new BigDecimal(190) , pot.getTotalMoney());
        assertEquals(new BigDecimal(10), result[2]);
    }

    @Test
    public void givenPotAndNoWinnersToCalculatePrizesThreePrizesAreProducedAccordingly() {
        Pot pot = new Pot();
        Player[] winners = {null , null , null};
        BigDecimal[] result = GamePanel.calculatePrizes(winners , pot);
        assertEquals(new BigDecimal(200) , pot.getTotalMoney());
    }

}
