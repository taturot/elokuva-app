package com.example.finnkinoht;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RatingController extends AppCompatActivity {

    public static List<Rating> ratings = new ArrayList<>();

    public static void printRatingsToConsole()
    {
        ratings.stream().forEach(System.out::println);
    }

    public void deleteRatingFile()
    {
        File dir = this.getBaseContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        Path jsonFile = dir.toPath().resolve("ratings.json");
        if(Files.exists(jsonFile))
        {
            try
            {
                Files.delete(jsonFile);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }

        }
    }

    public static List<Rating> getRatingFileContent(Context ctx)
    {
        File dir = ctx.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        Path jsonFile = dir.toPath().resolve("ratings.json");
        if(Files.exists(jsonFile))
        {
            try
            {
                byte[] jsonBinary = Files.readAllBytes(jsonFile);
                String json = new String(jsonBinary, StandardCharsets.UTF_8);

                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());
                List<Rating> ratings = mapper.readValue(json, new TypeReference<List<Rating>>(){});
                return ratings;
            }
            catch(IOException e)
            {
                System.err.println("Failed to read rating file, exception: "+e.getMessage());
            }
        }
        else {
            System.err.println("File doesn't exist: "+jsonFile.toAbsolutePath());
        }
        return Collections.emptyList();
    }

    private void readRatingFile()
    {
        List<Rating> ratings = getRatingFileContent(this.getBaseContext());
        if(ratings != null && !ratings.isEmpty()) RatingController.ratings = ratings;
    }

    public void writeRatingToFile(Rating rating)
    {
        //TODO: duplicate check & update instead of always adding
        ratings.add(rating);
        File dir = this.getBaseContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        Path jsonFile = dir.toPath().resolve("ratings.json");

        System.err.println("Location of jsonFile: "+jsonFile.toAbsolutePath());

        try
        {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            final String json = mapper.writeValueAsString(ratings);
            Files.write(jsonFile, Collections.singleton(json), StandardCharsets.UTF_8, new StandardOpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING});
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public static String currentMovie = null;

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arvostelulayout);
        readRatingFile();
        printRatingsToConsole();
        button = (Button) findViewById(R.id.Save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RatingBar movieRating = (RatingBar) findViewById(R.id.reviewRatingInput);
                final float ratingFloat = movieRating.getRating();
                TextInputEditText commentTextbox = (TextInputEditText) findViewById(R.id.reviewTextInput);
                final String comment = commentTextbox.getText().toString();
                Rating rating = new Rating(RatingController.currentMovie, comment, ratingFloat, LocalDateTime.now());
                writeRatingToFile(rating);
                goBack();
            }
        });
    }

    public void goBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}

