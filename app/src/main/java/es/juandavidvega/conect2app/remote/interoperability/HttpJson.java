package es.juandavidvega.conect2app.remote.interoperability;

public class HttpJson {
    public String get(String appsUrl) {
        return "[" +
                "    {" +
                "        \"app_img\": \"http://juandavidvega.es/simple/Calendario.png\"," +
                "        \"app_id\": \"com.android.calendar\"," +
                "        \"app_type\": \"Package\"" +
                "    }," +
                "    {" +
                "        \"app_img\": \"http://juandavidvega.es/simple/Contactos.png\"," +
                "        \"app_id\": \"com.android.contacts\"," +
                "        \"app_type\": \"Package\"" +
                "    }," +
                "    {" +
                "        \"app_img\": \"http://juandavidvega.es/simple/llamar.png\"," +
                "        \"app_id\": \"android.intent.action.DIAL\"," +
                "        \"app_type\": \"Action\"" +
                "    }," +
                "    {" +
                "        \"app_img\": \"http://juandavidvega.es/simple/Alarma.png\"," +
                "        \"app_id\": \"com.android.deskclock\"," +
                "        \"app_type\": \"Package\"" +
                "    }" +
                "]";
    }
}