package com.ferit.dfundak.fundakdoradz3;

/**
 * Created by Dora on 04/04/2017.
 */

public class Task {

    private String mTitle;
    private String mDescription;
    private String mUrgency;

    public Task(String title, String description, String urgency) {
        this.mTitle = title;
        this.mDescription = description;
        this.mUrgency = urgency;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getUrgency() {
        return mUrgency;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public void setUrgency(String urgency) {
        this.mUrgency = urgency;
    }
}
