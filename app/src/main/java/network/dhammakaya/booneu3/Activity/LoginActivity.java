package network.dhammakaya.booneu3.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linecorp.linesdk.LineApiResponse;
import com.linecorp.linesdk.auth.LineLoginApi;
import com.linecorp.linesdk.auth.LineLoginResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import network.dhammakaya.booneu3.Adapter.RecyclerViewAdapter;
import network.dhammakaya.booneu3.Data.EventData;
import network.dhammakaya.booneu3.Data.UserData;
import network.dhammakaya.booneu3.Line.Constants;
import network.dhammakaya.booneu3.R;
import network.dhammakaya.booneu3.UrlInterface;
import network.dhammakaya.booneu3.View.CustomTextView;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends Activity implements View.OnClickListener{

    private LinearLayout li_skip;
    private LinearLayout btn_login_line;
    private static final int REQUEST_CODE = 1;
    private String user_id;
    private String user_id_data;
    private String user_code;
    private String user_displayname_new;
    private ArrayList<UserData> userData;

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
                transitionIntent.putExtra("user_code", result.getLineProfile().getUserId());
                transitionIntent.putExtra("picture_url", result.getLineProfile().getPictureUrl().toString());

                user_code = result.getLineProfile().getUserId();
                user_displayname_new = result.getLineProfile().getDisplayName();

                new InsertAsyn().execute(UrlInterface.BASE_URL + "insert_user.php?user_code=" + user_code  + "&user_displayname=" + user_displayname_new);
                new FeedAsyn().execute(UrlInterface.BASE_URL + "query_user.php?user_code=" + user_code);

                SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = f_data.edit();
                editor.putString("user_code", result.getLineProfile().getUserId());
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

    public class InsertAsyn extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            try {

                OkHttpClient _okHttpClient = new OkHttpClient();
                RequestBody _requestBody = new FormBody.Builder()
                        .add("user_code", user_code)
                        .add("user_displayname", user_displayname_new)
                        .build();

                Request _request = new Request.Builder().url(strings[0]).post(_requestBody).build();

                _okHttpClient.newCall(_request).execute();

                return "successfully";

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

        }
    }

    private class FeedAsyn extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                OkHttpClient _OkHttpClient = new OkHttpClient();

                Request _request = new Request.Builder().url(strings[0]).get().build();

                okhttp3.Response response = _OkHttpClient.newCall(_request).execute();

                JSONArray array = new JSONArray(response.body().string());

                JSONObject object = array.getJSONObject(0);
                user_id_data = object.getString("user_id");

                return "successfully";

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(LoginActivity.this, "user_id : " + user_id_data, Toast.LENGTH_SHORT).show();
            SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = f_data.edit();
            editor.putString("user_id", user_id_data);
            editor.commit();
        }
    }
}
