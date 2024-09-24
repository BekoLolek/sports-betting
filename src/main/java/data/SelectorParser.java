package data;

import models.ClassNamesDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class SelectorParser {


    public static ClassNamesDTO parseElementsToSelector(Document source) {
        String homeTeamName = "Real Madrid";
        String awayTeamName = "Alavés";
        String dateDay = "24";
        String dateMonth = "09";
        String time = "21:00";

        ClassNamesDTO classNames = new ClassNamesDTO();

        Document doc = source;

        Elements homeTeamElements = doc.getElementsContainingOwnText(homeTeamName);
        for (Element teamElement : homeTeamElements) {
            Element parent = teamElement.parent();
            classNames.setHome_team(parent.className());
            System.out.println("Team: " + teamElement.text() + ", Selector: " + parent.className());

        }
        Elements awayTeamElements = doc.getElementsContainingOwnText(awayTeamName);
        for (Element teamElement : awayTeamElements) {
            Element parent = teamElement.parent();
            classNames.setAway_team(parent.className());
            System.out.println("Team: " + teamElement.text() + ", Selector: " + parent.className());
        }

        Element currentParent = homeTeamElements.get(0).parent();
        while (true) {
            if (!currentParent.parent().select("span:matchesOwn(\\d+\\,\\d+)").isEmpty()) {
                classNames.setGameContainer(currentParent.className());
                System.out.println("Game container: " + currentParent.className());
                currentParent = currentParent.parent();
                break;
            } else {
                currentParent = currentParent.parent();
            }

        }
        Elements gameItemElements = currentParent.getAllElements();

        //get odds
        Elements oddsElements = gameItemElements.select("span:matchesOwn(\\d+\\,\\d+)");
        for(int i = 0; i < 3;i++){
            Element parent = oddsElements.get(i).parent();
            classNames.setOdds(parent.className());
            System.out.println("Odds: " + parent.className());
        }

        //get date
        Elements dateElements = gameItemElements.select("span:matchesOwn((?=.*" + dateMonth + ")(?=.*" + dateDay + "))");
        for (Element e : dateElements) {
            Element parent = e.parent();
            classNames.setDate(parent.className());
            System.out.println("Date: " + parent.className());
        }
        //get time
        Elements timeElements = gameItemElements.select("span:matchesOwn(" + time + ")");
        for (Element e : timeElements) {
            Element parent = e.parent();
            classNames.setTime(parent.className());
            System.out.println("Time: " + parent.className());
        }

        return classNames;
    }
}
