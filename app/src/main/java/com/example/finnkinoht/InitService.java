package com.example.finnkinoht;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class InitService {

    public static void initialise(final AppCompatActivity ref)
    {
        //Close application Button
        Button button = (Button) ref.findViewById(R.id.Close);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.finish();
                System.exit(0);
            }
        });

        //Open reviewView Button
        button = (Button) ref.findViewById(R.id.reviewView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RatingController.currentMovie = MainActivity.selectedEvent.Title;
                Intent intent = new Intent(ref, RatingController.class);
                ref.startActivity(intent);
            }
        });

        //Open reviewList Button
        button = (Button) ref.findViewById(R.id.List);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ref, RatingDisplayView.class);
                ref.startActivity(intent);
            }
        });

        try {
            FinnkinoService.setMovies(ref);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



}
