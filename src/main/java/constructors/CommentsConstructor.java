package constructors;

import java.util.List;

public class CommentsConstructor {

    String author;

    String writtenDates;

    String country;

    String mark;

    String title;

    String negative;

    String positive;

    String stayDates;

    List<String> customerTags;

    public CommentsConstructor() {
    }

    public String getWrittenDates() {
        return writtenDates;
    }

    public void setWrittenDates(String writtenDates) {
        this.writtenDates = writtenDates;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNegative() {
        return negative;
    }

    public void setNegative(String negative) {
        this.negative = negative;
    }

    public String getPositive() {
        return positive;
    }

    public void setPositive(String positive) {
        this.positive = positive;
    }

    public String getStayDates() {
        return stayDates;
    }

    public void setStayDates(String stayDates) {
        this.stayDates = stayDates;
    }

    public List<String> getCustomerTags() {
        return customerTags;
    }

    public void setCustomerTags(List<String> customerTags) {
        this.customerTags = customerTags;
    }
}
