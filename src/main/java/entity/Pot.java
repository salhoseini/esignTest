package entity;

import java.math.BigDecimal;

/**
 * Created by Salman on 2/1/2016.
 */
public class Pot {

    BigDecimal totalMoney;

    public Pot() {
        totalMoney = new BigDecimal(200);
    }

    public Pot(int money) {
        totalMoney = new BigDecimal(money);
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public void addToPot(int money) {
        BigDecimal valueToAdd = new BigDecimal(money);
        this.totalMoney = this.totalMoney.add(valueToAdd);
    }

    public void subtractFromPot(BigDecimal money) {

        this.totalMoney = this.totalMoney.subtract(money);
    }

    protected BigDecimal calculateTotalPrize() {
        BigDecimal result = calculatePercentage(totalMoney, new BigDecimal(50));
        return result;
    }

    public BigDecimal calculateFirstPrize() {
        BigDecimal totalPrize = calculateTotalPrize();
        BigDecimal result = calculatePercentage(totalPrize , new BigDecimal(75));
        return result;
    }

    public BigDecimal calculateSecondPrize() {
        BigDecimal totalPrize = calculateTotalPrize();
        BigDecimal result = calculatePercentage(totalPrize , new BigDecimal(15));
        return result;
    }

    public BigDecimal calculateThirdPrize() {
        BigDecimal totalPrize = calculateTotalPrize();
        BigDecimal result = calculatePercentage(totalPrize , new BigDecimal(10));
        return result;
    }

    protected BigDecimal calculatePercentage(BigDecimal base , BigDecimal prc) {
        return base.multiply(prc).divide(new BigDecimal(100));
    }
}
