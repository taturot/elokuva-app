package com.example.finnkinoht;

import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import java.util.List;

@Root(name="Events", strict=false)
public class Events {

    @ElementList(inline = true)
    public List<Event> events;





}