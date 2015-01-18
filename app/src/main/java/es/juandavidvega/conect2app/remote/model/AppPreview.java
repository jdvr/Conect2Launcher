package es.juandavidvega.conect2app.remote.model;

import android.graphics.drawable.Drawable;

import com.google.gson.annotations.SerializedName;

public class AppPreview {

    @SerializedName("app_pkg")
    private final String name;

    @SerializedName("app_name")
    private final String label;

    @SerializedName("app_img")
    private final String iconURL;

    @SerializedName("app_type")
    private final String type;

    public AppPreview(String name, String label, String iconURL, String type) {
        this.name = name;
        this.label = label;
        this.iconURL = iconURL;
        this.type = type;
    }

    public String getIconURL() {
        return iconURL;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public String getIcon() {
        return iconURL;
    }
}
