package scrappingFunctions;

import com.google.gson.Gson;
import constructors.CommentsConstructor;
import constructors.MarksConstructor;
import constructors.ServicesConstructor;
import constructors.UbicationConstructor;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Booking implements HotelScrapper {
    @Override
    public String location(String name) throws IOException {
        Gson gson = new Gson();
        UbicationConstructor location = new UbicationConstructor();
        String hotel = Tools.getHTML(Tools.coincidenceVerification(name)).getElementsByClass("d2fee87262 pp-header__title").text();
        location.setName(hotel);
        Elements comment = Tools.getHTML(Tools.coincidenceVerification(name)).select("span.hp_address_subtitle.js-hp_address_subtitle.jq_tooltip");
        String place = comment.select("span").attr("title aria-describedby", "tooltip-1").text();
        location.setUbicacion(place);
        String coordinates = Tools.getHTML(Tools.coincidenceVerification(name)).select("a").attr("data-atlas-latlng");
        location.setCoordenadas(coordinates);
        return gson.toJson(location);
    }

    @Override
    public String services(String name) throws IOException {
        Gson gson = new Gson();
        Elements general = Tools.getHTML(Tools.coincidenceVerification(name)).getElementsByClass("hotel-facilities-group");
        ServicesConstructor facilities = new ServicesConstructor();
        Map<String, List<String>> hotelFacilitiesMap = new HashMap<>();
        return getString(gson, general, facilities, hotelFacilitiesMap);
    }

    private static String getString(Gson gson, Elements general, ServicesConstructor facilities, Map<String, List<String>> facilitiesList) {
        for (Element element : general) {
            String globalAttribute = element.getElementsByClass("bui-title__text hotel-facilities-group__title-text").text();
            List<String> contents = new ArrayList<>();
            Elements specificAttributes = element.getElementsByClass("bui-list__item bui-spacer--medium hotel-facilities-group__list-item");
            for (Element service : specificAttributes) {
                Elements specificService = service.getElementsByClass("bui-list__description");
                contents.add(specificService.text());
            }
            facilitiesList.put(globalAttribute, contents);
            facilities.setServicios(facilitiesList);
        }
        return gson.toJson(facilities);
    }

    @Override
    public String ratings(String name) throws IOException {
        Gson gson = new Gson();
        Elements grades = Tools.getHTML(Tools.coincidenceVerification(name)).getElementsByClass("a1b3f50dcd b2fe1a41c3 a1f3ecff04 e187349485 d19ba76520");
        MarksConstructor marks = new MarksConstructor();
        Map<String, String> servicesMarks = new HashMap<>();
        for (Element grade : grades) {
            String service = grade.getElementsByClass("d6d4671780").text();
            String serviceRating = grade.getElementsByClass("ee746850b6 b8eef6afe1").text();
            servicesMarks.put(service, serviceRating);
        }
        marks.setRatings(servicesMarks);
        return gson.toJson(marks);
    }

    @Override
    public String comments(String name) throws IOException {
        Gson gson = new Gson();
        Elements allComments = Tools.getHTML(Tools.reviewUrl(Tools.coincidenceVerification(name))).getElementsByClass("review_item clearfix ");
        List<CommentsConstructor> commentsList = new ArrayList<>();
        extractedComments(allComments, commentsList);
        return gson.toJson(commentsList);
    }

    private static void extractedComments(Elements comments, List<CommentsConstructor> CommentsConstructorList) {
        for (Element comment : comments) {
            CommentsConstructor attributes = new CommentsConstructor();
            extractedCommentsTitle(comment, attributes);
            List<String> tags = getCommentsReviewTags(comment);
            extractedCommentsItems(comment, attributes, tags);
            CommentsConstructorList.add(attributes);
        }
    }

    private static void extractedCommentsItems(Element element, CommentsConstructor attributes, List<String> tags) {
        attributes.setAuthor(element.getElementsByClass("reviewer_name").text());
        attributes.setWrittenDates(element.getElementsByClass("review_item_date").text());
        attributes.setCountry(element.getElementsByClass("reviewer_country").text());
        attributes.setMark(element.getElementsByClass("review-score-badge").text());
        attributes.setStayDates(element.getElementsByClass("review_staydate ").text());
        attributes.setPositive(element.getElementsByClass("review_pos ").text());
        attributes.setNegative(element.getElementsByClass("review_neg ").text());
        attributes.setCustomerTags(tags);
    }

    private static void extractedCommentsTitle(Element element, CommentsConstructor attributes) {
        Elements title = element.getElementsByClass("review_item_header_content_container");
        for (Element element1 : title) {
            String title1 = element1.select("span").attr("itemprop", "name").text();
            attributes.setTitle(title1);
        }
    }

    private static List<String> getCommentsReviewTags(Element element) {
        List<String> tagsList = new ArrayList<>();
        Elements tags = element.getElementsByClass("review_item_info_tags");
        for (Element tag : tags) {
            Elements reviewersTags = tag.getElementsByClass("review_info_tag ");
            for (Element clientTags : reviewersTags) {
                String el = clientTags.text();
                tagsList.add(el.replaceAll("•", ""));
            }
        }
        return tagsList;
    }
}