package es.juandavidvega.conect2app.remote.persistence;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.ArrayList;
import java.util.List;

import es.juandavidvega.conect2app.remote.model.AppPreview;

public class AllAppsLoader implements AppLoader {
    private  final Context context;
    public AllAppsLoader(Context context) {
        this.context = context;
    }

    @Override
    public List<AppPreview> load(){
        /*
        PackageManager manager = context.getPackageManager();
        List<AppPreview> apps = new ArrayList<>();
        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> availableActivities = manager.queryIntentActivities(i, 0);
        for(ResolveInfo resolveInfo : availableActivities){
            apps.add(new AppPreview(
                    resolveInfo.activityInfo.packageName,
                    resolveInfo.loadLabel(manager).toString(),
                    resolveInfo.loadIcon(manager)
                    ));
            if (apps.size() == 4) break;
        }*/
        return null;
    }
}
