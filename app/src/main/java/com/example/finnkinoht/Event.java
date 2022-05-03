package com.example.finnkinoht;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="Event", strict = false)
public class Event {


    @Element
    public String Title;

    @Override
    public String toString()
    {
        return Title;
    }

}
