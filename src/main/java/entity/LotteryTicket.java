package entity;

/**
 * Created by Salman on 2/1/2016.
 */
public class LotteryTicket {

    int ticketNumber;
    boolean taken;

    public LotteryTicket(int number) {
        this.ticketNumber = number;
        taken = false;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }
}
