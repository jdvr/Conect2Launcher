package es.juandavidvega.simplelauncher.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import es.juandavidvega.simplelauncher.AppPreviewHolder;
import es.juandavidvega.simplelauncher.R;
import es.juandavidvega.simplelauncher.holder.AppPreview;

public class AppsAdapter extends BaseAdapter{

    private final Context context;
    private final int itemLayout;
    private final List<AppPreview> apps;

    public AppsAdapter(Context context, int layout, List<AppPreview> apps) {
        this.context = context;
        this.itemLayout = layout;
        this.apps = apps;
    }

    @Override
    public int getCount() {
        return apps.size();
    }

    @Override
    public Object getItem(int position) {
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
