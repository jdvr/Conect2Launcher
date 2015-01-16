package es.juandavidvega.conect2app.remote.configure;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import es.juandavidvega.conect2app.launcher.HomeActivity;
import es.juandavidvega.conect2app.launcher.R;
import es.juandavidvega.conect2app.launcher.adapter.AppsAdapter;
import es.juandavidvega.conect2app.launcher.widgets.AppsWidget;
import es.juandavidvega.conect2app.launcher.widgets.ClockWidget;
import es.juandavidvega.conect2app.launcher.widgets.WidgetContainer;
import es.juandavidvega.conect2app.remote.persistence.AllAppsLoader;
import es.juandavidvega.conect2app.remote.persistence.CustoAppsLoader;


public class HomeConfigurator implements Configurator {

    private Configurable configurable;
    private Activity target;
    private WidgetContainer targetWidgets;


    public HomeConfigurator(Configurable configurableActivity) {
        this.configurable = configurableActivity;
        this.target = (Activity) configurableActivity;
        targetWidgets = new WidgetContainer();
    }

    public Configurable getConfigurableActivity() {
        return configurable;
    }

    @Override
    public void configureView(int resource) {
        target.setContentView(resource);
        addWidgets();
        configureImageLoader();
    }

    private void configureImageLoader() {
        File cacheDir = StorageUtils.getCacheDirectory(target.getApplicationContext());
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(target.getApplicationContext())
                .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
                .diskCacheExtraOptions(480, 800, null)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(100)
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(config);
    }

    private void addWidgets() {
        addClockWidget();
        addAppsWidget();
    }

    private void addAppsWidget() {
        final AppsWidget apps = new AppsWidget((GridView) target.findViewById(R.id.gv_apps),
                                          new AppsAdapter(target.getApplicationContext(), R.layout.app_preview));
        apps.getAppsAdpater().setApps(new CustoAppsLoader().load());
        apps.loadAppsGrid();
        apps.setItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = target.getPackageManager().getLaunchIntentForPackage(apps.getAppsAdpater().getItem(position).getName());
                Log.d("APP", apps.getAppsAdpater().getItem(position).getName());
                target.startActivity(i);

            }
        });
        targetWidgets.add(apps);
    }

    private void addClockWidget() {
        ClockWidget clock = new ClockWidget((TextView) target.findViewById(R.id.tv_hour),
                                            (TextView) target.findViewById(R.id.tv_minutes_seg));
        new Timer().schedule(getClockTask(new Handler(), clock), 0, 1000);
        targetWidgets.add(clock);

    }

    private TimerTask getClockTask(final Handler handler, final ClockWidget clock) {
        return new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            clock.setLastDate(new Date());
                            clock.update();
                        }catch (Exception e){}
                    }
                });
            }
        };
    }
}
