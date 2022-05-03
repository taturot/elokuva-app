package com.example.finnkinoht;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;


public class EventChangeListener extends Activity implements AdapterView.OnItemSelectedListener {

    private AppCompatActivity parent;

    public EventChangeListener()
    {}

    public EventChangeListener(AppCompatActivity parent)
    {
        this.parent = parent;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Event event = (Event) adapterView.getItemAtPosition(i);
        System.out.println("You have selected: "+event.Title);
        MainActivity.selectedEvent = event;
    }



    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}