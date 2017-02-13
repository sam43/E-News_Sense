package com.rrmsense.enewspaperonline;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.wangyuwei.particleview.ParticleView;

public class SplashScreenActivity extends AppCompatActivity {

    ParticleView mParticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mParticle = (ParticleView) findViewById(R.id.pv_splash);
        mParticle.startAnim();
        mParticle.setOnParticleAnimListener(new ParticleView.ParticleAnimListener() {
            @Override
            public void onAnimationEnd() {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                SplashScreenActivity.this.startActivity(intent);
                finish();
            }
        });
    }
}
