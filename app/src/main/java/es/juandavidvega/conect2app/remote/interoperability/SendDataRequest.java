package es.juandavidvega.conect2app.remote.interoperability;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JuanDavid on 26/05/2015.
 */
public class SendDataRequest extends Request<JSONObject> {

    private final String data;
    private Response.Listener responseListener;
    private Response.ErrorListener errorListener;

    public SendDataRequest(int method, String url, String data, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.responseListener = responseListener;
        this.errorListener = errorListener;
        this.data = data;
    }


    @Override
    public String getBodyContentType() {
        return "application/json; charset=utf-8";
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse networkResponse) {
        String jsonString = null;
        jsonString = new String(networkResponse.data, Charset.forName("UTF-8"));
        try {
            return Response.success(new JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (JSONException e) {
            e.printStackTrace();
            errorListener.onErrorResponse(new VolleyError(e));
            return null;
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        responseListener.onResponse(response);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json; charset=utf-8");
        return headers;
    }

    @Override
    public byte[] getBody() {
        return data.getBytes(Charset.forName("UTF-8"));
    }
}
