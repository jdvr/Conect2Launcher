package es.juandavidvega.simplelauncher;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AppsListActivity extends Activity {

    private PackageManager manager;
    private List<AppPreviewHolder> apps;
    private GridView appsGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apps_list_layout);
        loadApps();
        loadListView();
        addClickListener();
    }

    private void loadListView(){
        appsGrid = (GridView)findViewById(R.id.lv_apps);
        ArrayAdapter<AppPreviewHolder> adapter = new ArrayAdapter<AppPreviewHolder>(this,
                R.layout.app_preview,
                apps) {
            private boolean[] hasBeenAnimated;
            private int currentPosition = 0;
            {
                hasBeenAnimated = new boolean[apps.size()];
            }
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView == null){
                    convertView = getLayoutInflater().inflate(R.layout.app_preview, null);
                }

                ImageView appIcon = (ImageView)convertView.findViewById(R.id.iv_app_icon);
                appIcon.setImageDrawable(apps.get(position).icon);

                TextView appLabel = (TextView)convertView.findViewById(R.id.tv_app_label);
                appLabel.setText(apps.get(position).label);
                if (! hasBeenAnimated[position]) {
                    Animation entranceAnim = AnimationUtils.loadAnimation(AppsListActivity.this, R.anim.bounce);
                    entranceAnim.setStartOffset(200 + 200 * position);
                    convertView.startAnimation(entranceAnim);
                    hasBeenAnimated[position] =true;
                }
                return convertView;
            }
        };

        appsGrid.setAdapter(adapter);
    }

    private void addClickListener(){

        appsGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos,
                                    long id) {
                Intent i = manager.getLaunchIntentForPackage(apps.get(pos).name.toString());
                Log.d("APP", apps.get(pos).name.toString());
                AppsListActivity.this.startActivity(i);
            }
        });
    }


    private void loadApps(){
        manager = getPackageManager();
        apps = new ArrayList<AppPreviewHolder>();

        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> availableActivities = manager.queryIntentActivities(i, 0);
        for(ResolveInfo ri:availableActivities){
            AppPreviewHolder app = new AppPreviewHolder();
            app.label = ri.loadLabel(manager);
            app.name = ri.activityInfo.packageName;
            app.icon = ri.activityInfo.loadIcon(manager);
            apps.add(app);
        }
    }
}
