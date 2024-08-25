package models;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class DataCollection {
    public static void main(String[] args) {

        JSONArray data = new JSONArray();
        JSONParser parser = new JSONParser();
        try {
            data = (JSONArray) parser.parse(new FileReader("C:\\Users\\Lolek\\IdeaProjects\\sports betting\\src\\main\\java\\data\\classesData.json"));
        } catch (IOException | ParseException e) {
            //not an option
        }

        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://vegas.hu/betting#page=overview");

        GameDataDTO mockData = new GameDataDTO("Manchester Utd", "Fulham","1,64","4,60","5,25","08.16","21:00");






        int id = data.size();
        String url = "";
        boolean cookie = false;
        boolean loadMore = false;
        String cookieClassName = "CybotCookiebotDialogBodyButton";

        String loadMoreClassName = "";
        String gamesWrapper = "SportsBookRouterstyled__RoutesWrapper-sc-iwc66s-0.fHjhfY";
        String gameWrapper = "";
        String homeName = findElementClass(mockData.getHomeName(),driver);
        String awayName = findElementClass(mockData.getAwayName(),driver);
        String homeOdds = findElementClass(mockData.getHomeOdds(),driver);
        String awayOdds = findElementClass(mockData.getAwayOdds(),driver);
        String drawOdds = findElementClass(mockData.getDrawOdds(),driver);
        String time = findElementClass(mockData.getStartTime(),driver);
        String date = findElementClass(mockData.getDate(),driver);
        JSONArray classesObject = new JSONArray();
        classesObject.add(id);
        classesObject.add(url);
        classesObject.add(cookie);
        classesObject.add(loadMore);
        classesObject.add(cookieClassName);
        classesObject.add(loadMoreClassName);
        classesObject.add(gamesWrapper);
        classesObject.add(gameWrapper);
        classesObject.add(homeName);
        classesObject.add(awayName);
        classesObject.add(homeOdds);
        classesObject.add(awayOdds);
        classesObject.add(drawOdds);
        classesObject.add(time);
        classesObject.add(date);







        //write to file
        try {
            FileWriter writer = new FileWriter("C:\\Users\\Lolek\\IdeaProjects\\sports betting\\src\\main\\java\\data\\classesData.json", true);
            writer.write(classesObject.toJSONString());
            writer.flush();
            writer.close();
        } catch (IOException exception) {
            //not an option
        }

    }
    public static String findElementClass(String value, WebDriver driver){
        return driver.findElement(By.xpath("//*[contains(text(),'"+ value + "')]")).getAttribute("class");

    }
}
