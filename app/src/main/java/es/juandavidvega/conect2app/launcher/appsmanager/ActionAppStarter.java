package es.juandavidvega.conect2app.launcher.appsmanager;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by JuanDavid on 18/01/2015.
 */
public class ActionAppStarter extends AppStarter {
    @Override
    public void start(Activity activity, String appIdentifier) {
        activity.startActivity(new Intent(appIdentifier));
    }
}
