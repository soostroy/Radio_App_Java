package com.example.onlineradio;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public ImageButton imageButton;
    public boolean isImage1 = true;
    public SeekBar seekBar;

    public Button playButton;
    public Button classicbutton;
    public Button newsbutton;
    public Button popbutton;
    public Button folklorebutton;

    public Button back;
    public MediaPlayer mediaPlayer;
    public AudioManager audioManager;

    public  String streamURL ="https://icast.connectmedia.hu/5301/live.mp3";
    //public String rockurl = "http://stream.radioreklama.bg/radio1rock128";
   // public String newsurl = "http://stream002.radio.hu/mr1.mp3";
   // public String classicurl = "http://stream002.radio.hu/mr3.mp3";
  //  public String popurl = "http://dombradio.mooo.com:8000/live.mp3";
  //  public String folkloreurl = "http://stream002.radio.hu/mr7.mp3";
    public int flag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        imageButton = findViewById(R.id.imageButton);
        playButton = findViewById(R.id.playButton);
        popbutton = findViewById(R.id.popbutton);
        newsbutton = findViewById(R.id.newsbutton);
        folklorebutton = findViewById(R.id.folklorebutton);
        classicbutton = findViewById(R.id.classicbutton);
        back = findViewById(R.id.back);
        mediaPlayer = new MediaPlayer();
        int maxvolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentvolume= audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        seekBar = (android.widget.SeekBar)findViewById(R.id.volumeseekbar);
        seekBar.setMax(maxvolume);
        seekBar.setProgress(currentvolume);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isImage1) {
                    imageButton.setImageResource(R.drawable.img_2); // Change to image2
                } else {
                    imageButton.setImageResource(R.drawable.img_1); // Change to image1
                }
                isImage1 = !isImage1; // Toggle the boolean value
                if (!mediaPlayer.isPlaying()) {
                    playRadioStream();
                } else {
                    stopRadioStream();
                }
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mediaPlayer.isPlaying()) {
                    streamURL = "https://icast.connectmedia.hu/5301/live.mp3";

                } else {
                    stopRadioStream();
                    streamURL = "https://icast.connectmedia.hu/5301/live.mp3";
                    playRadioStream();

                }
            }
        });
        popbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mediaPlayer.isPlaying()) {
                    streamURL = "http://dombradio.mooo.com:8000/live.mp3";

                } else {
                    stopRadioStream();
                    streamURL = "http://dombradio.mooo.com:8000/live.mp3";
                    playRadioStream();

                }
            }
        });
        newsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mediaPlayer.isPlaying()) {
                    streamURL = "http://stream002.radio.hu/mr1.mp3";

                } else {
                    stopRadioStream();
                    streamURL = "http://stream002.radio.hu/mr1.mp3";
                    playRadioStream();

                }
            }
        });
        classicbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mediaPlayer.isPlaying()) {
                    streamURL = "http://stream002.radio.hu/mr3.mp3";

                } else {
                    stopRadioStream();
                    streamURL = "http://stream002.radio.hu/mr3.mp3";
                    playRadioStream();

                }
            }
        });
        folklorebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mediaPlayer.isPlaying()) {
                    streamURL = "http://stream002.radio.hu/mr7.mp3";

                } else {
                    stopRadioStream();
                    streamURL = "http://stream002.radio.hu/mr7.mp3";
                    playRadioStream();

                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRadioStream();
                Intent intent = new Intent(MainActivity.this,hungary_page.class);
                startActivity(intent);
            }
        });
    }

    public void playRadioStream() {
        try {
            mediaPlayer.setDataSource(streamURL);
            mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build());
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Error playing radio stream", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void stopRadioStream() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}