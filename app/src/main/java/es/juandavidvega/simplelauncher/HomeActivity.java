package es.juandavidvega.simplelauncher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

    }

    public void ShowApps(View view) {
        startActivity(new Intent(this, AppsListActivity.class));
    }
}
