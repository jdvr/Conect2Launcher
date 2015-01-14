package es.juandavidvega.conect2app.remote.persistence;

import java.util.ArrayList;
import java.util.List;

import es.juandavidvega.conect2app.remote.model.AppPreview;

public class CustoAppsLoader implements AppLoader {
    @Override
    public List<AppPreview> load() {
        List<AppPreview> result = new ArrayList<>();
        result.add(new AppPreview("com.android.calendar", "Calendario chulo", "http://cdn.flaticon.com/png/256/32203.png"));
        return result;
    }
}
