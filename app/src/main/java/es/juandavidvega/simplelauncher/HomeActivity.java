package es.juandavidvega.simplelauncher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import es.juandavidvega.simplelauncher.adapter.AppsAdapter;
import es.juandavidvega.simplelauncher.holder.AppPreview;
import es.juandavidvega.simplelauncher.widgets.AppsWidget;
import es.juandavidvega.simplelauncher.widgets.ClockWidget;

public class HomeActivity extends Activity{

    private ClockWidget clockWidget;
    private AppsWidget appsWidget;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        clockWidget = new ClockWidget((TextView) findViewById(R.id.tv_hour), (TextView) findViewById(R.id.tv_minutes_seg));
        startSchudleForClock();
        List<AppPreview> apps = new AppLoader(this).load();
        appsWidget = new AppsWidget((GridView) findViewById(R.id.gv_apps),
                new AppsAdapter(this, R.layout.app_preview, apps ));


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
                            clockWidget.update(new Date());
                        }
                        catch (Exception e) {
                            // TODO Auto-generated catch block
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 1000);
    }
}
