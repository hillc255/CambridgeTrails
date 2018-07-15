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
 * A simple {@link Fragment} subclass.
 */
public class PeopleFragment extends Fragment {


//    public PeopleFragment() {
//        // Required empty public constructor
//    }

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
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
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

        /** TODO: Insert all the code from the NumberActivity’s onCreate() method after the setContentView method call */

        // Create a list of words - word object
        //
        // create word object - long way of doing this is below
        // Site w = new Site("one","lutti");
        // words.add(w)


        // Create and setup the {@link AudioManager} to request audio focus
        //fixed for Frameents  mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        // Create a list of words
        final ArrayList<Site> sites = new ArrayList<Site>();
        sites.add(new Site(getString(R.string.date1), getString(R.string.name1), R.drawable.baldwin,R.raw.color_black));
        sites.add(new Site(getString(R.string.date2), getString(R.string.name2), R.drawable.brownc, R.raw.color_black));
        sites.add(new Site(getString(R.string.date3), getString(R.string.name3), R.drawable.brownw, R.raw.color_black));
        sites.add(new Site(getString(R.string.date4), getString(R.string.name4), R.drawable.clark, R.raw.color_black));
        sites.add(new Site(getString(R.string.date5), getString(R.string.name5), R.drawable.dubois, R.raw.color_black));
        sites.add(new Site(getString(R.string.date6), getString(R.string.name6), R.drawable.duckrey, R.raw.color_black));
        sites.add(new Site(getString(R.string.date7), getString(R.string.name7), R.drawable.fatal, R.raw.color_black));
        sites.add(new Site(getString(R.string.date8), getString(R.string.name8), R.drawable.green, R.raw.color_black));
        sites.add(new Site(getString(R.string.date9), getString(R.string.name9), R.drawable.hopkins, R.raw.color_black));
        sites.add(new Site(getString(R.string.date10), getString(R.string.name10), R.drawable.jacobs, R.raw.color_black));
        sites.add(new Site(getString(R.string.date11), getString(R.string.name11), R.drawable.lane, R.raw.color_black));
        sites.add(new Site(getString(R.string.date12), getString(R.string.name12), R.drawable.lewis, R.raw.color_black));
        sites.add(new Site(getString(R.string.date13), getString(R.string.name13), R.drawable.mcguire, R.raw.color_black));
        sites.add(new Site(getString(R.string.date14), getString(R.string.name14), R.drawable.morgan, R.raw.color_black));
        sites.add(new Site(getString(R.string.date15), getString(R.string.name15), R.drawable.morris, R.raw.color_black));
        sites.add(new Site(getString(R.string.date16), getString(R.string.name16), R.drawable.raymond, R.raw.color_black));
        sites.add(new Site(getString(R.string.date17), getString(R.string.name17), R.drawable.scott, R.raw.color_black));
        sites.add(new Site(getString(R.string.date18), getString(R.string.name18), R.drawable.smith, R.raw.color_black));
        sites.add(new Site(getString(R.string.date19), getString(R.string.name19), R.drawable.stanford, R.raw.color_black));
        sites.add(new Site(getString(R.string.date20), getString(R.string.name20), R.drawable.wright, R.raw.color_black));


        // Create an {@link SiteAdapter}, whose data source is a list of {@link Site}s. The
        // adapter knows how to create list items for each item in the list.
        // SiteAdapter adapter = new SiteAdapter(this, words);
        //fix for fragments  - SiteAdapter adapter = new SiteAdapter(this, words, R.color.category_numbers);
        SiteAdapter adapter = new SiteAdapter(getActivity(), sites, R.color.category_people);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // site_list.xml file.
        //fixed for Fragment  ListView listView = (ListView) findViewById(R.id.list);
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link SiteAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Site} in the list.
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                //   String value = parent.getItemAtPosition(position).toString();

                // Get the {@link Site} object at the given position the user clicked on
                Site site = sites.get(position);

                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus now.


                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    //fix for fragment -   mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());
                    mMediaPlayer = MediaPlayer.create(getActivity(), site.getAudioResourceId());

                    // Start the audio file
                    mMediaPlayer.start();
                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }

            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();

        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

}