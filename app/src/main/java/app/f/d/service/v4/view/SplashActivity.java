package app.f.d.service.v4.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import app.f.d.service.v4.R;
import app.f.d.service.v4.api.AppUtil;
import app.f.d.service.v4.database.AppDataBase;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        iniciarAplicativo();

    }

    private void iniciarAplicativo() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent aplicacao = new Intent(SplashActivity.this,
                        MainActivity.class);

                startActivity(aplicacao);
                finish();

            }
        }, AppUtil.PAUSE_SPLASH);

    }
}
