package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.HarbCareerDateTimeParser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class HabrCareerParse implements Parse {
    private final DateTimeParser dateTimeParser;
    private static final String SOURCE_LINK = "https://career.habr.com";

    public HabrCareerParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    public static void main(String[] args) throws IOException {
        Parse habrCareerParse = new HabrCareerParse(new HarbCareerDateTimeParser());
        String pageLink = String.format("%s/vacancies/java_developer?page=%s", SOURCE_LINK, 1);
        List<Post> posts = new LinkedList<>(habrCareerParse.list(pageLink));
        posts.forEach(post -> System.out.println(post.getTitle()));
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

    @Override
    public List<Post> list(String link) throws IOException {
        List<Post> posts = new LinkedList<>();
        Connection connection = Jsoup.connect(link);
        Document document = connection.get();
        Elements rows = document.select(".vacancy-card__inner");
        rows.forEach(row -> {
            Element titleElement = row.select(".vacancy-card__title").first();
            Element linkElement = titleElement.child(0);
            String title = titleElement.text();
            Element dateElement = row.select(".vacancy-card__date").first();
            Element timeElement = dateElement.child(0);
            String date = String.format("%s", timeElement.attr("datetime"));
            String vacancyLink = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
            String description = String.format("%s", new HabrCareerParse(dateTimeParser).retrieveDescription(vacancyLink));
            LocalDateTime vacancyDate = dateTimeParser.parse(date);
            posts.add(new Post(title, vacancyLink, description, vacancyDate));
        });
        return posts;
    }
}