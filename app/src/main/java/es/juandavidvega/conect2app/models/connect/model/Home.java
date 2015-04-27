package es.juandavidvega.conect2app.models.connect.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Home implements Serializable{

    private boolean isClockVisible;

    private boolean isDateVisible;

    private List<Item> items;

    private ClockInfo clockInfo;
    private DateInfo dateInfo;

    public Home() {
        items = new ArrayList <>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public boolean isClockVisible() {
        return isClockVisible;
    }

    public void setClockVisible(boolean isClockVisible) {
        this.isClockVisible = isClockVisible;
    }

    public boolean isDateVisible() {
        return isDateVisible;
    }

    public void setDateVisible(boolean isDateVisible) {
        this.isDateVisible = isDateVisible;
    }

    public Item[] getItems() {
        return items.toArray(new Item[items.size()]);
    }

    public void remove(Item item) {
        items.remove(item);
    }
}


