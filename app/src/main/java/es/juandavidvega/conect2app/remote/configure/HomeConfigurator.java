package es.juandavidvega.conect2app.remote.configure;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import es.juandavidvega.conect2app.launcher.R;
import es.juandavidvega.conect2app.launcher.adapter.AppsAdapter;
import es.juandavidvega.conect2app.launcher.appsmanager.AppStarter;
import es.juandavidvega.conect2app.launcher.widgets.AppsWidget;
import es.juandavidvega.conect2app.launcher.widgets.ClockWidget;
import es.juandavidvega.conect2app.launcher.widgets.Widget;
import es.juandavidvega.conect2app.launcher.widgets.WidgetContainer;
import es.juandavidvega.conect2app.models.connect.model.Item;
import es.juandavidvega.conect2app.models.connect.view.SerializableConfiguration;
import es.juandavidvega.conect2app.remote.model.AppPreview;
import es.juandavidvega.conect2app.remote.persistence.CustomAppsLoader;


public class HomeConfigurator implements Configurator {

    private Configurable configurable;
    private WidgetContainer widgetForConfigurable;


    public HomeConfigurator(Configurable configurableActivity) {
        this.configurable = configurableActivity;
        widgetForConfigurable = new WidgetContainer();
    }

    @Override
    public void configureView(int resource) {
        configurable.setContentView(resource);
        addWidgets();
        configureImageLoader();
        configurable.hasBeenConfigured(widgetForConfigurable);
    }

    @Override
    public void update(SerializableConfiguration newData) {
        updateApps(newData.getItems());
    }

    private void updateApps(Item[] items) {
        AppsWidget widget = getAppsWidget();
        widget.getAppsAdpater().setApps(bindItemToAppPreview(items));
        widget.setItemClickListener(new AppClickListener(widget.getAppsAdpater()));
        widget.getAppsAdpater().notifyDataSetChanged();

    }

    private void configureImageLoader() {
        File cacheDir = StorageUtils.getCacheDirectory(configurable.getApplicationContext());
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(configurable.getApplicationContext())
                .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
                .diskCacheExtraOptions(480, 800, null)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(100)
                .build();
        ImageLoader.getInstance().init(config);
    }

    private void addWidgets() {
        addClockWidget();
        addAppsWidget();
    }

    private void addAppsWidget() {
        final AppsWidget apps = new AppsWidget((GridView) configurable.findViewById(R.id.gv_apps),
                new AppsAdapter(configurable.getApplicationContext(), R.layout.app_preview));
        apps.getAppsAdpater().setApps(new CustomAppsLoader().load());
        apps.loadAppsGrid();
        apps.setItemClickListener(new AppClickListener(apps.getAppsAdpater()));
        widgetForConfigurable.add(apps);
    }

    private void addClockWidget() {
        ClockWidget clock = new ClockWidget((TextView) configurable.findViewById(R.id.tv_hour),
                (TextView) configurable.findViewById(R.id.tv_minutes_seg));
        new Timer().schedule(getClockTask(new Handler(), clock), 0, 1000);
        widgetForConfigurable.add(clock);

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
                        } catch (Exception e) {
                        }
                    }
                });
            }
        };
    }

    private AppsWidget getAppsWidget() {
        for (Widget widget : widgetForConfigurable.getWidgets())
            if (widget instanceof AppsWidget) return (AppsWidget) widget;
        return null;
    }

    private List<AppPreview> bindItemToAppPreview(Item[] items) {
        List<AppPreview> apps = new ArrayList<>();
        for (Item item : items) {
            apps.add(new AppPreview(item.getId(), item.getIconURL(), item.getType().toString()));
        }
        return apps;
    }


    private class AppClickListener implements AdapterView.OnItemClickListener {


        private AppsAdapter apps;

        private AppClickListener(AppsAdapter apps) {
            this.apps = apps;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            try {
                AppStarter appStarter = (AppStarter) Class.forName("es.juandavidvega.conect2app.launcher.appsmanager." + apps.getItem(position).getType() + "AppStarter").newInstance();
                Log.d("APP", apps.getItem(position).getId());
                appStarter.start(configurable, apps.getItem(position).getId());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }


        }
    }
}
