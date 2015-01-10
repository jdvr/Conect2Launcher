package es.juandavidvega.simplelauncher.widgets;

import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClockWidget {

    private final TextView hour;
    private final TextView minutesAndSecond;
    private Date lastDate;

    public ClockWidget(TextView hour, TextView minutesAndSecond){
        this.hour = hour;
        this.minutesAndSecond = minutesAndSecond;
    }

    public TextView getHour() {
        return hour;
    }

    public TextView getMinutesAndSecond() {
        return minutesAndSecond;
    }

    public void update(Date date) {
        this.lastDate = date;
        updateHour();
        updateSecondsAndMinutes();
    }

    private void updateSecondsAndMinutes() {
        updateField(minutesAndSecond, new SimpleDateFormat(":mm:ss"));
    }

    private void updateHour() {
        updateField(hour, new SimpleDateFormat("hh"));
    }

    private void updateField(TextView field, DateFormat format) {
        field.setText(format.format(lastDate));
    }
}
