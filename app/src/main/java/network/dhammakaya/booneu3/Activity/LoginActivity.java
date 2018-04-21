package network.dhammakaya.booneu3.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.linecorp.linesdk.LineApiResponse;
import com.linecorp.linesdk.auth.LineLoginApi;
import com.linecorp.linesdk.auth.LineLoginResult;

import network.dhammakaya.booneu3.Line.Constants;
import network.dhammakaya.booneu3.R;
import network.dhammakaya.booneu3.View.CustomTextView;

public class LoginActivity extends Activity implements View.OnClickListener{

    private LinearLayout li_skip;
    private LinearLayout btn_login_line;
    private static final int REQUEST_CODE = 1;
    private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        checkLogin();
        initView();
        initListener();

    }

    private void checkLogin() {
        SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
        user_id = f_data.getString("user_id", null);

        if(!user_id.equals("null")){
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        if(user_id.equals("null")) {
            Toast.makeText(this,"Please Login",Toast.LENGTH_SHORT).show();
        }
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
                finish();
                Intent transitionIntent = new Intent(this, MainActivity.class);
                transitionIntent.putExtra("line_profile", result.getLineProfile());
                transitionIntent.putExtra("line_credential", result.getLineCredential());
                transitionIntent.putExtra("display_name", result.getLineProfile().getDisplayName());
                transitionIntent.putExtra("status_message", result.getLineProfile().getStatusMessage());
                transitionIntent.putExtra("user_id", result.getLineProfile().getUserId());
                transitionIntent.putExtra("picture_url", result.getLineProfile().getPictureUrl().toString());

                SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = f_data.edit();
                editor.putString("user_id", result.getLineProfile().getUserId());
                editor.putString("display_name", result.getLineProfile().getDisplayName());
                editor.commit();

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
            finishActivity (0);
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("display_name","null");
            startActivity (intent);

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
