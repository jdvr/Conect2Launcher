package es.juandavidvega.conect2app.launcher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import es.juandavidvega.conect2app.launcher.R;
import es.juandavidvega.conect2app.launcher.adapter.AppsAdapter;
import es.juandavidvega.conect2app.remote.model.AppPreview;
import es.juandavidvega.conect2app.remote.persistence.AllAppsLoader;
import es.juandavidvega.conect2app.launcher.widgets.AppsWidget;
import es.juandavidvega.conect2app.launcher.widgets.ClockWidget;

public class HomeActivity extends Activity{

    private ClockWidget clockWidget;
    private AppsWidget appsWidget;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        createWidgets();
        loadRemotePreferences();

        final List<AppPreview> apps = new AllAppsLoader(this).load();
        appsWidget.setItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                Intent i = getPackageManager().getLaunchIntentForPackage(apps.get(pos).getName());
                Log.d("APP", apps.get(pos).getName());
                HomeActivity.this.startActivity(i);
            }
        });
    }

    private void loadRemotePreferences() {
        createWidgetsContainer();
    }

    private void createWidgetsContainer() {

    }

    private void createWidgets() {
        createClockWidget();
        createAppsWidget();
    }

    private void createAppsWidget() {
        appsWidget = new AppsWidget((GridView) findViewById(R.id.gv_apps),
                new AppsAdapter(this, R.layout.app_preview));
    }

    private void createClockWidget() {
        clockWidget = new ClockWidget((TextView) findViewById(R.id.tv_hour), (TextView) findViewById(R.id.tv_minutes_seg));
        startSchudleForClock();
    }

    private void startSchudleForClock() {
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            clockWidget.setLastDate(new Date());
                            clockWidget.update();
                        }
                        catch (Exception e) {
                            // TODO Auto-generated catch block
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 5000);
    }
}
