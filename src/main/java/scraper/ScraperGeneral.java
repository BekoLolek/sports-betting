package scraper;

import data.SelectorParser;
import models.ClassNamesDTO;
import models.GameDataDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class ScraperGeneral {
    public ArrayList<GameDataDTO> ScraperGeneral(int id) {
        ArrayList<GameDataDTO> dataList = new ArrayList<>();



        return dataList;
    }

    public void ClassNameScraper(String url){
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get(url);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String pageSource = driver.getPageSource();
        Document doc = Jsoup.parse(pageSource);
        ClassNamesDTO classNames = SelectorParser.parseElementsToSelector(doc);
        //todo send this to database
        driver.close();
    }
}
