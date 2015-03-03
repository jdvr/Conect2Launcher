package es.juandavidvega.conect2app.remote.persistence;

import java.util.ArrayList;
import java.util.List;

import es.juandavidvega.conect2app.remote.interoperability.Mock.RemotePreferencesLoader;
import es.juandavidvega.conect2app.remote.model.AppPreview;





public class CustomAppsLoader implements AppLoader {
    @Override
    public List<AppPreview> load() {
        return new RemotePreferencesLoader().load().getApps();
    }
}
