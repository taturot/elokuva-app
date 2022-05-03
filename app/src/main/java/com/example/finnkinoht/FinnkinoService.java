package com.example.finnkinoht;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.InputStream;
import java.net.URL;

public class FinnkinoService {

    public static void setMovies(AppCompatActivity ref) throws Exception
    {
        Events events = FinnkinoService.readXML();
        Spinner eventSpinner =  (Spinner) ref.findViewById(R.id.EventSpinner);
        ArrayAdapter<Event> adapter = new ArrayAdapter<Event>(ref, android.R.layout.simple_spinner_dropdown_item, events.events);
        eventSpinner.setAdapter(adapter);
        eventSpinner.setOnItemSelectedListener(new EventChangeListener(ref));
    }

    public static InputStream getURLStream(String urlS) throws Exception
    {
        URL url = new URL(urlS);
        return url.openStream();
    }

    public static Events readXML() throws Exception {
        String urlString ="https://www.finnkino.fi/xml/Events/";

        Serializer serializer = new Persister();
        Events events = serializer.read(Events.class,getURLStream(urlString));
        return events;
    }

}
