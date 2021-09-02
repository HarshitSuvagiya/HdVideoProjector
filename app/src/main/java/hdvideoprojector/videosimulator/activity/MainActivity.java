package hdvideoprojector.videosimulator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import hdvideoprojector.videosimulator.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    RelativeLayout rlProjectorTheme, rlProjectorVideo, rlProjectorGuide, rlProjectorSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bindView();
        addListner();
    }

    private void bindView() {
        rlProjectorTheme = findViewById(R.id.rlProjectorTheme);
        rlProjectorVideo = findViewById(R.id.rlProjectorVideo);
        rlProjectorGuide = findViewById(R.id.rlProjectorGuide);
        rlProjectorSetting = findViewById(R.id.rlProjectorSetting);
    }


    private void addListner() {
        rlProjectorTheme.setOnClickListener(this);
        rlProjectorVideo.setOnClickListener(this);
        rlProjectorGuide.setOnClickListener(this);
        rlProjectorSetting.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlProjectorTheme:
                loadProjectTheme();
                break;

            case R.id.rlProjectorVideo:
                loadVideoList();
                break;

            case R.id.rlProjectorGuide:
                loadGuide();
                break;

            case R.id.rlProjectorSetting:
                loadSetting();
                break;
        }
    }

    private void loadSetting() {
        Intent intent = new Intent(MainActivity.this, SettingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);


    }

    private void loadGuide() {
        Intent intent = new Intent(MainActivity.this, GuideActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void loadVideoList() {

        Intent intent = new Intent(MainActivity.this, GetVidoeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void loadProjectTheme() {
        Intent intent = new Intent(MainActivity.this, VideoThemeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

}