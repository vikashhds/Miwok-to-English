package com.example.nonu.anew;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_LOSS;

public class Colors extends AppCompatActivity {

    private MediaPlayer control;
    private AudioManager mAudioManager;
    AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                        // Permanent loss of audio focus
                        control.pause();
                        // Pause playback immediately
                        control.seekTo(0);

                    }

                    else if (focusChange == AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    }

                    else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        control.start();
                    }
                    // Your app has been granted audio focus again
                    // Raise volume to normal, restart playback if necessary
                }
            };
    private MediaPlayer.OnCompletionListener compListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_word_list);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<word> words = new ArrayList<>();
        word w = new word("One", "Lutti", R.drawable.color_black, R.raw.color_black);
        words.add(w);
        word w1 = new word("Two", "Lutti", R.drawable.color_brown, R.raw.color_brown);
        words.add(w1);
        word w2 = new word("Three", "Lutti", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow);
        words.add(w2);
        word w3 = new word("Four", "Lutti", R.drawable.color_gray, R.raw.color_gray);
        words.add(w3);
        word w4 = new word("Five", "Lutti", R.drawable.color_green, R.raw.color_green);
        words.add(w4);
        word w5 = new word("Six", "Lutti", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow);
        words.add(w5);
        word w6 = new word("Seven", "Lutti", R.drawable.color_red, R.raw.color_red);
        words.add(w6);
        word w7 = new word("Eight", "Lutti", R.drawable.color_white, R.raw.color_white);
        words.add(w7);

        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_colors);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                word syn = words.get(i);
                releaseMediaPlayer();
                // Request audio focus for playback
                int result = mAudioManager.requestAudioFocus(mAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {



                    control = MediaPlayer.create(Colors.this, syn.getPosition());
                    control.start();
                    control.setOnCompletionListener(compListener);
                }
            }
        });
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (control != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            control.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            control = null;
            mAudioManager.abandonAudioFocus(mAudioFocusChangeListener);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }


}


