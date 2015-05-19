package es.juandavidvega.conect2app.remote.configure;

import android.app.Activity;

import java.util.List;

import es.juandavidvega.conect2app.launcher.widgets.WidgetContainer;
import es.juandavidvega.conect2app.models.connect.view.SerializableConfiguration;
import es.juandavidvega.conect2app.remote.model.AppPreview;

/**
 * Created by JuanDavid on 14/01/2015.
 */
public abstract class Configurable extends Activity {
    public abstract void hasBeenConfigured(WidgetContainer viewWidgets);

    public abstract void updateData(SerializableConfiguration newData);
}
