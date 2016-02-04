package exec;

import entity.LotteryTicket;
import entity.Player;
import org.junit.Test;
import static  org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Salman on 2/1/2016.
 */
public class GameControlerTest {

    @Test
    public void givenARequestToInitializeTheListOfTicketsThenAListContainingFiftyTicketsIsCreated() {
        GameController controler = new GameController();
        List<LotteryTicket> tickets = controler.getTickets();
        assertEquals(50 , tickets.size());
        LotteryTicket firstTicket = tickets.get(0);
        LotteryTicket lastTicket = tickets.get(49);

        assertEquals(1 , firstTicket.getTicketNumber());
        assertEquals(50 , lastTicket.getTicketNumber());
    }

    @Test
    public void givenARequestToBuyATicketThenARandomTicketIsSelectedFromListOfTickets() {
        GameController controler = new GameController();
        Player player = new Player("Bob");
        LotteryTicket playerTicket = controler.produceTicket(player);
        assertTrue(playerTicket.getTicketNumber() >= 1);
        assertTrue(playerTicket.getTicketNumber() <= 50);
    }

    @Test
    public void givenAllTicketsBoughtThenTheControllerShouldPreventBuyingNewTickets() {
        GameController controler = new GameController();
        List<LotteryTicket> tickets = new ArrayList<LotteryTicket>();
        for(int i = 0 ; i < 50 ; i ++) {
            Player player = new Player("Bob" + i);
            controler.produceTicket(player);
        }
        assertFalse(tickets.contains(null));
        Player player = new Player("Unlucky");
        LotteryTicket ticketNotValid = controler.produceTicket(player);
        assertNull(ticketNotValid);


    }

    @Test
    public void givenAPurchaseTicketCommandThenThePurchasedTicketIsAssignedToAPlayer() {
        GameController controler = new GameController();
        Player player = new Player("Bob");
        LotteryTicket playerTicket = controler.produceTicket(player);
        Map<Integer,Player> boughtTickets = controler.getBoughtTickets();
        assertEquals(1, boughtTickets.size());
        Player player1 = boughtTickets.get(playerTicket.getTicketNumber());
        assertNotNull(player1);
        assertEquals("Bob",player1.getName());
    }

    @Test
    public void givenADrawCommandThenTheDrawnTicketIsNotInTheTicketListAnymore() {
        LotteryTicket ticket = null;
        GameController controler = new GameController();
        ticket = controler.draw();
        assertTrue(controler.getTickets().size() == 49);
        assertTrue(!controler.getTickets().contains(ticket));
    }



}
