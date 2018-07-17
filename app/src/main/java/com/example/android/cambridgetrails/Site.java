package com.example.android.cambridgetrails;

/**
 * Site class represents site objects - based on Miwok Application
 */
public class Site {

    /**
     * Default translation for the site
     */
    private String mDefaultDate;

    /**
     * Name associated with site
     */
    private String mTrailName;


    /**
     * Image resource ID for the site and constant of none exists
     */
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;


    /**
     * Audio resource ID for the site and constant of none exists
     */
    private int mAudioResourceId = NO_AUDIO_PROVIDED;
    private static final int NO_AUDIO_PROVIDED = -1;


    /**
     * Constructor - create new site object
     * Setters not needed because the site object will not change
     *
     * @param vTrailName   is the site name
     * @param vDefaultDate is the date associated with name
     */
    public Site(String vDefaultDate, String vTrailName) {
        mDefaultDate = vDefaultDate;
        mTrailName = vTrailName;
    }


    /**
     * Constructor - create new site object
     * Setters not needed because the site object will not change
     *
     * @param vTrailName       is the site name
     * @param vDefaultDate     is the site date or subtitle
     * @param vAudioResourceId is the associated audio resource
     */
    public Site(String vDefaultDate, String vTrailName, int vAudioResourceId) {
        mDefaultDate = vDefaultDate;
        mTrailName = vTrailName;
        mAudioResourceId = vAudioResourceId;
    }


    /**
     * Constructor2 - create new word object
     * Setters not needed because the word object will not change
     *
     * @param vTrailName       is the site name
     * @param vDefaultDate     is the site date or subtitle
     * @param vImageResourceId is the drawable resource for the image
     * @param vAudioResourceId is the associated audio resource
     */
    public Site(String vDefaultDate, String vTrailName, int vImageResourceId, int vAudioResourceId) {
        mDefaultDate = vDefaultDate;
        mTrailName = vTrailName;
        mImageResourceId = vImageResourceId;
        mAudioResourceId = vAudioResourceId;
    }


    /**
     * Get the default date or subtitle - Getter Method
     *
     * @return String
     */
    public String getDefaultDate() {
        return mDefaultDate;
    }

    /**
     * Get name associated with the site - Getter Method
     *
     * @return String
     */
    public String getTrailName() {
        return mTrailName;
    }

    /**
     * Get image resource for the site - Getter Method
     * Return the image resource ID of the site.
     *
     * @return int
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }

    /**
     * Returns whether or not there is an image for this site.
     */
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    /**
     * Get audio resource for the site - Getter Method
     * Return the audio resource ID of the site.
     *
     * @return int
     */
    public int getAudioResourceId() {
        return mAudioResourceId;
    }

    public boolean hasAudio() {
        return mAudioResourceId != NO_AUDIO_PROVIDED;
    }


}