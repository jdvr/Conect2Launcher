package es.juandavidvega.conect2app.launcher.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import es.juandavidvega.conect2app.launcher.R;
import es.juandavidvega.conect2app.remote.model.AppPreview;

public class AppPreviewHolder {
    private TextView label;
    private ImageView icon;
    private View view;

    public AppPreviewHolder(View convertView) {
        label = (TextView) convertView.findViewById(R.id.tv_app_label);
        icon = (ImageView) convertView.findViewById(R.id.iv_app_icon);
        this.view = convertView;
    }

    public void fillView(AppPreview appPreview) {
        label.setText(appPreview.getLabel());
        icon.setImageDrawable(appPreview.getIcon());
    }

    public View getView() {
        return view;
    }
}
