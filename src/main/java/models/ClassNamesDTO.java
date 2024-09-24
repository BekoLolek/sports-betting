package models;

public class ClassNamesDTO {
    public String home_team;
    public String away_team;
    public String odds;
    public String date;
    public String time;
    public String cookieButton;
    public String loadMoreButton;
    public String gameContainer;

    public ClassNamesDTO(String home_team, String away_team, String odds, String date, String time, String cookieButton, String loadMoreButton, String gameContainer) {
        this.home_team = home_team;
        this.away_team = away_team;
        this.odds = odds;
        this.date = date;
        this.time = time;
        this.cookieButton = cookieButton;
        this.loadMoreButton = loadMoreButton;
        this.gameContainer = gameContainer;
    }

    public ClassNamesDTO() {
    }

    public String getHome_team() {
        return home_team;
    }

    public void setHome_team(String home_team) {
        this.home_team = home_team;
    }

    public String getAway_team() {
        return away_team;
    }

    public void setAway_team(String away_team) {
        this.away_team = away_team;
    }

    public String getOdds() {
        return odds;
    }

    public void setOdds(String odds) {
        this.odds = odds;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCookieButton() {
        return cookieButton;
    }

    public void setCookieButton(String cookieButton) {
        this.cookieButton = cookieButton;
    }

    public String getLoadMoreButton() {
        return loadMoreButton;
    }

    public void setLoadMoreButton(String loadMoreButton) {
        this.loadMoreButton = loadMoreButton;
    }

    public String getGameContainer() {
        return gameContainer;
    }

    public void setGameContainer(String gameContainer) {
        this.gameContainer = gameContainer;
    }
}
