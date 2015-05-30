package es.juandavidvega.conect2app.models.connect.view;


import com.google.gson.annotations.SerializedName;

public class DeviceInfo {

    @SerializedName("available_apps")
    public App[] apps;

    @SerializedName("current_configuration")
    public SerializableConfiguration  currentConfiguration;

    @SerializedName("user")
    public String user;

    @SerializedName("device")
    public String device;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public App[] getApps() {
        return apps;
    }

    public void setApps(App[] apps) {
        this.apps = apps;
    }

    public SerializableConfiguration getCurrentConfiguration() {
        return currentConfiguration;
    }

    public void setCurrentConfiguration(SerializableConfiguration currentConfiguration) {
        this.currentConfiguration = currentConfiguration;
    }
}
