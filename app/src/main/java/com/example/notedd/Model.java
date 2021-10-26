package com.example.notedd;

public class Model {
    String title;
    String subtitle;
    String note;

    public Model(String title, String subtitle, String note) {
        this.title = title;
        this.subtitle = subtitle;
        this.note = note;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
