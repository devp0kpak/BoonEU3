package network.dhammakaya.booneu3.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import network.dhammakaya.booneu3.Adapter.FavoriteAdapter;
import network.dhammakaya.booneu3.Adapter.RecyclerViewAdapter;
import network.dhammakaya.booneu3.Data.EventData;
import network.dhammakaya.booneu3.Data.FavoriteData;
import network.dhammakaya.booneu3.R;
import network.dhammakaya.booneu3.UrlInterface;
import network.dhammakaya.booneu3.View.CustomTextView;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class FavoriteActivity extends Activity implements View.OnClickListener {

    private ImageView iv_back_from_favorite;

    private RecyclerView rv_event;

    private CustomTextView empty_view;

    private String user_id;

    private ArrayList<FavoriteData> favoriteData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        initView();
        initListener();
        getStringFromFile();
    }

    @Override
    public void onResume() {
        super.onResume();
        feedData();
    }

    private void getStringFromFile() {
        SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
        user_id = f_data.getString("user_id","null");
    }

    private void feedData() {
        new FeedAsyn().execute(UrlInterface.BASE_URL + "query_favorite.php?user_id=" + user_id);
    }

    private void initView() {
        iv_back_from_favorite = (ImageView) findViewById(R.id.iv_back_from_favorite);
        rv_event = (RecyclerView) findViewById(R.id.rv_event);
        empty_view = (CustomTextView) findViewById(R.id.empty_view);
    }

    private void initListener() {

        iv_back_from_favorite.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == iv_back_from_favorite) {
            finish();
        }
    }

    private class FeedAsyn extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                OkHttpClient _OkHttpClient = new OkHttpClient();

                Request _request = new Request.Builder().url(strings[0]).get().build();

                okhttp3.Response _response = _OkHttpClient.newCall(_request).execute();

                String _result = _response.body().string();

                Gson _gson = new Gson();

                Type type = new TypeToken<List<FavoriteData>>() {}.getType();

                favoriteData = _gson.fromJson(_result, type);

                return "successfully";

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            Integer eventCount = favoriteData.size();

            if (result != null){
                if(eventCount.equals(0)){
                    rv_event.setVisibility(View.GONE);
                    empty_view.setVisibility(View.VISIBLE);
                } else {
                    rv_event.setVisibility(View.VISIBLE);
                    empty_view.setVisibility(View.GONE);
                    rv_event.setAdapter(new FavoriteAdapter(favoriteData,getApplicationContext()));
                }
            } else {
                Toast.makeText(getApplicationContext(), "Feed Data Failure", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
