package hdvideoprojector.videosimulator.activity;

import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import hdvideoprojector.videosimulator.R;

public class SettingActivity extends AppCompatActivity {
    private AudioManager audioManager = null;
    SeekBar sbVolume;

    TextView tvDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_dialog);



        bindView();
        init();
        addListner();
    }

    private void bindView() {
        sbVolume = findViewById(R.id.sbVolume);
        tvDone = findViewById(R.id.tvDone);
    }


    private void init() {
        audioManager = (AudioManager) SettingActivity.this.getSystemService(AUDIO_SERVICE);

        sbVolume.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        sbVolume.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
    }

    private void addListner() {
        sbVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                        progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        tvDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SettingActivity.this, MainActivity.class));
    }

 }