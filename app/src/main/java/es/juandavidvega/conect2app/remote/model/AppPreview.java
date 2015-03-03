package es.juandavidvega.conect2app.remote.model;

import com.google.gson.annotations.SerializedName;

public class AppPreview {

    @SerializedName("app_id")
    private final String id;

    @SerializedName("app_img")
    private final String iconURL;

    @SerializedName("app_type")
    private final String type;

    public AppPreview(String id, String iconURL, String type) {
        this.id = id;
        this.iconURL = iconURL;
        this.type = type;
    }

    public String getIconURL() {
        return iconURL;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }


    public String getIcon() {
        return iconURL;
    }
}
