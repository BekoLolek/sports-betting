package models;

public class CalculatedData {

    public double homeOdds;
    public double drawOdds;
    public double awayOdds;

    public double homeAmount;
    public double drawAmount;
    public double awayAmount;

    public double totalBet;
    public double sumOfInverses;

    public double homeProfit;
    public double drawProfit;
    public double awayProfit;

    public CalculatedData(double homeOdds, double drawOdds, double awayOdds, double totalBet) {
        this.homeOdds = homeOdds;
        this.drawOdds = drawOdds;
        this.awayOdds = awayOdds;
        this.totalBet = totalBet;

        sumOfInverses = 1/homeOdds + 1/drawOdds + 1/awayOdds;

        if(sumOfInverses<1){
            homeAmount = totalBet/homeOdds * 1/sumOfInverses;
            drawAmount = totalBet/drawOdds * 1/sumOfInverses;
            awayAmount = totalBet/awayOdds * 1/sumOfInverses;

            homeProfit = homeOdds * homeAmount;
            drawProfit = drawOdds * drawAmount;
            awayProfit = awayOdds * awayAmount;
        }else{
            homeAmount = 0;
            drawAmount = 0;
            awayAmount = 0;

            homeProfit = 0;
            drawProfit = 0;
            awayProfit = 0;
        }


    }

    public double getHomeOdds() {
        return homeOdds;
    }

    public double getDrawOdds() {
        return drawOdds;
    }

    public double getAwayOdds() {
        return awayOdds;
    }

    public double getHomeAmount() {
        return homeAmount;
    }

    public double getDrawAmount() {
        return drawAmount;
    }

    public double getAwayAmount() {
        return awayAmount;
    }

    public double getTotalBet() {
        return totalBet;
    }

    public double getHomeProfit() {
        return homeProfit;
    }

    public double getDrawProfit() {
        return drawProfit;
    }

    public double getAwayProfit() {
        return awayProfit;
    }

    public double getSumOfInverses() {
        return sumOfInverses;
    }
}
