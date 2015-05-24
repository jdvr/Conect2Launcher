package es.juandavidvega.conect2app.launcher;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import es.juandavidvega.conect2app.models.connect.view.App;
import es.juandavidvega.conect2app.models.connect.view.DeviceInfo;
import es.juandavidvega.conect2app.models.connect.view.SerializableConfiguration;
import es.juandavidvega.conect2app.remote.configure.Configurable;
import es.juandavidvega.conect2app.remote.interoperability.RequestSender;
import es.juandavidvega.conect2app.remote.interoperability.ResponseHandler;

import static es.juandavidvega.conect2app.launcher.Config.*;

public class ConfigurationReciever extends Service {


    public static final String ReadApps = "#read-apps#";
    private Configurable home;


    private final IBinder mBinder = new MyBinder();

    @Override
    public void onCreate() {
        Log.e("State", "here");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("ConfigurationService", "Start");
        if(intent.getExtras().get(MESSAGE_KEY) == null) return 1;
        String appsJson = intent.getExtras().get(MESSAGE_KEY).toString();
        Log.e("ConfigurationService", appsJson);
        if(appsJson.contains(ReadApps)) try {
            return readApps();
        } catch (JSONException e) {
            e.printStackTrace();
            return 1;
        }
        return updateApps(appsJson);
    }

    private int readApps() throws JSONException {
        DeviceInfo deviceInfo = new DeviceInfo();
        addCredentials(deviceInfo);
        deviceInfo.setApps(listInstalledApps());
        new RequestSender(getApplicationContext(), new ResponseHandler() {
            @Override
            public boolean onResponse(String response) {
                Log.e("ConfigurationReceiver", response);
                return false;
            }
        }).send(deviceInfo);
        return 1;
    }

    private App[] listInstalledApps() {
        PackageManager packageManager = getPackageManager();
        List<ApplicationInfo> installedApplications = filterInstalledApps(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));
        List<App> apps = new ArrayList<>();
        for (ApplicationInfo installedApplication : installedApplications) {
            App app = new App();
            app.setId(installedApplication.packageName);
            app.setName(String.valueOf(installedApplication.loadLabel(getPackageManager())));
            apps.add(app);
        }
        return apps.toArray(new App[apps.size()]);
    }

    private List<ApplicationInfo> filterInstalledApps(List<ApplicationInfo> installedApplications) {
        List<ApplicationInfo> applist = new ArrayList<>();
        for (ApplicationInfo info : installedApplications) {
            try {
                if (getPackageManager().getLaunchIntentForPackage(info.packageName) != null) {
                    applist.add(info);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return applist;
    }

    private void addCredentials(DeviceInfo deviceInfo) {
        SharedPreferences preferences = getSharedPreferences(PreferencesName, Context.MODE_PRIVATE);
        deviceInfo.setUser(preferences.getString("user", null));
        deviceInfo.setDevice(preferences.getString("phone", null));
    }

    private int updateApps(String appsJson) {
        SerializableConfiguration serializableConfiguration = new Gson().fromJson(appsJson, SerializableConfiguration.class);
        pushData(serializableConfiguration);
        return 1;
    }


    @Override
    public IBinder onBind(Intent arg0) {
        Log.d("service", "onBind");
        // Get messager from the Activity
        return mBinder;
    }


    public void setHome(Configurable home) {
        this.home = home;
    }

    public void pushData(SerializableConfiguration data) {
        home.updateData(data);
    }

    public class MyBinder extends Binder {
        public ConfigurationReciever getService() {
            return ConfigurationReciever.this;
        }
    }
}
