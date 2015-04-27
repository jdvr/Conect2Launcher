package es.juandavidvega.conect2app.models.connect.view;



import com.google.gson.annotations.SerializedName;

import es.juandavidvega.conect2app.models.connect.model.Device;
import es.juandavidvega.conect2app.models.connect.model.DeviceConfiguration;
import es.juandavidvega.conect2app.models.connect.model.Item;

public class SerializableConfiguration {

    @SerializedName("items")
    public Item[] items;

    @SerializedName("clock")
    public boolean clock;

    @SerializedName("date")
    public boolean date;

    @SerializedName("device")
    public String device;

    @SerializedName("device_alias")
    public String alias;

    public SerializableConfiguration(DeviceConfiguration deviceConfiguration, Device device){
        this.items = deviceConfiguration.getItems();
        this.clock = deviceConfiguration.isClockActive();
        this.date = deviceConfiguration.isDateActive();
        this.device = device.getNumber();
        this.alias = device.getAlias();
    }

    public Item[] getItems() {
        return items;
    }

    public boolean isClock() {
        return clock;
    }

    public boolean isDate() {
        return date;
    }

    public String getDevice() {
        return device;
    }

    public String getAlias() {
        return alias;
    }
}
