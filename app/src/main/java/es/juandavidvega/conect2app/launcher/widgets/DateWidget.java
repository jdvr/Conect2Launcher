package es.juandavidvega.conect2app.launcher.widgets;

import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.view.View.VISIBLE;

public class DateWidget implements Widget {

    private TextView date;
    private final SimpleDateFormat format = new SimpleDateFormat("d 'de' MMMM");

    public DateWidget(TextView date) {
        this.date = date;
    }

    @Override
    public void update() {
        date.setText(format.format(new Date()));
    }

    @Override
    public void makeVisible() {
        date.setVisibility(VISIBLE);
    }

    public void setVisibility(boolean visibility) {
        int newVisibility = visibility ? View.VISIBLE : View.GONE;
        date.setVisibility(newVisibility);

    }
}
