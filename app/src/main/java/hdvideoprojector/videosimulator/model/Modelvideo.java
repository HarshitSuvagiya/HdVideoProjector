package hdvideoprojector.videosimulator.model;

import android.graphics.Bitmap;

public class Modelvideo {
    private String path;
    private String name;

    private String Date;
    private String duration;
    private Bitmap videothumb;
    private String size;
    private String resolution;
    private boolean isSelected = false;

    private boolean isEetraNative = false;

    public boolean isEetraNative() {
        return isEetraNative;
    }

    public void setEetraNative(boolean eetraNative) {
        isEetraNative = eetraNative;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Bitmap getVideothumb() {
        return videothumb;
    }

    public void setVideothumb(Bitmap videothumb) {
        this.videothumb = videothumb;
    }

    public Modelvideo() {

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
