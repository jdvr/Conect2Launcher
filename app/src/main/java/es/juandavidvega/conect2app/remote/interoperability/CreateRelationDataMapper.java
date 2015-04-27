package es.juandavidvega.conect2app.remote.interoperability;

import com.google.gson.annotations.SerializedName;

public class CreateRelationDataMapper{
    @SerializedName("device")
    private String device;

    @SerializedName("address")
    private String address;

    @SerializedName("user")
    private String user;

    @SerializedName("pass")
    private String password;

    public String getDevice() {
        return device;
    }

    public String getUserEmail() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceNumber() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public boolean isCompleted(){
        return device != null &&  address != null;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
