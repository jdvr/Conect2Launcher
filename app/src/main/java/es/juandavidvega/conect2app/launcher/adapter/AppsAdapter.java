package es.juandavidvega.conect2app.launcher.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import es.juandavidvega.conect2app.launcher.holder.AppPreviewHolder;
import es.juandavidvega.conect2app.remote.model.AppPreview;

public class AppsAdapter extends BaseAdapter{

    private final Context context;
    private final int itemLayout;
    private List<AppPreview> apps;

    public AppsAdapter(Context context, int layout) {
        this.context = context;
        this.itemLayout = layout;
    }

    public void setApps(List<AppPreview> apps) {
        this.apps = apps;
    }

    @Override
    public int getCount() {
        return apps.size() > 3 ? 3 : apps.size();
    }

    @Override
    public AppPreview getItem(int position) {
        return apps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(itemLayout, null);
        }
        convertView = bindData(convertView, apps.get(position));
        return convertView;
    }

    private View bindData(View convertView, AppPreview appPreview) {
        AppPreviewHolder appPreviewHolder = new AppPreviewHolder(convertView);
        appPreviewHolder.fillView(appPreview);
        return appPreviewHolder.getView();
    }
}
