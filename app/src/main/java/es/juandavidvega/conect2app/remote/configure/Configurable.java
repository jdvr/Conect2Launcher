package es.juandavidvega.conect2app.remote.configure;

import android.app.Activity;

import es.juandavidvega.conect2app.launcher.widgets.WidgetContainer;

/**
 * Created by JuanDavid on 14/01/2015.
 */
public abstract class Configurable extends Activity {
    public abstract void hasBeenConfigured(WidgetContainer viewWidgets);
}
