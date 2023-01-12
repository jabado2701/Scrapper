package scrappingFunctions;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import java.util.regex.Matcher;


public class Tools {

    public static String base = "https://www.google.com/search?q=booking+";


    public static Document getHTML(String url) throws IOException {
        return Jsoup.connect(url).get();
    }


    public static String reviewUrl(String url) {
        String base = "https://www.booking.com/reviews/";

        String lugar = getCountry(url);

        List<String> urlList = new ArrayList<>();

        Pattern pattern = Pattern.compile("https://www.booking.com/hotel/" + lugar + "/(.*)");
        Matcher matcher = pattern.matcher(url);
        while (matcher.find()) {
            urlList.add(matcher.group(1));
        }
        return base + lugar + "/hotel/" + urlList.get(0);
    }

    private static String getCountry(String url) {
        List<String> place = new ArrayList<>();
        Pattern pattern = Pattern.compile("https://www.booking.com/hotel/(.*?)/");
        Matcher matcher = pattern.matcher(url);
        while (matcher.find()) {

            place.add(matcher.group(1));
        }

        return place.get(0);
    }

    public static String coincidenceVerification(String url) throws IOException {

        List<String> list = getUrl(url);
        List<String> regex = getFirstCoincidence(list);

        if (regex.size() == 0) {
            System.out.println("No coincidence found -> Closing programme");
            System.exit(0);
        }

        return regex.get(0);

    }

    private static List<String> getFirstCoincidence(List<String> list) {
        List<String> regex = new ArrayList<>();
        for (String elements : list) {
            Pattern pattern = Pattern.compile("https://www.booking.com/hotel/(.*?).html");
            Matcher matcher = pattern.matcher(elements);
            boolean matchFound = matcher.find();

            if (matchFound) {
                regex.add(elements);
                break;

            }
        }
        return regex;
    }

    private static List<String> getUrl(String url) throws IOException {
        List<String> list = new ArrayList<>();
        Elements elements = Tools.getHTML(url).getElementsByClass("yuRUbf");
        for (Element element : elements) {
            String result = element.select("a").attr("href");
            list.add(result);
        }
        return list;
    }


}
