package es.juandavidvega.conect2app.remote.persistence;

import java.util.List;

import es.juandavidvega.conect2app.remote.model.AppPreview;

/**
 * Created by JuanDavid on 11/01/2015.
 */
public interface AppLoader {
    public List<AppPreview> load();
}
