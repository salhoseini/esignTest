package exec;

import entity.LotteryTicket;
import entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Salman on 2/1/2016.
 */
public class GameController {
    private List<LotteryTicket> tickets;
    private List<LotteryTicket> assignedTickets;
    private Map<Integer , Player> boughtTickets;
    private final int MAX_TICKETS_AVAILABLE = 50;

    public GameController() {
        initializeTeickets();
        assignedTickets = new ArrayList<LotteryTicket>();
        boughtTickets = new HashMap<Integer, Player>();
    }

    protected void initializeTeickets() {
        tickets = new ArrayList<LotteryTicket>();
        for(int index = 1 ; index <= MAX_TICKETS_AVAILABLE ; index++) {
            LotteryTicket ticket = new LotteryTicket(index);
            tickets.add(ticket);
        }
    }

    public LotteryTicket produceTicket(Player player) {
        LotteryTicket result = null;
        if(areAllTicketsBought()) {
            return result;
        }
        int index = generateRandomIndex();
        result = tickets.get(index);
        result.setTaken(true);
        boughtTickets.put(result.getTicketNumber() , player);
        assignedTickets.add(result);
        tickets.remove(index);
        return result;
    }

    public LotteryTicket draw() {
        int index = generateRandomIndex();
        LotteryTicket result = tickets.get(index);
        tickets.remove(index);
        return result;
    }

    protected int generateRandomIndex() {
        if(tickets.size() == 1) {
            return 0;
        }
        int index = ThreadLocalRandom.current().nextInt(1, tickets.size());
        return index;
    }

    protected boolean areAllTicketsBought() {
        if( boughtTickets.size() == MAX_TICKETS_AVAILABLE) {
            return true;
        }
        return false;
    }

    public List<LotteryTicket> getTickets() {
        return tickets;
    }

    public void setTickets(List<LotteryTicket> tickets) {
        this.tickets = tickets;
    }

    public Map<Integer, Player> getBoughtTickets() {
        return boughtTickets;
    }

    public void setBoughtTickets(Map<Integer, Player> boughtTickets) {
        this.boughtTickets = boughtTickets;
    }
}
