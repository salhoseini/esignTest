package exec;

import core.GameCore;
import entity.LotteryTicket;
import entity.Player;
import entity.Pot;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Salman on 2/1/2016.
 */
public class GamePanel {

    private static final int DEFAULT_TICKET_PRICE = 10;
    private static final String PURCHASE_COMMAND = "purchase";
    private static final String DRAW_COMMAND = "draw";
    private static final String WINNERS_COMMAND = "winners";

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        GameCore core = GameCore.getInstance();
        Pot pot = new Pot();

        System.out.println("pot Contains " + pot.getTotalMoney().toPlainString());
        System.out.println("write purchase to register as a player and get a ticket");
        System.out.println("write draw to play");
        System.out.println("write winners to see who the winners are");
        System.out.println("write quit to quit the game");

        while(true) {
            String command = scanner.next();
            if(command.equalsIgnoreCase(PURCHASE_COMMAND)) {
                System.out.println("enter first name");
                String playerName = scanner.next();
                LotteryTicket ticket = sellTicket(playerName , core);
                if(ticket == null) {
                    System.out.println("No more tickets are available for this round!");
                } else {
                    System.out.println(playerName +" bought ticket : " + ticket.getTicketNumber());
                    pot.addToPot(DEFAULT_TICKET_PRICE);
                    System.out.println("pot Contains " + pot.getTotalMoney().toPlainString());
                }
            }
            else if (command.equalsIgnoreCase(DRAW_COMMAND)) {
                boolean drawProgression = draw(core);
                if(!drawProgression) {
                    continue;
                }

            }
            else if (command.equalsIgnoreCase(WINNERS_COMMAND)) {
                Player[] winners = getWinners(core);
                if(winners == null) {
                    continue;
                } else {
                    BigDecimal[] prizes = calculatePrizes(winners , pot);
                    printInfo(winners , core.getDrawnTickets() , prizes[0], prizes[1] ,prizes[2]);
                    core.reinitialize();
                    System.out.println("pot Contains " + pot.getTotalMoney().toPlainString());
                }
            }
            else if (command.equalsIgnoreCase("quit")) {
                break;
            } else {
                System.out.println("Command not recognized! try again");
                continue;
            }
        }

    }

    protected static BigDecimal[] calculatePrizes(Player[] winners, Pot pot) {
        BigDecimal _1stPrize = pot.calculateFirstPrize();
        BigDecimal _2ndPrize = pot.calculateSecondPrize();
        BigDecimal _3rdPrize = pot.calculateThirdPrize();
        if(winners[0] != null) {
            pot.subtractFromPot(_1stPrize);
        }
        if(winners[1] != null) {
            pot.subtractFromPot(_2ndPrize);
        }
        if(winners[2] != null) {
            pot.subtractFromPot(_3rdPrize);
        }
        BigDecimal[] result = {_1stPrize , _2ndPrize , _3rdPrize};
        return result;
    }

    protected static Player[] getWinners(GameCore core) {
        if(!core.isDrawn()) {
            System.out.println("need to draw first");
            return null;
        }
        core.processWinners();
        Player[] winners = core.getDrawnPlayers();
        return winners;
    }

    protected static boolean draw(GameCore core) {
        if(core.isDrawn()) {
            System.out.println("draw has already been done!");
            return false;
        } else {
            core.processDraw();
            System.out.println("Draw is Done!");
            return true;
        }
    }

    protected static LotteryTicket sellTicket(String playerName , GameCore core) {
        Player player = new Player(playerName);
        LotteryTicket ticket = core.processPurchase(player);
        return ticket;
    }


    private static void printInfo(Player[] winners, LotteryTicket[] drawnTickets,
                                  BigDecimal _1stPrize, BigDecimal _2ndPrize, BigDecimal _3rdPrize) {
        System.out.println("***********************************************");
        System.out.print("1st ball : " + drawnTickets[0].getTicketNumber() +"\t");
        System.out.print("2nd ball : " + drawnTickets[1].getTicketNumber() +"\t");
        System.out.print("3rd ball : " + drawnTickets[2].getTicketNumber() +"\t");
        System.out.println();
        if(winners[0] != null) {
            System.out.print(winners[0].getName() + " : " + _1stPrize.toPlainString() + " $" +"\t");
        } else {
            System.out.print("No Winner!" +"\t");
        }
        if(winners[1] != null) {
            System.out.print(winners[1].getName() + " : " + _2ndPrize.toPlainString() + " $" +"\t");
        } else {
            System.out.print("No Winner!" +"\t");
        }
        if(winners[2] != null) {
            System.out.print(winners[2].getName() + " : " + _3rdPrize.toPlainString() + " $" +"\t");
        } else {
            System.out.print("No Winner!" +"\t");
        }
        System.out.println();
        System.out.println("***********************************************");

    }
}
