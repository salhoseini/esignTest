package core;

import entity.LotteryTicket;
import entity.Player;
import exec.GameController;

import java.util.Map;

/**
 * Created by Salman on 2/2/2016.
 */
public class GameCore {
    private static GameCore instance = null;
    private LotteryTicket[] drawnTickets;
    private Player[] drawnPlayers;
    private GameController controller;
    private boolean drawn = false;

    private GameCore() {
        controller = new GameController();
        drawnTickets = new LotteryTicket[3];
        drawnPlayers = new Player[3];
    }

    public static GameCore getInstance() {
        if(instance == null) {
            instance = new GameCore();
        }
        return instance;
    }

    public void reinitialize() {
        controller = new GameController();
        drawnTickets = new LotteryTicket[3];
        drawnPlayers = new Player[3];
        drawn = false;
    }

    public LotteryTicket processPurchase(Player player) {
        LotteryTicket result = controller.produceTicket(player);
        return result;
    }

    public void processDraw() {
        drawnTickets[0] = controller.draw();
        drawnTickets[1] = controller.draw();
        drawnTickets[2] = controller.draw();
        drawn = true;
    }

    public void processWinners() {
        Map<Integer,Player> players = controller.getBoughtTickets();
        drawnPlayers[0] = players.get(drawnTickets[0].getTicketNumber());
        drawnPlayers[1] = players.get(drawnTickets[1].getTicketNumber());
        drawnPlayers[2] = players.get(drawnTickets[2].getTicketNumber());
    }

    public boolean isDrawn() {
        return drawn;
    }

    public GameController getController() {
        return controller;
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public Player[] getDrawnPlayers() {
        return drawnPlayers;
    }

    public void setDrawnPlayers(Player[] drawnPlayers) {
        this.drawnPlayers = drawnPlayers;
    }

    public LotteryTicket[] getDrawnTickets() {
        return drawnTickets;
    }

    public void setDrawnTickets(LotteryTicket[] drawnTickets) {
        this.drawnTickets = drawnTickets;
    }
}
