import models.GameDataDTO;
import scraper.ScraperGeneral;

import java.util.ArrayList;

public class SportScraper {

    public static ArrayList<GameDataDTO> gameDataList = new ArrayList<GameDataDTO>();

    public static void main(String[] args) throws InterruptedException {

        ScraperGeneral scraper = new ScraperGeneral();
        gameDataList.addAll(scraper.ScraperGeneral(1));




    }




}

