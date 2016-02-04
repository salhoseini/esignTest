package entity;
import static  org.junit.Assert.*;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by Salman on 2/1/2016.
 */
public class PotTest {

    @Test
    public void testDefaultConstructor() {
        Pot pot = new Pot();

        BigDecimal totalValue = pot.getTotalMoney();
        BigDecimal expected = new BigDecimal(200);
        assertEquals(expected , totalValue);
    }

    @Test
    public void givenAddValueToPotThenTheCorrectAmountIsAdded() {
        Pot pot = new Pot();

        pot.addToPot(50);
        BigDecimal expected = new BigDecimal(250);
        BigDecimal newValue = pot.getTotalMoney();
        assertEquals(expected , newValue);
    }

    @Test
    public void givenSubtractValueFromPotThenTheCorrectAmountIsSubtracted() {
        Pot pot = new Pot();

        pot.subtractFromPot(new BigDecimal(100));
        BigDecimal expected = new BigDecimal(100);
        BigDecimal newValue = pot.getTotalMoney();
        assertEquals(expected , newValue);
    }

    @Test
    public void givenARequestToCalculateTheTotalPrizeThenTheReturnedValueIsHalfOfPot() {
        Pot pot = new Pot();

        BigDecimal prize = pot.calculateTotalPrize();
        assertEquals(new BigDecimal(100) , prize);
    }

    @Test
    public void givenValuesToCalculatePercentageTheCorrectResultShouldBeReturned() {
        Pot pot = new Pot();
        BigDecimal base = new BigDecimal(200);
        BigDecimal prc = new BigDecimal(50);
        BigDecimal expected = new BigDecimal(100);
        BigDecimal result = pot.calculatePercentage(base,prc);
        assertEquals(expected , result);
    }

    @Test
    public void givenValuesToCalculateFirstPrizeTheCorrectResultShouldBeReturned() {
        Pot pot = new Pot();

        BigDecimal expected = new BigDecimal(75);
        BigDecimal result = pot.calculateFirstPrize();
        assertEquals(expected , result);
    }

    @Test
    public void givenValuesToCalculateSecondPrizeTheCorrectResultShouldBeReturned() {
        Pot pot = new Pot();

        BigDecimal expected = new BigDecimal(15);
        BigDecimal result = pot.calculateSecondPrize();
        assertEquals(expected , result);
    }

    @Test
    public void givenValuesToCalculateThirdPrizeTheCorrectResultShouldBeReturned() {
        Pot pot = new Pot();

        BigDecimal expected = new BigDecimal(10);
        BigDecimal result = pot.calculateThirdPrize();
        assertEquals(expected , result);
    }

}
