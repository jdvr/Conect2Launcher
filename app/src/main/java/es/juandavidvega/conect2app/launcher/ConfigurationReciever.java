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

import es.juandavidvega.conect2app.models.connect.model.Item;
import es.juandavidvega.conect2app.models.connect.view.SerializableConfiguration;
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
        Log.e("ConfigurationService", "Start");
        if(intent.getExtras().get(MESSAGE_KEY) == null) return 1;
        String appsJson = intent.getExtras().get(MESSAGE_KEY).toString();
        Log.e("ConfigurationService", appsJson);
        SerializableConfiguration serializableConfiguration = new Gson().fromJson(appsJson, SerializableConfiguration.class);
        pushData(bindItemToAppPreview(serializableConfiguration.getItems()));
        return 1;
    }

    private List<AppPreview> bindItemToAppPreview(Item[] items) {
        List<AppPreview> apps = new ArrayList<>();
        for (Item item : items) {
            apps.add(new AppPreview(item.getId(), item.getIconURL(), item.getType().toString()));
        }
        return apps;
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

    public void pushData(List<AppPreview> data) {
        home.updateData(data);
    }

    public class MyBinder extends Binder {
        public ConfigurationReciever getService() {
            return ConfigurationReciever.this;
        }
    }
}
