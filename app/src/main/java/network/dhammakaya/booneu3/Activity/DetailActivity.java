 package network.dhammakaya.booneu3.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.like.LikeButton;
import com.like.OnLikeListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import network.dhammakaya.booneu3.Adapter.ContactAdapter;
import network.dhammakaya.booneu3.Adapter.ImageEventAdapter;
import network.dhammakaya.booneu3.Adapter.PhotoAdapter;
import network.dhammakaya.booneu3.Adapter.RecyclerViewAdapter;
import network.dhammakaya.booneu3.Data.ContactData;
import network.dhammakaya.booneu3.Data.EventData;
import network.dhammakaya.booneu3.Data.FavoriteData;
import network.dhammakaya.booneu3.Data.ImageEventData;
import network.dhammakaya.booneu3.R;
import network.dhammakaya.booneu3.UrlInterface;
import network.dhammakaya.booneu3.View.CustomDateView;
import network.dhammakaya.booneu3.View.CustomTextView;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import static network.dhammakaya.booneu3.Data.EventData.BASE_URL;

 public class DetailActivity extends Activity implements View.OnClickListener {

     private EventData eventData;

     private ImageView iv_btn_back;

     private CustomTextView btn_favorite;
     private CustomTextView tv_day;
     private CustomTextView tv_month;
     private CustomTextView tv_year;
     private CustomTextView tv_event_name;
     private CustomTextView tv_center_name;
     private CustomTextView tv_time;
     private CustomTextView empty_view;
     private CustomTextView empty_view_image;
     private CustomTextView btn_favorite_hide;

     private LinearLayout ll_center;
     private LinearLayout btn_ok;
     private LinearLayout btn_cancel;

     private LikeButton btn_like;

     private RecyclerView rv_image;
     private RecyclerView rv_contact;

     private String day;
     private String month;
     private String year;
     private String time_start;
     private String time_stop;
     private String country_id;
     private String center_id;
     private String event_id;
     private String user_id;
     private String r1_id;
     private String user_code;
     private String r1_id_f;

     private ArrayList<ContactData> contactData;
     private ArrayList<ImageEventData> imageEventData;
     private ArrayList<FavoriteData> favoriteData;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getExtraString();
        initView();
        initListener();
        setRecyclerViewImage();
        setRecyclerViewContact();
        setTextInterface();
        feedData();
        setFavoritButton();
    }

     private void setFavoritButton() {
         if (!user_code.equals("null")) {
             btn_favorite.setVisibility(View.VISIBLE);
         } else {
             btn_favorite.setVisibility(View.GONE);
         }
     }

     private void getExtraString() {

         eventData = getIntent().getParcelableExtra("event_data");

         SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
         user_id = f_data.getString("user_id","null");
         user_code = f_data.getString("user_code","null");

     }

     private void initView() {
         iv_btn_back = (ImageView) findViewById(R.id.iv_btn_back);

         btn_favorite = (CustomTextView) findViewById(R.id.btn_favorite);
         tv_day = (CustomTextView) findViewById(R.id.tv_day_d);
         tv_month = (CustomTextView) findViewById(R.id.tv_month);
         tv_year = (CustomTextView) findViewById(R.id.tv_year);
         tv_event_name = (CustomTextView) findViewById(R.id.tv_event_name);
         tv_center_name = (CustomTextView) findViewById(R.id.tv_center_name);
         tv_time = (CustomTextView) findViewById(R.id.tv_time);
         empty_view = (CustomTextView) findViewById(R.id.empty_view);
         empty_view_image = (CustomTextView) findViewById(R.id.empty_view_image);
         btn_favorite_hide = (CustomTextView) findViewById(R.id.btn_favorite_hide);

         ll_center = (LinearLayout) findViewById(R.id.ll_center);

         btn_like = (LikeButton) findViewById(R.id.btn_like);

         rv_image = (RecyclerView) findViewById(R.id.rv_image);
         rv_contact = (RecyclerView) findViewById(R.id.rv_contact);
     }

     private void initListener() {
         iv_btn_back.setOnClickListener(this);
         btn_favorite.setOnClickListener(this);
         ll_center.setOnClickListener(this);
         btn_favorite_hide.setOnClickListener(this);

         btn_like.setOnLikeListener(new OnLikeListener() {
             @Override
             public void liked(LikeButton likeButton) {
                 new InsertAsyn().execute(UrlInterface.BASE_URL + "insert_favorite.php?user_id=" + user_id  + "&r1_id=" + r1_id);
                 recreate();
             }

             @Override
             public void unLiked(LikeButton likeButton) {
                 recreate();
             }
         });
     }

     private void feedData() {
         new FeedAsyn().execute(BASE_URL + "query_r3.php?country_id="+ country_id +"&"+"center_id=" + center_id);
         new FeedImage().execute(BASE_URL + "query_media.php?center_id="+ center_id +"&media_group=event&media_type=image&ref="+ event_id);
         new FasvoriteAsyn().execute(BASE_URL + "query_favorite_check.php?user_id=" + user_id + "&r1_id=" +  eventData.getR1_id() );
     }

     private void setRecyclerViewImage() {
         rv_image.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
         rv_image.setHasFixedSize(true);
     }

     private void setRecyclerViewContact() {
         rv_contact.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
         rv_contact.setHasFixedSize(true);
     }

     private void setTextInterface() {
         r1_id = eventData.getR1_id();
         country_id = eventData.getCountry_id();
         center_id = eventData.getCenter_id();
         event_id = eventData.getEvent_id();
         day = CustomDateView.setDay(eventData.getCalendar_date());
         month = CustomDateView.monthThai(eventData.getCalendar_date());
         year = CustomDateView.setYear(eventData.getCalendar_date());
         time_start = CustomDateView.timeShot(eventData.getTime_start());
         time_stop = CustomDateView.timeShot(eventData.getTime_stop());
         tv_day.setText(day);
         tv_month.setText(month);
         tv_year.setText(year);
         tv_event_name.setText(eventData.getEvent_name());
         tv_center_name.setText(eventData.getCenter_name_en());
         tv_time.setText(time_start+ " - " +time_stop);
     }

     // OUT METHOD -------------------------------------------------------------

     @Override
     public void onClick(View v) {

        if (v == iv_btn_back) {
            finish();
        }

        if (v == btn_favorite) {
            //startDialogConfirmFavorite();
            new InsertAsyn().execute(UrlInterface.BASE_URL + "insert_favorite.php?user_id=" + user_id  + "&r1_id=" + r1_id);
            //Toast.makeText(DetailActivity.this, "user_id : " + user_id + "\n r1_id : " + r1_id, Toast.LENGTH_SHORT).show();
            recreate();
        }

         if (v == btn_favorite_hide) {
             new InsertAsyn().execute(UrlInterface.BASE_URL + "insert_favorite.php?user_id=" + user_id  + "&r1_id=" + r1_id);
             //Toast.makeText(DetailActivity.this, "user_id : " + user_id + "\n r1_id : " + r1_id, Toast.LENGTH_SHORT).show();
             recreate();
         }

        if (v == ll_center) {
            //Intent intentCenter = new Intent(getApplicationContext(), CenterActivity.class);
            //startActivity(intentCenter);
        }

     }

     private void startDialogConfirmFavorite() {
         AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
         View mView = getLayoutInflater().inflate(R.layout.dialog_confirm_favorite, null);
         mBuilder.setView(mView);
         final AlertDialog dialog = mBuilder.create();
         dialog.setCancelable(true);
         dialog.setCanceledOnTouchOutside(true);
         dialog.show();
         dialog.getWindow().setLayout(600, 500);

         btn_ok = (LinearLayout) mView.findViewById(R.id.btn_ok);
         btn_cancel = (LinearLayout) mView.findViewById(R.id.btn_cancel);

         btn_ok.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

             }
         });

         btn_cancel.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 dialog.dismiss();
             }
         });

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

                 Type type = new TypeToken<List<ContactData>>() {}.getType();

                 contactData = _gson.fromJson(_result, type);

                 return "successfully";

             } catch (IOException e) {
                 e.printStackTrace();
             }

             return null;
         }

         @Override
         protected void onPostExecute(String result) {
             super.onPostExecute(result);

             Integer contactCount = contactData.size();

             if (result != null){
                 if(contactCount.equals(0)){
                     rv_contact.setVisibility(View.GONE);
                     empty_view.setVisibility(View.VISIBLE);
                 } else {
                     rv_contact.setVisibility(View.VISIBLE);
                     empty_view.setVisibility(View.GONE);
                     rv_contact.setAdapter(new ContactAdapter(contactData,getApplicationContext()));
                 }
             } else {
                 Toast.makeText(getApplicationContext(), "Feed Data Failure", Toast.LENGTH_SHORT).show();
             }
         }
     }

     private class FeedImage extends AsyncTask<String, Void, String> {

         @Override
         protected String doInBackground(String... strings) {
             try {
                 OkHttpClient _OkHttpClient = new OkHttpClient();

                 Request _request = new Request.Builder().url(strings[0]).get().build();

                 okhttp3.Response _response = _OkHttpClient.newCall(_request).execute();

                 String _result = _response.body().string();

                 Gson _gson = new Gson();

                 Type type = new TypeToken<List<ImageEventData>>() {}.getType();

                 imageEventData = _gson.fromJson(_result, type);

                 return "successfully";

             } catch (IOException e) {
                 e.printStackTrace();
             }

             return null;
         }

         @Override
         protected void onPostExecute(String result) {
             super.onPostExecute(result);

             Integer imageEventCount = imageEventData.size();
             Toast.makeText(getApplicationContext(), "country_id : " + country_id + "\n center_id : " + center_id + "\n event_id : " + event_id, Toast.LENGTH_SHORT).show();

             if (result != null){
                 if(imageEventCount.equals(0)){
                     rv_image.setVisibility(View.GONE);
                     empty_view_image.setVisibility(View.VISIBLE);
                 } else {
                     rv_image.setVisibility(View.VISIBLE);
                     empty_view_image.setVisibility(View.GONE);
                     rv_image.setAdapter(new ImageEventAdapter(imageEventData,getApplicationContext()));
                 }
             } else {
                 Toast.makeText(getApplicationContext(), "Feed Data Failure", Toast.LENGTH_SHORT).show();
             }
         }
     }

     public class InsertAsyn extends AsyncTask<String, Void, String> {

         @Override
         protected String doInBackground(String... strings) {

             try {

                 OkHttpClient _okHttpClient = new OkHttpClient();
                 RequestBody _requestBody = new FormBody.Builder()
                         .add("user_id", user_id)
                         .add("r1_id", r1_id)
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

     private class FasvoriteAsyn extends AsyncTask<String, Void, String> {

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

             Integer favoriteCount = favoriteData.size();

             if (result != null){
                 if(favoriteCount.equals(0)){
                        //btn_like.setLiked(false);
                     btn_favorite.setVisibility(View.VISIBLE);
                     btn_favorite_hide.setVisibility(View.GONE);
                 } else {
                        //btn_like.setLiked(true);
                     btn_favorite.setVisibility(View.GONE);
                     btn_favorite_hide.setVisibility(View.VISIBLE);
                 }
             } else {
                 btn_favorite.setText("Error");
             }
         }
     }

 }
