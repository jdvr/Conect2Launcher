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

import es.juandavidvega.conect2app.launcher.widgets.WidgetContainer;
import es.juandavidvega.conect2app.models.connect.view.SerializableConfiguration;
import es.juandavidvega.conect2app.remote.configure.Configurable;
import es.juandavidvega.conect2app.remote.configure.Configurator;
import es.juandavidvega.conect2app.remote.configure.HomeConfigurator;

public class HomeActivity extends Configurable {

    private WidgetContainer myWidgets;
    private ConfigurationReciever configurationReciever;
    private Handler handler;
    private ServiceConnection reciverConnection;
    Configurator configurator = new HomeConfigurator(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkLogin();
        configurator.configureView(R.layout.home_layout);

    }

    @Override
    protected void onResume() {

        Log.d("activity", "onResume");
        if (configurationReciever == null) {
            reciverConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    configurationReciever = ((ConfigurationReciever.MyBinder) service).getService();
                    configurationReciever.setHome(HomeActivity.this);
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    configurationReciever = null;
                }
            };

            handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    //HomeActivity.this.updateData(null);
                }
            };
            bindConfigurationProcessor();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {

        Log.d("activity", "onPause");
        if (configurationReciever != null) {
            unbindService(reciverConnection);
            reciverConnection = null;
            configurationReciever = null;
        }
        super.onPause();
    }

    public void bindConfigurationProcessor() {
        Intent intent = new Intent(this, ConfigurationReciever.class);
        Messenger messenger = new Messenger(handler);
        intent.putExtra("MESSENGER", messenger);
        bindService(intent, reciverConnection, Context.BIND_AUTO_CREATE);
    }

    private void checkLogin() {
        if (getUser() != null) return;
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
    public void updateData(SerializableConfiguration newData) {
        if (newData == null) return;
        configurator.update(newData);
    }

    public String getUser() {
        return getSharedPreferences("credentials", Context.MODE_PRIVATE).getString("phone", null);
    }
}
