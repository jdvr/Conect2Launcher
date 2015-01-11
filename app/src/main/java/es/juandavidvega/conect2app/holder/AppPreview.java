package es.juandavidvega.conect2app.holder;

import android.graphics.drawable.Drawable;

public class AppPreview {

    private final String name;
    private final String label;
    private final Drawable icon;

    public AppPreview(String name, String label, Drawable icon) {
        this.name = name;
        this.label = label;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public Drawable getIcon() {
        return icon;
    }
}
