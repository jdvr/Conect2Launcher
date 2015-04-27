package es.juandavidvega.conect2app.models.connect.model;

import java.io.Serializable;

public class Device implements Serializable{

    private final  String number;
    private final String address;
    private String alias;
    private DeviceConfiguration configuration;

    public Device(String number, String address,DeviceConfiguration configuration) {
        this.number = number;
        this.address = address;
        this.configuration = configuration;
    }

    public String getAddress() {
        return address;
    }

    public DeviceConfiguration getConfiguration() {
        return configuration;
    }

    public String getNumber() {
        return number;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void updateConfiguration(DeviceConfiguration deviceConfiguration){
        this.configuration = deviceConfiguration;
    }

    @Override
    public boolean equals(Object other) {
        return this == other || !(other == null || getClass() != other.getClass()) && equals((Device) other);

    }


    private boolean equals(Device device) {
        return this.configuration.equals(device.getConfiguration())
                && this.number.equals(device.getNumber());

    }

    @Override
    public int hashCode() {
        int result = number != null ? number.hashCode() : 0;
        result = 31 * result + (configuration != null ? configuration.hashCode() : 0);
        return result;
    }
}
