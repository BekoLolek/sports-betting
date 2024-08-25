package scraper;

import models.GameDataDTO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

public class ScraperGeneral {
    public ArrayList<GameDataDTO> ScraperGeneral(int id) {
        ArrayList<GameDataDTO> dataList = new ArrayList<>();

        JSONArray data = null;
        try {
            JSONParser parser = new JSONParser();
            data = (JSONArray) parser.parse(new FileReader("C:\\Users\\Lolek\\IdeaProjects\\sports betting\\src\\main\\java\\data\\classesData.json"));
        } catch (IOException | ParseException e) {
            System.out.println("No file found");
        }
        if (data != null){
            for (Object o : data){
                JSONObject obj = (JSONObject) o;
                if(id == Integer.parseInt(obj.get("id").toString())){
                    System.out.println("Found site with id " + id + " and url " + obj.get("url"));
                    String url = obj.get("url").toString();
                    boolean cookie = (boolean) obj.get("cookie");
                    boolean loadMore = (boolean) obj.get("loadMore");
                    String cookieIdName = obj.get("cookieIdName").toString();
                    String cookieButtonOrder = obj.get("cookieButtonOrder").toString();
                    String loadMoreClassName = obj.get("loadMoreClassName").toString();
                    String homeNameClassName = obj.get("homeName").toString();
                    String awayNameClassName = obj.get("awayName").toString();
                    String homeOddsClassName = obj.get("homeOdds").toString();
                    String awayOddsClassName = obj.get("awayOdds").toString();
                    String drawOddsClassName = obj.get("drawOdds").toString();
                    String timeClassName = obj.get("time").toString();
                    String dateClassName = obj.get("date").toString();
                    String datetimeClassName = "";
                    String datetimeSeparator = "";
                    if(timeClassName.isEmpty() || dateClassName.isEmpty()){
                        datetimeClassName = obj.get("datetime").toString();
                        datetimeSeparator = obj.get("datetimeSeparator").toString();
                    }
                    String gamesWrapperClassName = obj.get("gamesWrapper").toString();
                    String singleGameWrapperClassName = obj.get("gameWrapper").toString();
                    WebDriver driver = new FirefoxDriver();
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
                    driver.get(url);

                    if(cookie){
                        WebElement element = driver.findElement(By.id(cookieIdName));
                        wait.until(ExpectedConditions.elementToBeClickable(element));
                        element.click();

                    }
                    if(loadMore){
                        int loadMoreCount = 0;
                        while(true){
                            if(loadMoreCount > 5){
                                break;
                            }
                            try{
                                wait.until(ExpectedConditions.elementToBeClickable(By.className(loadMoreClassName)));
                                driver.findElements(By.className(loadMoreClassName)).forEach(WebElement::click);
                                loadMoreCount++;

                            } catch (Exception e) {
                                System.out.println("Error with loading more: " + e.getMessage());
                                break;
                            }
                        }
                    }
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(gamesWrapperClassName)));
                    String pageSource = driver.getPageSource();
                    Document doc = Jsoup.parse(pageSource);

                    Elements gameWrappers = doc.getElementsByClass(gamesWrapperClassName);
                    Element gameWrapper = gameWrappers.first();
                    Elements games = gameWrapper.getElementsByClass(singleGameWrapperClassName);
                    for(int i = 0; i < games.size(); i++){
                        String homeName = games.get(i).getElementsByClass(homeNameClassName).get(0).text();
                        String awayName = games.get(i).getElementsByClass(awayNameClassName).get(0).text();
                        if(homeName.equals(awayName)){
                            homeName = games.get(i).getElementsByClass(homeNameClassName).get(0).text();
                            awayName = games.get(i).getElementsByClass(awayNameClassName).get(1).text();
                        }

                        String homeOdds = games.get(i).getElementsByClass(homeOddsClassName).get(0).text();
                        String awayOdds = games.get(i).getElementsByClass(awayOddsClassName).get(0).text();
                        String drawOdds = games.get(i).getElementsByClass(drawOddsClassName).get(0).text();
                        if(homeOddsClassName.equals(awayOddsClassName)){
                            homeOdds = games.get(i).getElementsByClass(homeOddsClassName).get(0).text();
                            drawOdds = games.get(i).getElementsByClass(homeOddsClassName).get(1).text();
                            awayOdds = games.get(i).getElementsByClass(homeOddsClassName).get(2).text();
                        }

                        String time;
                        String date;
                        if(timeClassName.isEmpty() || dateClassName.isEmpty()){
                            time = games.get(i).getElementsByClass(datetimeClassName).get(0).text().split(datetimeSeparator)[0];
                            date = games.get(i).getElementsByClass(datetimeClassName).get(0).text().split(datetimeSeparator)[1];
                        }else{
                            time = games.get(i).getElementsByClass(timeClassName).get(0).text();
                            date = games.get(i).getElementsByClass(dateClassName).get(0).text();
                        }
                        if(timeClassName.equals(dateClassName)){
                            time = games.get(i).getElementsByClass(timeClassName).get(0).text();
                            date = games.get(i).getElementsByClass(dateClassName).get(1).text();
                        }

                        System.out.println("Home name: " + homeName);
                        System.out.println("Away name: " + awayName);
                        System.out.println("Home odds: " + homeOdds);
                        System.out.println("Draw odds: " + drawOdds);
                        System.out.println("away odds: " + awayOdds);
                        System.out.println("Time: " + time);
                        System.out.println("Date: " + date);

                        GameDataDTO gameDataDTO = new GameDataDTO(homeName,awayName,homeOdds,awayOdds,drawOdds,time,date);
                        dataList.add(gameDataDTO);
                    }
                    driver.quit();

                    break;
                }
            }
        }

        return dataList;
    }
}
