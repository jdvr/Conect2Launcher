package es.juandavidvega.conect2app.launcher;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.juandavidvega.conect2app.launcher.widgets.AppsWidget;
import es.juandavidvega.conect2app.launcher.widgets.WidgetContainer;
import es.juandavidvega.conect2app.remote.configure.Configurable;
import es.juandavidvega.conect2app.remote.configure.Configurator;
import es.juandavidvega.conect2app.remote.configure.HomeConfigurator;
import es.juandavidvega.conect2app.remote.model.AppPreview;

public class HomeActivity extends Configurable {

    private WidgetContainer myWidgets;
    private ConfigurationReciever stateListener;
    private Handler handler;
    private ServiceConnection myServiceConnection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkLogin();
        Configurator configurator = new HomeConfigurator(this);
        configurator.configureView(R.layout.home_layout);
        myServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                stateListener = ((ConfigurationReciever.MyBinder) service).getService();
                stateListener.setHome(HomeActivity.this);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                stateListener = null;
            }
        };

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                HomeActivity.this.updateData(new ArrayList<AppPreview>());
            }
        };
    }

    @Override
    protected void onResume() {

        Log.d("activity", "onResume");
        if (stateListener == null) {
            bindConfigurationProcessor();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {

        Log.d("activity", "onPause");
        if (stateListener != null) {
            unbindService(myServiceConnection);
            myServiceConnection= null;
        }
        super.onPause();
    }

    public void bindConfigurationProcessor() {
        Intent intent = new Intent(this, ConfigurationReciever.class);
        Messenger messenger = new Messenger(handler);
        intent.putExtra("MESSENGER", messenger);
        bindService(intent, myServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private void checkLogin() {
        if(getUser() != null) return;
        askCredentials();
    }

    private void askCredentials() {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    @Override
    public void hasBeenConfigured(WidgetContainer viewWidgets) {
        myWidgets = viewWidgets;
    }

    @Override
    public void updateData(List<AppPreview> newData) {
        AppsWidget appsWidget = (AppsWidget) myWidgets.getWidgets().get(1);
        appsWidget.getAppsAdpater().setApps(newData);
        appsWidget.update();
    }

    public String getUser() {
        return getSharedPreferences("credentials", Context.MODE_PRIVATE).getString("phone", null);
    }
}
