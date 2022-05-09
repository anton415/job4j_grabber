package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class HabrCareerParse {

    private static final String SOURCE_LINK = "https://career.habr.com";

    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 5; i++) {
            String pageLink = String.format("%s/vacancies/java_developer?page=%s", SOURCE_LINK, i);
            Connection connection = Jsoup.connect(pageLink);
            Document document = connection.get();
            Elements rows = document.select(".vacancy-card__inner");
            rows.forEach(row -> {
                Element titleElement = row.select(".vacancy-card__title").first();
                Element linkElement = titleElement.child(0);
                String vacancyName = titleElement.text();
                Element dateElement = row.select(".vacancy-card__date").first();
                Element timeElement = dateElement.child(0);
                String date = String.format("%s", timeElement.attr("datetime"));
                String link = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
                String description = String.format("%s", new HabrCareerParse().retrieveDescription(link));
                System.out.printf("%s %s %s %s%n", vacancyName, date, description, link);
            });
        }
    }

    private String retrieveDescription(String link) {
        String description = "";
        Connection connection = Jsoup.connect(link);
        try {
            Document document = connection.get();
            Element firstElement = document.selectFirst(".style-ugc");
            description = firstElement.text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return description;
    }
}