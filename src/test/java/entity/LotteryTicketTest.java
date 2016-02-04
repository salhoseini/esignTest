package entity;

import org.junit.Test;
import static  org.junit.Assert.*;

/**
 * Created by Salman on 2/1/2016.
 */
public class LotteryTicketTest {

    @Test
    public void testConstructor() {
        LotteryTicket ticket = new LotteryTicket(14);

        assertEquals(14 , ticket.getTicketNumber());
        assertFalse(ticket.isTaken());
    }

}
