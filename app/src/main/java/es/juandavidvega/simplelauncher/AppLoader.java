package es.juandavidvega.simplelauncher;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.ArrayList;
import java.util.List;

import es.juandavidvega.simplelauncher.holder.AppPreview;

public class AppLoader {
    private  final Context context;
    public AppLoader(Context context) {
        this.context = context;
    }

    public List<AppPreview> load(){
        PackageManager manager = context.getPackageManager();
        List<AppPreview> apps = new ArrayList<AppPreview>();
        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> availableActivities = manager.queryIntentActivities(i, 0);
        for(ResolveInfo resolveInfo : availableActivities){
            apps.add(new AppPreview(
                    resolveInfo.activityInfo.packageName,
                    resolveInfo.loadLabel(manager).toString(),
                    resolveInfo.loadIcon(manager)
                    ));
        }
        return apps;
    }
}
