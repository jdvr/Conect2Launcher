package es.juandavidvega.conect2app.interoperability;

import java.util.Date;
import java.util.List;

import es.juandavidvega.conect2app.holder.AppPreview;

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
