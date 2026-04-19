package com.example.starsgallery.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.starsgallery.R;

/**
 * Activité de démarrage (Splash Screen)
 * Affiche une animation puis redirige vers ListActivity
 */
public class SplashActivity extends AppCompatActivity {

    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 🌑 Plein écran immersif — cache status bar & nav bar
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );

        setContentView(R.layout.activity_splash);

        logo = findViewById(R.id.logo);

        // ✨ Fade-in du logo avant l'animation de sortie
        logo.setAlpha(0f);
        logo.animate()
                .alpha(1f)
                .setDuration(800)
                .withEndAction(() -> {

                    // Animation de sortie (logique originale inchangée)
                    logo.animate()
                            .rotation(360f)
                            .scaleX(0.5f)
                            .scaleY(0.5f)
                            .translationY(800f)
                            .alpha(0f)
                            .setDuration(4000)
                            .start();

                })
                .start();

        //  Redirection après 4 secondes
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, ListActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out); // 🎬 transition douce
            finish();
        }, 4000);
    }
}