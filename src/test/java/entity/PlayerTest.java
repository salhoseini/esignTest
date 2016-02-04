package entity;

import org.junit.Test;
import static  org.junit.Assert.*;

import java.math.BigDecimal;

/**
 * Created by Salman on 2/1/2016.
 */
public class PlayerTest {
    @Test
    public void givenValuesToPlayerConstructorThenValuesAreSetForPlayer() {
        String name = "Bob";
        LotteryTicket ticket = new LotteryTicket(13);

        Player player= new Player(name ,ticket );

        assertEquals(name , player.getName());
        assertEquals(ticket , player.getTicketNumber());


    }
}
