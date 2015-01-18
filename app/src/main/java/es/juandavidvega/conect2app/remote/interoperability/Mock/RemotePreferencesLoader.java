package es.juandavidvega.conect2app.remote.interoperability.Mock;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import es.juandavidvega.conect2app.remote.interoperability.HttpJson;
import es.juandavidvega.conect2app.remote.interoperability.RemoteResources;
import es.juandavidvega.conect2app.remote.model.AppPreview;
import es.juandavidvega.conect2app.remote.model.RemotePreferences;
import es.juandavidvega.conect2app.remote.persistence.CustomAppsLoader;

public class RemotePreferencesLoader implements es.juandavidvega.conect2app.remote.interoperability.RemotePreferencesLoader {
    @Override
    public RemotePreferences load() {
        RemotePreferences result = new RemotePreferences();
        Gson json = new Gson();
        Type appsListType = new TypeToken<ArrayList<AppPreview>>(){}.getType();
        result.setApps((List<AppPreview>) json.fromJson(new HttpJson().get(RemoteResources.APPS_URL), appsListType));
        return result;
    }
}
