package es.juandavidvega.conect2app.remote.interoperability;


import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class RequestSender {

    private Context context;
    private ResponseHandler responseHandler;

    public RequestSender(Context context, ResponseHandler responseHandler) {
        this.context = context;
        this.responseHandler = responseHandler;
    }

    public void sendName(CreateRelationDataMapper) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://192.168.56.1:8080/;
        Log.e("SEND", "url que voy a enviar: " + url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        responseHandler.recivedResponse(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error Volley", error.toString());
                    }
                });
        queue.add(stringRequest);
    }
}
