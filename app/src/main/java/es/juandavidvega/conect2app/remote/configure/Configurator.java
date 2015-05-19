package es.juandavidvega.conect2app.remote.configure;

import java.util.List;

import es.juandavidvega.conect2app.models.connect.view.SerializableConfiguration;
import es.juandavidvega.conect2app.remote.model.AppPreview;

/**
 * Created by JuanDavid on 14/01/2015.
 */
public interface Configurator {
    public void configureView(int resource);

    void update(SerializableConfiguration newData);
}
