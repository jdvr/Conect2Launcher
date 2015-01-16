package es.juandavidvega.conect2app.remote.persistence;

import java.util.ArrayList;
import java.util.List;

import es.juandavidvega.conect2app.remote.model.AppPreview;

public class CustoAppsLoader implements AppLoader {
    @Override
    public List<AppPreview> load() {
        List<AppPreview> result = new ArrayList<>();
        result.add(new AppPreview("com.android.calendar", "Calendario chulo", "http://juandavidvega.es/simple/Calendario.png"));
        result.add(new AppPreview("com.android.dialer", "Telfonossd", "http://juandavidvega.es/simple/llamar.png"));
        result.add(new AppPreview("com.android.contacts", "Contacticos", "http://juandavidvega.es/simple/Contactos.png"));
        result.add(new AppPreview("com.android.deskclock", "Alarma", "http://juandavidvega.es/simple/Alarma.png"));
        return result;
    }
}
