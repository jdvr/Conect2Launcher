package es.juandavidvega.conect2app.launcher;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import es.juandavidvega.conect2app.remote.configure.Configurable;
import es.juandavidvega.conect2app.remote.interoperability.HttpJson;
import es.juandavidvega.conect2app.remote.model.AppPreview;

import static es.juandavidvega.conect2app.launcher.Config.*;

public class ConfigurationReciever extends Service {


    private Configurable home;


    private final IBinder mBinder = new MyBinder();

    @Override
    public void onCreate() {
        Log.e("State", "here");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("here", "there");
        String AppsJson = intent.getExtras().get(MESSAGE_KEY).toString();
        Type appsListType = new TypeToken<ArrayList<AppPreview>>(){}.getType();
        pushData((List<AppPreview>) new Gson().fromJson(AppsJson, appsListType));
        return 1;
    }


    @Override
    public IBinder onBind(Intent arg0) {
        Bundle extras = arg0.getExtras();
        Log.d("service", "onBind");
        // Get messager from the Activity
        return mBinder;
    }


    public void setHome(Configurable home) {
        this.home = home;
    }

    public void pushData(List<AppPreview> data) {
        home.updateData(data);
    }

    public class MyBinder extends Binder {
        public ConfigurationReciever getService() {
            return ConfigurationReciever.this;
        }
    }
}
