package es.juandavidvega.conect2app.models.connect.model;

import java.io.Serializable;

import es.juandavidvega.conect2app.models.connect.view.SerializableConfiguration;


public class DeviceConfiguration implements Serializable {

    private Home home;

    public DeviceConfiguration(Home home) {
        this.home = home;
    }

    public void addItem(Item item){
        home.addItem(item);
    }

    public void removeItem(Item item){
        home.remove(item);
    }


    public Item[] getItems(){
        return home.getItems();
    }

    public SerializableConfiguration getSerializableConfigurationFor(Device device){
        return new SerializableConfiguration(this, device);
    }

    public Home home(){
        return home;
    }

    public static DeviceConfiguration getDefault() {
        Home home = new Home();
        return new DeviceConfiguration(home);
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }

    public boolean isClockActive() {
        return home.isClockVisible();
    }

    public boolean isDateActive() {
        return home.isDateVisible();
    }
}
