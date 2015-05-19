package es.juandavidvega.conect2app.launcher.appsmanager;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

public class ApplicationAppStarter extends  AppStarter{

    @Override
    public void start(Activity activity, String appIdentifier) {
        Log.d("APPSTARTER", "Srart" + appIdentifier + " By Package");
        Intent intent = activity.getPackageManager().getLaunchIntentForPackage(appIdentifier);
        activity.startActivity(intent);
    }
}
