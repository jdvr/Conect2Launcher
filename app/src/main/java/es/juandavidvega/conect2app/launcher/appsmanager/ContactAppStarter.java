package es.juandavidvega.conect2app.launcher.appsmanager;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class ContactAppStarter extends AppStarter {

    @Override
    public void start(Activity activity, String appIdentifier) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + appIdentifier));
        activity.startActivity(intent);

    }
}
