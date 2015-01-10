package es.juandavidvega.simplelauncher.widgets;

import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.List;

import es.juandavidvega.simplelauncher.holder.AppPreview;

public class AppsWidget {

    private final GridView appsGridView;
    private final BaseAdapter appsAdpater;

    public AppsWidget(GridView appsGridView, BaseAdapter appsAdapter) {
        this.appsGridView = appsGridView;
        this.appsAdpater = appsAdapter;
        loadAppsGrid();
    }

    private void loadAppsGrid() {
        appsGridView.setAdapter(appsAdpater);
    }

    public GridView getAppsGridView() {
        return appsGridView;
    }

    public Adapter getAppsAdpater() {
        return appsAdpater;
    }

    public void setItemClickListener(AdapterView.OnItemClickListener listener){
        appsGridView.setOnItemClickListener(listener);
    }
}
