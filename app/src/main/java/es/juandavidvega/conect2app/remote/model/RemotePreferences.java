package es.juandavidvega.conect2app.remote.model;

import java.util.Date;
import java.util.List;

import es.juandavidvega.conect2app.remote.model.AppPreview;

public class RemotePreferences {
    //param list should be include here
    private List<AppPreview> apps;
    private final Date date;

    public RemotePreferences() {
        this.date = new Date();
    }

    public void setApps(List<AppPreview> apps) {
        this.apps = apps;
    }

    public List<AppPreview> getApps() {
        return apps;
    }

    public Date getDate() {
        return date;
    }
}
