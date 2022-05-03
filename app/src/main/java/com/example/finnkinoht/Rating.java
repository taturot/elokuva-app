package com.example.finnkinoht;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Rating {

    public String title;
    public String comment;
    public float rating;
    public LocalDateTime reviewDate;

    public Rating()
    {}

    public Rating(String title, String comment, float rating, LocalDateTime reviewDate)
    {
        this.title = title;
        this.comment = comment;
        this.rating = rating;
        this.reviewDate = reviewDate;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("title=");
        sb.append(title);
        sb.append(", ");
        sb.append("comment=");
        sb.append(comment);
        sb.append(", ");
        sb.append("rating=");
        sb.append(rating);
        sb.append(", ");
        sb.append("reviewDate=");
        sb.append(reviewDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
        return sb.toString();
    }


}
