package models;

import java.io.IOException;

public class GameDataDTO {


    public String homeName;
    public String awayName;
    public String homeOdds;
    public String drawOdds;
    public String awayOdds;
    public String startTime;
    public String date;

    public CalculatedData data;



    public GameDataDTO(String homeName, String awayName, String homeOdds, String drawOdds, String awayOdds, String startTime, String date) {
        this.homeName = homeName;
        this.awayName = awayName;
        this.homeOdds = homeOdds;
        this.drawOdds = drawOdds;
        this.awayOdds = awayOdds;
        this.startTime = startTime;
        this.date = date;
    }

    public GameDataDTO() {
    }


    public void calculate(){
        data = new CalculatedData(Double.parseDouble(homeOdds.replaceAll(",",".")),Double.parseDouble(drawOdds.replaceAll(",",".")),Double.parseDouble(awayOdds.replaceAll(",",".")), 5000);
    }

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public String getAwayName() {
        return awayName;
    }

    public void setAwayName(String awayName) {
        this.awayName = awayName;
    }

    public String getHomeOdds() {
        return homeOdds;
    }

    public void setHomeOdds(String homeOdds) {
        this.homeOdds = homeOdds;
    }

    public String getDrawOdds() {
        return drawOdds;
    }

    public void setDrawOdds(String drawOdds) {
        this.drawOdds = drawOdds;
    }

    public String getAwayOdds() {
        return awayOdds;
    }

    public void setAwayOdds(String awayOdds) {
        this.awayOdds = awayOdds;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CalculatedData getData() {
        return data;
    }

    public void setData(CalculatedData data) {
        this.data = data;
    }
}
