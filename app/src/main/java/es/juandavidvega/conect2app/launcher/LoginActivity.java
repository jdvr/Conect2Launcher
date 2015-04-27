package es.juandavidvega.conect2app.launcher;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.json.JSONException;

import java.io.IOException;

import es.juandavidvega.conect2app.remote.interoperability.CreateRelationDataMapper;
import es.juandavidvega.conect2app.remote.interoperability.RequestSender;
import es.juandavidvega.conect2app.remote.interoperability.ResponseHandler;
import es.juandavidvega.conect2app.remote.interoperability.ShareExternalServer;

import static es.juandavidvega.conect2app.launcher.Config.*;

public class LoginActivity extends Activity {

    private static final String TAG = "LOGIN_ACTIVITY";

    private EditText phone;
    private EditText user;
    private EditText password;
    private GoogleCloudMessaging googleCloudMessaging;
    private String registerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        configurePhoneView();
    }

    private void configurePhoneView() {
        phone = (EditText) findViewById(R.id.et_phone_number);
        phone.setText(((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getLine1Number());
        user = (EditText) findViewById(R.id.et_user_email);
        password = (EditText) findViewById(R.id.et_password);
    }

    public void useTermsViewDetails(View view) {

    }

    public void loginButtonClick(View view) throws JSONException {
        String address = registerAtGCM();
        if (address == null) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            return;
        }


    }

    private void registerAtConnect2(String address) {
        CreateRelationDataMapper createRelationDataMapper = new CreateRelationDataMapper();
        createRelationDataMapper.setAddress(address);
        createRelationDataMapper.setDevice(phone.getText().toString());
        createRelationDataMapper.setUser(user.getText().toString());
        createRelationDataMapper.setPassword(password.getText().toString());
        try {
            new RequestSender(getApplicationContext(), new ResponseHandler() {
                @Override
                public boolean recivedResponse(String response) {
                    Log.e("LOGN", "LOGIN response: " + response);
                    done();
                    return false;
                }
            }).registerDevice(createRelationDataMapper);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private String registerAtGCM() {
        googleCloudMessaging = GoogleCloudMessaging.getInstance(this);
        registerId = getRegistrationId();

        if (TextUtils.isEmpty(registerId)) {

            registerInBackground();

            Log.d(TAG,
                    "registerGCM - successfully registered with GCM server - regId: "
                            + registerId);
        } else {
            Toast.makeText(getApplicationContext(),
                    "RegId already available. RegId: " + registerId,
                    Toast.LENGTH_LONG).show();
            done();
        }
        return registerId;
    }

    private String getRegistrationId() {
        final SharedPreferences prefs = getSharedPreferences(
                "credentials", Context.MODE_PRIVATE);
        String registrationId = prefs.getString(REG_ID, "");
        if (registrationId.isEmpty()) {
            Log.i(TAG, "Registration not found.");
            return "";
        }
        int registeredVersion = prefs.getInt(APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = getAppVersion();
        if (registeredVersion != currentVersion) {
            Log.i(TAG, "App version changed.");
            return "";
        }
        return registrationId;
    }

    private int getAppVersion() {
        try {
            PackageInfo packageInfo = getPackageManager()
                    .getPackageInfo(getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.d("RegisterActivity",
                    "I never expected this! Going down, going down!" + e);
            throw new RuntimeException(e);
        }
    }

    private void registerInBackground() {
        new AsyncTask() {
            @Override
            protected String doInBackground(Object... params) {
                String msg = "";
                try {
                    if (googleCloudMessaging == null) {
                        googleCloudMessaging = GoogleCloudMessaging.getInstance(getApplicationContext());
                    }
                    registerId = googleCloudMessaging.register(GOOGLE_PROJECT_ID);
                    Log.d("RegisterActivity", "registerInBackground - regId: "
                            + registerId);
                    msg = "Device registered, registration ID=" + registerId;

                    storeRegistrationId();
                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                    Log.d(TAG, "Error: " + msg);
                }
                Log.d(TAG, "AsyncTask completed: " + msg);
                return msg;
            }

            @Override
            protected void onPostExecute(Object o) {
                Toast.makeText(getApplicationContext(),
                        "Registered with GCM Server." + o, Toast.LENGTH_LONG)
                        .show();

                registerAtServer();
            }
        }.execute(null, null, null);
    }

    private void storeRegistrationId() {
        final SharedPreferences prefs = getSharedPreferences("credentials", Context.MODE_PRIVATE);
        int appVersion = getAppVersion();
        Log.i(TAG, "Saving regId on app version " + appVersion);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(REG_ID, registerId);
        editor.putInt(APP_VERSION, appVersion);
        editor.commit();
    }

    private void registerAtServer() {

        final String regId = getSharedPreferences("credentials", Context.MODE_PRIVATE).getString(Config.REG_ID, null);
        if (regId == null)
            Log.d(TAG, "regId: " + null);
        Log.d(TAG, "regId: " + regId);

        final Context context = this;
        AsyncTask shareRegidTask = new AsyncTask() {
            @Override
            protected String doInBackground(Object... params) {
                registerAtConnect2(regId);
                return "";
            }

            @Override
            protected void onPostExecute(Object result) {
                Toast.makeText(getApplicationContext(), result + "",
                        Toast.LENGTH_LONG).show();

            }

        };
        shareRegidTask.execute(null, null, null);
    }

    private void done() {
        SharedPreferences.Editor editor = getSharedPreferences("credentials", Context.MODE_PRIVATE).edit();
        editor.putString("phone", String.valueOf(phone.getText()));
        editor.commit();
        finish();
    }


}
