package com.example.finnkinoht;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.resources.TextAppearance;

import org.w3c.dom.Text;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class RatingDisplayView extends AppCompatActivity {

    private List<Rating> ratings;

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.listlayout);

        this.ratings  = RatingController.getRatingFileContent(this.getBaseContext());

        TableLayout ratingEntries = findViewById(R.id.ratingEntries);
        TableRow th = new TableRow(this);

        TextView title = new TextView(this);
        title.setText("Title");
        title.setTextColor(Color.BLACK);
        title.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG | Paint.FAKE_BOLD_TEXT_FLAG);
        th.addView(title);

        TextView comment = new TextView(this);
        comment.setText("Comment");
        comment.setTextColor(Color.BLACK);
        comment.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG | Paint.FAKE_BOLD_TEXT_FLAG);
        th.addView(comment);

        TextView ratingTH = new TextView(this);
        ratingTH.setPadding(4,0,4,0);
        ratingTH.setText("Rating");
        ratingTH.setTextColor(Color.BLACK);
        ratingTH.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG | Paint.FAKE_BOLD_TEXT_FLAG);
        th.addView(ratingTH);

        TextView reviewDate = new TextView(this);
        reviewDate.setPadding(4,0,4,0);
        reviewDate.setText("Review Date");
        reviewDate.setTextColor(Color.BLACK);
        reviewDate.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG | Paint.FAKE_BOLD_TEXT_FLAG);
        th.addView(reviewDate);

        ratingEntries.addView(th);

        for(Rating rating : ratings)
        {
            TableRow row = new TableRow(this);
            TextView titleTD = new TextView(this);
            TextView commentTD = new TextView(this);
            TextView ratingTD = new TextView(this);
            TextView reviewDateTD = new TextView(this);

            titleTD.setText(rating.title);
            commentTD.setText(rating.comment);
            ratingTD.setText(rating.rating+"");
            reviewDateTD.setText(rating.reviewDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm:ss")));
            row.addView(titleTD);
            row.addView(commentTD);
            row.addView(ratingTD);
            row.addView(reviewDateTD);
            ratingEntries.addView(row);
        }


        button = (Button) findViewById(R.id.Return);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEntry();
            }
        });
    }

    public void openEntry() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}