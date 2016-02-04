package core;

import entity.LotteryTicket;
import entity.Player;
import org.junit.Test;
import static  org.junit.Assert.*;

/**
 * Created by Salman on 2/2/2016.
 */
public class GameCoreTest {

    @Test
    public void givenAPurchaseCommandToCoreTheCommandIsProcessedByCore() {
        GameCore core = GameCore.getInstance();

        Player player = new Player("Bob");
        LotteryTicket ticket = core.processPurchase(player);
        assertTrue(ticket.getTicketNumber() >= 1);
        assertTrue(ticket.getTicketNumber() <= 50);
    }

    @Test
    public void givenADrawCommandToCoreProducesThreeUniqueLotteryTickets() {
        GameCore core = GameCore.getInstance();
        core.processDraw();
        LotteryTicket[] tickets = core.getDrawnTickets();
        assertNotNull(tickets[0]);
        assertNotNull(tickets[1]);
        assertNotNull(tickets[2]);
        assertNotEquals(tickets[0],tickets[1]);
        assertNotEquals(tickets[0],tickets[2]);
        assertNotEquals(tickets[2],tickets[1]);
    }

    @Test
    public void givenARequestToCheckForDrawCoreDoesNotAllowsDrawIfAlreadyDrawn() {
        GameCore core = GameCore.getInstance();
        core.processDraw();
        assertTrue(core.isDrawn());
    }

    @Test
    public void givenAnEndedRoundOfGameThenReinitializeShouldSetBackAllProperties() {
        GameCore core = GameCore.getInstance();
        core.processDraw();
        LotteryTicket[] tickets = core.getDrawnTickets();
        assertFalse(tickets[0] == null);
        assertFalse(tickets[1] == null);
        assertFalse(tickets[2] == null);

        core.reinitialize();
        tickets = core.getDrawnTickets();
        assertTrue(tickets[0] == null);
        assertTrue(tickets[1] == null);
        assertTrue(tickets[2] == null);
    }
}
