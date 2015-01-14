package es.juandavidvega.conect2app.launcher.widgets;

import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

import es.juandavidvega.conect2app.launcher.adapter.AppsAdapter;

public class AppsWidget implements Widget {

    private GridView appsGridView;
    private BaseAdapter appsAdpater;

    public AppsWidget(GridView appsGridView, BaseAdapter appsAdapter) {
        this.appsGridView = appsGridView;
        this.appsAdpater = appsAdapter;
    }

    public void loadAppsGrid() {
        appsGridView.setAdapter(appsAdpater);
    }

    public GridView getAppsGridView() {
        return appsGridView;
    }

    public AppsAdapter getAppsAdpater() {
        return (AppsAdapter) appsAdpater;
    }

    public void setItemClickListener(AdapterView.OnItemClickListener listener){
        appsGridView.setOnItemClickListener(listener);
    }

    @Override
    public void update() {
        appsAdpater.notifyDataSetChanged();
    }

    @Override
    public void makeVisible() {
        appsGridView.setVisibility(View.VISIBLE);
    }
}
