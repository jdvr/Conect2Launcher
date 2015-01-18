package es.juandavidvega.conect2app.remote.interoperability;

public class HttpJson {
    public String get(String appsUrl) {
        return "[" +
                "    {" +
                "        \"app_name\": \"Calendario chulo\"," +
                "        \"app_img\": \"http://juandavidvega.es/simple/Calendario.png\"," +
                "        \"app_pkg\": \"com.android.calendar\"," +
                "        \"app_type\": \"Package\"" +
                "    }," +
                "    {" +
                "        \"app_name\": \"Contactos chulo\"," +
                "        \"app_img\": \"http://juandavidvega.es/simple/Contactos.png\"," +
                "        \"app_pkg\": \"com.android.contacts\"," +
                "        \"app_type\": \"Package\"" +
                "    }," +
                "    {" +
                "        \"app_name\": \"Telefono chulo\"," +
                "        \"app_img\": \"http://juandavidvega.es/simple/llamar.png\"," +
                "        \"app_pkg\": \"android.intent.action.DIAL\"," +
                "        \"app_type\": \"Action\"" +
                "    }," +
                "    {" +
                "        \"app_name\": \"Alarma chula\"," +
                "        \"app_img\": \"http://juandavidvega.es/simple/Alarma.png\"," +
                "        \"app_pkg\": \"com.android.deskclock\"," +
                "        \"app_type\": \"Package\"" +
                "    }" +
                "]";
    }
}
