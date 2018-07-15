package com.example.android.cambridgetrails;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass based on Miwok Application in class
 */
public class TimelineFragment extends Fragment {

    /** Handles playback of all the sound files */
    private MediaPlayer mMediaPlayer;

    /** Handles audio focus when playing a sound file */
    private AudioManager mAudioManager;

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };


    /**
     * This listener gets triggered whenever the audio focus changes
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
                mMediaPlayer.stop();
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.site_list, container, false);

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);


        // Create a list of timeline sites
        final ArrayList<Site> sites = new ArrayList<Site>();
        sites.add(new Site(getString(R.string.timedate1),getString(R.string.timetopic1), R.raw.time1));
        sites.add(new Site(getString(R.string.timedate2), getString(R.string.timetopic2), R.raw.time2));
        sites.add(new Site(getString(R.string.timedate3),getString(R.string.timetopic3), R.raw.time3));
        sites.add(new Site(getString(R.string.timedate4), getString(R.string.timetopic4), R.raw.time4));
        sites.add(new Site(getString(R.string.timedate5), getString(R.string.timetopic5), R.raw.time5));
        sites.add(new Site(getString(R.string.timedate6), getString(R.string.timetopic6), R.raw.time6));
        sites.add(new Site(getString(R.string.timedate7), getString(R.string.timetopic7), R.raw.time7));
        sites.add(new Site(getString(R.string.timedate8), getString(R.string.timetopic8), R.raw.time8));


        // Create an {@link SiteAdapter}, whose data source is a list of {@link Site}s.
        SiteAdapter adapter = new SiteAdapter(getActivity(), sites, R.color.category_timeline);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        ListView listView = rootView.findViewById(R.id.list);


        // Make the {@link ListView} use the {@link SiteAdapter} created
        listView.setAdapter(adapter);

        // Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                // Get the {@link Site} object at the given position the user clicked on
                Site site = sites.get(position);

                // Request audio focus so in order to play the audio file.
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(getActivity(), site.getAudioResourceId());

                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
        return rootView;
    }

     //stop and release media player
    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {

        if (mMediaPlayer != null) {

            mMediaPlayer.release();
            mMediaPlayer = null;

               mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

}