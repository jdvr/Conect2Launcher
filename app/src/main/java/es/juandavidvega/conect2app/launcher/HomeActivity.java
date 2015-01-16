package es.juandavidvega.conect2app.launcher;

import android.os.Bundle;
import android.os.Handler;
import android.widget.GridView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
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

    }




    @Override
    public void hasBeenConfigured(WidgetContainer viewWidgets) {
        myWidgets = viewWidgets;
    }
}
