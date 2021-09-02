package hdvideoprojector.videosimulator.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import hdvideoprojector.videosimulator.R;


public class ActivitySplashScreen extends Activity {
    private ImageView ivImg1, ivImg2, ivImg3, ivImg4;


    private Animation alphaAnim1, alphaAnim2, alphaAnim3, alphaAnim4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        bindView();
        init();
    }

    private void bindView() {

        ivImg1 = findViewById(R.id.ivImg1);
        ivImg2 = findViewById(R.id.ivImg2);
        ivImg3 = findViewById(R.id.ivImg3);
        ivImg4 = findViewById(R.id.ivImg4);
        // rlSplash = findViewById(R.id.rlSplash);
    }

    private void splAnimation() {
        alphaAnim1 = AnimationUtils.loadAnimation(ActivitySplashScreen.this,
                R.anim.alpha_1);
        alphaAnim2 = AnimationUtils.loadAnimation(ActivitySplashScreen.this,
                R.anim.alpha_2);
        alphaAnim3 = AnimationUtils.loadAnimation(ActivitySplashScreen.this,
                R.anim.alpha_3);
        alphaAnim4 = AnimationUtils.loadAnimation(ActivitySplashScreen.this,
                R.anim.alpha_4);

        ivImg1.setVisibility(View.VISIBLE);
        ivImg1.startAnimation(alphaAnim1);

        alphaAnim1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivImg2.setVisibility(View.VISIBLE);
                ivImg2.startAnimation(alphaAnim2);
            }
        });

        alphaAnim2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivImg3.setVisibility(View.VISIBLE);
                ivImg3.startAnimation(alphaAnim3);
            }
        });

        alphaAnim3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivImg4.setVisibility(View.VISIBLE);
                ivImg4.startAnimation(alphaAnim4);
            }
        });

        alphaAnim4.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                firstScreen();
            }
        });
    }

    private void init() {
        splAnimation();
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onDestroy() {
        System.gc();
        super.onDestroy();
    }

    private void firstScreen() {
        Intent intent = new Intent(ActivitySplashScreen.this, StartActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

 }