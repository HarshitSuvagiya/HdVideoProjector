package hdvideoprojector.videosimulator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import hdvideoprojector.videosimulator.adapter.ThemeAdapter;
import hdvideoprojector.videosimulator.R;


public class VideoThemeActivity extends AppCompatActivity {


    RecyclerView rvThumbList;
    ThemeAdapter themeAdapter;
    Toolbar tbSelectTheme;


    public static Integer[] videoThemeThumb = {
            R.drawable.thumb_02, R.drawable.thumb_03,
            R.drawable.thumb_04, R.drawable.thumb_05, R.drawable.thumb_06,
            R.drawable.thumb_07, R.drawable.thumb_08, R.drawable.thumb_09,
            R.drawable.thumb_10, R.drawable.thumb_11, R.drawable.thumb_12,
            R.drawable.thumb_13, R.drawable.thumb_14, R.drawable.thumb_15,
            R.drawable.thumb_16, R.drawable.thumb_17, R.drawable.thumb_18,
            R.drawable.thumb_19, R.drawable.thumb_20, R.drawable.thumb_21
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_list);



        bindview();
        init();
        addListener();
    }


    private void bindview() {
        rvThumbList = findViewById(R.id.rvThumbList);
        tbSelectTheme = findViewById(R.id.tbSelectTheme);
    }

    private void init() {
        tbSelectTheme.setTitle("Select Theme");
        tbSelectTheme.setTitleTextColor(getResources().getColor(R.color.black));
        tbSelectTheme.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(tbSelectTheme);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(VideoThemeActivity.this, 2);
        rvThumbList.setLayoutManager(gridLayoutManager);
        themeAdapter = new ThemeAdapter(VideoThemeActivity.this, videoThemeThumb);
        rvThumbList.setAdapter(themeAdapter);

     }

    private void addListener() {
        tbSelectTheme.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }



    @Override
    public void onBackPressed() {
        startActivity(new Intent(VideoThemeActivity.this, MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}