package es.juandavidvega.conect2app.remote.interoperability;


import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import es.juandavidvega.conect2app.models.connect.view.DeviceInfo;

public class RequestSender {

    private Context context;
    private ResponseHandler responseHandler;
    //private String BaseURL = "http://juandavidvega.es:8080/connectdev/";
    public static final String BaseURL = "http://192.168.56.1:8080/";

    public RequestSender(Context context, ResponseHandler responseHandler) {
        this.context = context;
        this.responseHandler = responseHandler;
    }

    public void registerDevice(CreateRelationDataMapper createRelationDataMapper) throws JSONException {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = this.BaseURL + "api/noauth/user/add/device";
        final String data = new Gson().toJson(createRelationDataMapper);

        Log.e("SEND", "url que voy a enviar: " + url);
        Log.e("SEND", "datos enviar: " + data);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(data), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                responseHandler.onResponse(jsonObject.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("Error Volley", volleyError.toString());
            }
        });
        queue.add(jsonObjectRequest);
    }



    public void readConfiguration(String user, String device) throws JSONException {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = this.BaseURL + "api/noauth/device/configuration/"+ device +"/" + user;

        Log.e("SEND", "url que voy a enviar: " + url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                responseHandler.onResponse(jsonObject.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("Error Volley", volleyError.toString());
            }
        });
        queue.add(jsonObjectRequest);

    }

    public void send(DeviceInfo deviceInfo) throws JSONException {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = this.BaseURL + "api/noauth/device/apps";
        final String data = new Gson().toJson(deviceInfo);

        Log.e("SEND", "url que voy a enviar: " + url);
        Log.e("SEND", "datos enviar: " + data);

        Request jsonObjectRequest = new SendDataRequest(Request.Method.POST, url, data, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                responseHandler.onResponse(jsonObject.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("Error Volley", volleyError.toString());
            }
        });

        queue.add(jsonObjectRequest);

    }
}
