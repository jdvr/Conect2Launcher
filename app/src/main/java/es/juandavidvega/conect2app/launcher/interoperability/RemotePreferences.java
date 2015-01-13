package es.juandavidvega.conect2app.launcher.interoperability;

import java.util.Date;
import java.util.List;

import es.juandavidvega.conect2app.remote.model.AppPreview;

public class RemotePreferences {
    //param list should be include here
    private final List<AppPreview> apps;
    private final Date date;

    public RemotePreferences(List<AppPreview> apps, Date date) {
        this.apps = apps;
        this.date = date;
    }

    public List<AppPreview> getApps() {
        return apps;
    }

    public Date getDate() {
        return date;
    }
}
