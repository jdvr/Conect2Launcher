package es.juandavidvega.conect2app.widgets;

import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClockWidget {

    private final TextView hour;
    private final TextView minutes;
    private Date lastDate;

    public ClockWidget(TextView hour, TextView minutes){
        this.hour = hour;
        this.minutes = minutes;
    }

    public TextView getHour() {
        return hour;
    }

    public TextView getMinutes() {
        return minutes;
    }

    public void update(Date date) {
        this.lastDate = date;
        updateHour();
        updateSecondsAndMinutes();
    }

    private void updateSecondsAndMinutes() {
        updateField(minutes, new SimpleDateFormat(":mm"));
    }

    private void updateHour() {
        updateField(hour, new SimpleDateFormat("hh"));
    }

    private void updateField(TextView field, DateFormat format) {
        field.setText(format.format(lastDate));
    }
}
