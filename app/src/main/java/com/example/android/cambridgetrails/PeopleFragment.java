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
 * A simple {@link Fragment} subclass - based on Miwok Application
 */
public class PeopleFragment extends Fragment {

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

                // Pause playback and reset player to the start of the file.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
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

        // Create a list of sites
        final ArrayList<Site> sites = new ArrayList<Site>();
        sites.add(new Site(getString(R.string.date1), getString(R.string.name1), R.drawable.baldwin,R.raw.people1));
        sites.add(new Site(getString(R.string.date2), getString(R.string.name2), R.drawable.brownc, R.raw.people2));
        sites.add(new Site(getString(R.string.date3), getString(R.string.name3), R.drawable.brownw, R.raw.people3));
        sites.add(new Site(getString(R.string.date4), getString(R.string.name4), R.drawable.clark, R.raw.people4));
        sites.add(new Site(getString(R.string.date5), getString(R.string.name5), R.drawable.dubois, R.raw.people5));
        sites.add(new Site(getString(R.string.date6), getString(R.string.name6), R.drawable.duckrey, R.raw.people6));
        sites.add(new Site(getString(R.string.date7), getString(R.string.name7), R.drawable.fatal, R.raw.people7));
        sites.add(new Site(getString(R.string.date8), getString(R.string.name8), R.drawable.green, R.raw.people8));
        sites.add(new Site(getString(R.string.date9), getString(R.string.name9), R.drawable.hopkins, R.raw.people9));
        sites.add(new Site(getString(R.string.date10), getString(R.string.name10), R.drawable.jacobs, R.raw.people10));
        sites.add(new Site(getString(R.string.date11), getString(R.string.name11), R.drawable.lane, R.raw.people11));
        sites.add(new Site(getString(R.string.date12), getString(R.string.name12), R.drawable.lewis, R.raw.people12));
        sites.add(new Site(getString(R.string.date13), getString(R.string.name13), R.drawable.mcguire, R.raw.people13));
        sites.add(new Site(getString(R.string.date14), getString(R.string.name14), R.drawable.morgan, R.raw.people14));
        sites.add(new Site(getString(R.string.date15), getString(R.string.name15), R.drawable.morris, R.raw.people15));
        sites.add(new Site(getString(R.string.date16), getString(R.string.name16), R.drawable.raymond, R.raw.people16));
        sites.add(new Site(getString(R.string.date17), getString(R.string.name17), R.drawable.scott, R.raw.people17));
        sites.add(new Site(getString(R.string.date18), getString(R.string.name18), R.drawable.smith, R.raw.people18));
        sites.add(new Site(getString(R.string.date19), getString(R.string.name19), R.drawable.stanford, R.raw.people19));
        sites.add(new Site(getString(R.string.date20), getString(R.string.name20), R.drawable.wright, R.raw.people20));


        // Create an {@link SiteAdapter}, whose data source is a list of {@link Site}s.
        SiteAdapter adapter = new SiteAdapter(getActivity(), sites, R.color.category_people);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link SiteAdapter}
        // {@link ListView} will display list items for each {@link Site} in the list.
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                // Get the {@link Site} object at the given position the user clicked on
                Site site = sites.get(position);

                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(getActivity(), site.getAudioResourceId());

                    // Start the audio file
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }

            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

}