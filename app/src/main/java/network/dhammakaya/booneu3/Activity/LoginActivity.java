package network.dhammakaya.booneu3.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linecorp.linesdk.auth.LineLoginApi;
import com.linecorp.linesdk.auth.LineLoginResult;

import network.dhammakaya.booneu3.Line.Constants;
import network.dhammakaya.booneu3.R;
import network.dhammakaya.booneu3.View.CustomTextView;

public class LoginActivity extends Activity implements View.OnClickListener{

    private LinearLayout li_skip;
    private LinearLayout btn_login_line;
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initListener();

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != REQUEST_CODE) {
            Log.e("ERROR", "Unsupported Request");
            return;
        }

        LineLoginResult result = LineLoginApi.getLoginResultFromIntent(data);

        switch (result.getResponseCode()) {

            case SUCCESS:

                Intent transitionIntent = new Intent(this, MainActivity.class);
                transitionIntent.putExtra("line_profile", result.getLineProfile());
                transitionIntent.putExtra("line_credential", result.getLineCredential());
                startActivity(transitionIntent);
                break;

            case CANCEL:
                Log.e("ERROR", "LINE Login Canceled by user!!");
                break;

            default:
                Log.e("ERROR", "Login FAILED!");
                Log.e("ERROR", result.getErrorData().toString());
        }
    }

    private void initView() {
        li_skip = (LinearLayout) findViewById(R.id.li_skip);
        btn_login_line = (LinearLayout) findViewById(R.id.btn_login_line);
    }

    private void initListener() {
        li_skip.setOnClickListener(this);
        btn_login_line.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == li_skip) {
            finish();
            Intent pageMain = new Intent(this, MainActivity.class);
            startActivity(pageMain);
        }
        if(v == btn_login_line) {
            try {
                // App to App Login
                Intent LoginIntent = LineLoginApi.getLoginIntent(v.getContext(), Constants.CHANNEL_ID);
                startActivityForResult(LoginIntent, REQUEST_CODE);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }
        }
    }
}
