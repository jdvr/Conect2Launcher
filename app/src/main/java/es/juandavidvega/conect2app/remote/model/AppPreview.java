package es.juandavidvega.conect2app.remote.model;

import android.graphics.drawable.Drawable;

public class AppPreview {

    private final String name;
    private final String label;
    private final String iconURL;

    public AppPreview(String name, String label, String iconURL) {
        this.name = name;
        this.label = label;
        this.iconURL = iconURL;
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
