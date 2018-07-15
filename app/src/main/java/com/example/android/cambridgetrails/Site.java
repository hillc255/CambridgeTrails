package com.example.android.cambridgetrails;

/**
 * {@Link Site represents a vocabulary word that the user wants to learn.
 * It contains a default translation and a Miwork translation for that word} - Class Declaration
 */
public class Site {

    /**
     * Default translation for the word - State
     */
    private String mDefaultDate;

    /**
     * Miwok translation for the work - State
     */
    private String mTrailName;


    /** Image resource ID for the word */
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    /** Constant value that represents no image was provided for this word */
    private static final int NO_IMAGE_PROVIDED = -1;

    //test
    private int mAudioResourceId = NO_AUDIO_PROVIDED;
    private static final int NO_AUDIO_PROVIDED = -1;

    //  private int mAudioResourceId;

    /**
     * Constructor - create new word object
     * Setters not needed because the word object will not change
     *
     * @param vTrailName   is the Miwok word
     * @param vDefaultDate is the translated word
     */
    public Site(String vDefaultDate, String vTrailName) {
        mDefaultDate = vDefaultDate;
        mTrailName = vTrailName;
    }


    /**
     * Constructor - create new word object
     * Setters not needed because the word object will not change
     *
     * @param vTrailName   is the Miwok word
     * @param vDefaultDate is the translated word
     * @param vAudioResourceId is the audio file
     */
    public Site(String vDefaultDate, String vTrailName, int vAudioResourceId){
        mDefaultDate = vDefaultDate;
        mTrailName = vTrailName;
        mAudioResourceId = vAudioResourceId;
    }


    /**
     * Constructor2 - create new word object
     * Setters not needed because the word object will not change
     *
     * @param vTrailName   is the Miwok word
     * @param vDefaultDate is the translated word
     * @param vImageResourceId is the drawable resource for the image
     * @param vAudioResourceId
     */
    public Site(String vDefaultDate, String vTrailName, int vImageResourceId, int vAudioResourceId) {
        mDefaultDate = vDefaultDate;
        mTrailName = vTrailName;
        mImageResourceId = vImageResourceId;
        mAudioResourceId = vAudioResourceId;
    }



    /**
     * Get the default translation of the word - Getter Method
     *
     * @return String
     */
    public String getDefaultDate() {
        return mDefaultDate;
    }

    /**
     * Get Miwork translation of the word - Getter Method
     *
     * @return String
     */
    public String getTrailName() {
        return mTrailName;
    }

    /**
     * Get image resoure for the word - Getter Method
     * Return the image resource ID of the word.
     *
     * @return int
     */
    public int getImageResourceId(){
        return mImageResourceId;
    }

    /**
     * Returns whether or not there is an image for this word.
     */
    public boolean hasImage(){
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    /**
     * Get audio resource for the word - Getter Method
     * Return the audio resource ID of the word.
     *
     * @return int
     */
    public int getAudioResourceId(){
        return mAudioResourceId;
    }

    public boolean hasAudio(){
        return mAudioResourceId != NO_AUDIO_PROVIDED;
    }


}