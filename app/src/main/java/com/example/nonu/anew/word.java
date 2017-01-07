package com.example.nonu.anew;

/**
 * Created by Nonu on 31-12-2016.
 */

public class word {
    private String mdefaultLanguage;
    private String mmiwokLanguage;
    private int mimageresourseid = CHECK;
    private static final int CHECK = -1;
    private int mposition;

    public word(String defaultLanguage, String MiwokLanguage, int position) {
        mdefaultLanguage = defaultLanguage;
        mmiwokLanguage = MiwokLanguage;
        mposition = position;

    }

    public word(String defaultLanguage, String MiwokLanguage, int ImageResourseId, int position) {
        mdefaultLanguage = defaultLanguage;
        mmiwokLanguage = MiwokLanguage;
        mimageresourseid = ImageResourseId;
        mposition = position;
    }

    public String getDefaultLanguaage() {

        return mdefaultLanguage;
    }

    public String getMiwokLanguage() {

        return mmiwokLanguage;
    }

    public int getImageresourseid() {

        return mimageresourseid;
    }

    public boolean hasImage() {
        return mimageresourseid != CHECK;

    }
    public int getPosition() {
        return mposition;
    }
}

