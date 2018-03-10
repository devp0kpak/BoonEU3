package network.dhammakaya.booneu3.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import network.dhammakaya.booneu3.R;

public class SplashScreenActivity extends Activity {

    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);

        handler = new Handler();

        runnable = new Runnable() {
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        };

    }

    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 2000);
    }

    public void onStop() {
        super.onStop();
        handler.removeCallbacks(runnable);
    }
}
