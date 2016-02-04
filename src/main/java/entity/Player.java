package entity;

import java.math.BigDecimal;

/**
 * Created by Salman on 1/31/2016.
 */
public class Player {

    private String name;
    private LotteryTicket ticket;

    public Player(String name , LotteryTicket ticketNo) {
        this.name = name;
        this.ticket = ticketNo;
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LotteryTicket getTicketNumber() {
        return ticket;
    }

    public void setTicketNumber(LotteryTicket ticketNumber) {
        this.ticket = ticketNumber;
    }

}
