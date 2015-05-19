package es.juandavidvega.conect2app.models.connect.model;

import java.io.Serializable;

public class Item implements Serializable{

    private final String id;
    private final String iconURL;
    private final Type type;

    public Item(String id, String iconURL, Type type) {
        this.id = id;
        this.iconURL = iconURL;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getIconURL() {
        return iconURL;
    }

    public Type getType() {
        return type;
    }

    public enum Type {Action("Action"), Contact("Contact"), Application("App");
        private final String application;

        Type(String application) {
            this.application = application;
        }
    }
}
