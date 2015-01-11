package es.juandavidvega.conect2app.persistence;

import java.util.List;

import es.juandavidvega.conect2app.holder.AppPreview;

/**
 * Created by JuanDavid on 11/01/2015.
 */
public interface AppLoader {
    public List<AppPreview> load();
}
