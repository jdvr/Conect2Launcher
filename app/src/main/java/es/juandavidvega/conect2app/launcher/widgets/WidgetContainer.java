package es.juandavidvega.conect2app.launcher.widgets;

import java.util.ArrayList;
import java.util.List;

public class WidgetContainer {
    List<Widget> widgets;

    public WidgetContainer() {
        this.widgets = new ArrayList<>();
    }

    public void add(Widget widget){
        widgets.add(widget);
    }

    public List<Widget> getWidgets(){
        return widgets;
    }

    public void showAll(){
        for (Widget widget : widgets) {
            widget.makeVisible();
        }
    }
}
