package hdvideoprojector.videosimulator.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import hdvideoprojector.videosimulator.R;


public class GuideActivity extends AppCompatActivity {
    TextView textView;
    Spanned spanned;
    Toolbar tbGuide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);


        bindView();
        init();
        addListner();
    }

    private void bindView() {
        tbGuide = findViewById(R.id.tbGuide);
    }

    private void init() {
        tbGuide.setTitle("How Projectors Work");
        tbGuide.setTitleTextColor(getResources().getColor(R.color.black));
        tbGuide.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        setSupportActionBar(tbGuide);

        tbGuide.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        if (Build.VERSION.SDK_INT >= 24) {
            ((TextView) findViewById(R.id.text_data1)).setText(Html.fromHtml(getString(R.string.text1), 63));
            ((TextView) findViewById(R.id.text_data2)).setText(Html.fromHtml(getString(R.string.text2), 63));
            textView = (TextView) findViewById(R.id.text_data3);
            spanned = Html.fromHtml(getString(R.string.text3), 63);
        } else {
            ((TextView) findViewById(R.id.text_data1)).setText(Html.fromHtml(getString(R.string.text1)));
            ((TextView) findViewById(R.id.text_data2)).setText(Html.fromHtml(getString(R.string.text2)));
            textView = (TextView) findViewById(R.id.text_data3);
            spanned = Html.fromHtml(getString(R.string.text3));
        }
        textView.setText(spanned);

    }

    private void addListner() {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(GuideActivity.this, MainActivity.class));
    }
}