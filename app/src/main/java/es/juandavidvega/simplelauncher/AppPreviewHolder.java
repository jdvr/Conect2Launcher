package es.juandavidvega.simplelauncher;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import es.juandavidvega.simplelauncher.holder.AppPreview;

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
        Log.d("Fil", label.getText() + "");
        icon.setImageDrawable(appPreview.getIcon());
    }

    public View getView() {
        return view;
    }
}
