package es.juandavidvega.conect2app.launcher;

import android.os.Bundle;
import android.os.Handler;
import android.widget.GridView;
import android.widget.TextView;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import es.juandavidvega.conect2app.launcher.adapter.AppsAdapter;
import es.juandavidvega.conect2app.launcher.widgets.WidgetContainer;
import es.juandavidvega.conect2app.remote.configure.Configurable;
import es.juandavidvega.conect2app.launcher.widgets.AppsWidget;
import es.juandavidvega.conect2app.launcher.widgets.ClockWidget;
import es.juandavidvega.conect2app.remote.configure.Configurator;
import es.juandavidvega.conect2app.remote.configure.HomeConfigurator;

public class HomeActivity extends Configurable {

    private WidgetContainer myWidgets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configurator configurator = new HomeConfigurator(this);
        configurator.configureView(R.layout.home_layout);


        /*
        final List<AppPreview> apps = new AllAppsLoader(getApplicationContext()).load();
    appsWidget.setItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
            Intent i = getPackageManager().getLaunchIntentForPackage(apps.get(pos).getName());
            Log.d("APP", apps.get(pos).getName());
            HomeActivity.this.startActivity(i);
        }
    });
    */
    }


    @Override
    public void hasBeenConfigured(WidgetContainer viewWidgets) {
        myWidgets = viewWidgets;
    }
}
