package network.dhammakaya.booneu3.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linecorp.linesdk.api.LineApiClient;
import com.linecorp.linesdk.auth.LineLoginApi;
import com.linecorp.linesdk.auth.LineLoginResult;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import network.dhammakaya.booneu3.Adapter.RecyclerViewAdapter;
import network.dhammakaya.booneu3.Data.EventData;
import network.dhammakaya.booneu3.Dates.EventDecorator;
import network.dhammakaya.booneu3.Dates.OneDayDecorator;
import network.dhammakaya.booneu3.Line.Constants;
import network.dhammakaya.booneu3.R;
import network.dhammakaya.booneu3.UrlInterface;
import network.dhammakaya.booneu3.View.CustomDateView;
import network.dhammakaya.booneu3.View.CustomTextView;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MainActivity extends Activity implements View.OnClickListener {

    private MaterialCalendarView mcv;

    private ImageView iv_setting;
    private ImageView iv_btn_favorite;
    private ImageView im_country;
    private CustomTextView tv_country_name;
    private CustomTextView tv_day;
    private CustomTextView tv_month;
    private CustomTextView tv_year;
    private CustomTextView tv_day_e;
    private CustomTextView tv_month_e;
    private CustomTextView tv_year_e;
    private CustomTextView tv_user_id;
    private CustomTextView empty_view;

    //------ For Country -----//
    private LinearLayout btn_austria;
    private LinearLayout btn_belgium;
    private LinearLayout btn_denmark;
    private LinearLayout btn_france;
    private LinearLayout btn_germany;
    private LinearLayout btn_italy;
    private LinearLayout btn_malta;
    private LinearLayout btn_netherlands;
    private LinearLayout btn_norway;
    private LinearLayout btn_sweden;
    private LinearLayout btn_switzerland;
    private LinearLayout btn_uk;

    private LinearLayout btn_flag;

    private DateFormat dayFormat;
    private DateFormat monthFormat;
    private DateFormat yearFormat;
    private DateFormat dateFull;

    private BottomSheetDialog bottomSheetDialog;

    private RecyclerView rv_event;

    //Bottom Sheet
    private CustomTextView item_exit;
    private CustomTextView item_about;
    private CustomTextView item_line_login;
    private CustomTextView item_line_logout;

    private CustomDateView cs;
    private Object stringFromFile;

    //get Extra Value
    private String country = "Defult";
    private String display_name;
    private String user_id;
    private String getCountry;
    private String getCalendar;
    private String dotList;
    private String user_id_data;

    private ArrayList<EventData> eventData;
    private ArrayList<CalendarDay> datess;

    private EventData eventData_data;
    private static LineApiClient lineApiClient;
    private static final int REQUEST_CODE = 1;

    private AlertDialog dialog;

    private String Log_S_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getStringFromFile();
        //getExtraValue();
        initView();
        setBottomSheet();
        setCurrentDay();
        Log.e("POINT","part-2");
        customCalendar();
        //setRecyclerView();
        Log.e("POINT","part-3");
        initListener();
        Log.e("POINT","part-4");
        setTextFromExtra();
        Log.e("POINT","part-5");
        setTextFromFile();
        Log.e("POINT","part-6");
        setFlagCountry();
        Log.e("POINT","part-7");

    }

    @Override
    public void onResume() {
        super.onResume();
        feedData();
        callDate();
    }

    private void feedData() {
        new FeedAsyn().execute(UrlInterface.BASE_URL_A + "query_r1.php?country_name="+ getCountry +"&"+"calendar_date=" + getCalendar);
        Log_S_link = UrlInterface.BASE_URL_A + "query_r1.php?country_name="+ getCountry +"&"+"calendar_date=" + getCalendar;
        Log.e("LINK", Log_S_link);
    }

    private void getExtraValue() {
        user_id = getIntent().getExtras().getString("user_id", "null");
    }

    public void getStringFromFile() {
        SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
        country = f_data.getString("country", "Austria");
        user_id = f_data.getString("user_id","null");
        display_name = f_data.getString("display_name","null");
        getCountry = country;
    }

    private void setTextFromFile() {
        tv_country_name.setText(country);
    }

    @SuppressLint("ResourceAsColor")
    private void setTextFromExtra() {
        if (!display_name.equals("null")) {
            tv_user_id.setText(display_name);
            tv_user_id.setTextColor(getResources().getColor(R.color.green_500));
            tv_user_id.setBackgroundColor(getResources().getColor(R.color.white));
            tv_user_id.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            Toast.makeText(this,"user_id : "+user_id,Toast.LENGTH_SHORT).show();
            iv_btn_favorite.setVisibility(View.VISIBLE);
        } else {
            tv_user_id.setText("Not logged in");
        }

    }

    private void setFlagCountry() {
        if (country.equals("Austria")) {
            im_country.setImageResource(R.drawable.austria);
        }
        if (country.equals("Belgium")) {
            im_country.setImageResource(R.drawable.belgium);
        }
        if (country.equals("Denmark")) {
            im_country.setImageResource(R.drawable.denmark);
        }
        if (country.equals("France")) {
            im_country.setImageResource(R.drawable.france);
        }
        if (country.equals("Germany")) {
            im_country.setImageResource(R.drawable.germany);
        }
        if (country.equals("Italy")) {
            im_country.setImageResource(R.drawable.italy);
        }
        if (country.equals("Malta")) {
            im_country.setImageResource(R.drawable.malta);
        }
        if (country.equals("Netherlands")) {
            im_country.setImageResource(R.drawable.netherlands);
        }
        if (country.equals("Norway")) {
            im_country.setImageResource(R.drawable.norway);
        }
        if (country.equals("Sweden")) {
            im_country.setImageResource(R.drawable.sweden);
        }
        if (country.equals("Switzerland")) {
            im_country.setImageResource(R.drawable.switzerland);
        }
        if (country.equals("United Kingdom")) {
            im_country.setImageResource(R.drawable.united_kingdom);
        }
    }

    private void setCurrentDay() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        SimpleDateFormat dateFull = new SimpleDateFormat("yyyy-MM-dd");
        tv_day.setText(dayFormat.format(c));
        tv_month.setText(monthFormat.format(c));
        tv_year.setText(yearFormat.format(c));
        tv_day_e.setText(dayFormat.format(c));
        tv_month_e.setText(monthFormat.format(c));
        tv_year_e.setText(yearFormat.format(c));
        getCalendar = dateFull.format(c);
    }

    private void customCalendar() {

        mcv.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(CalendarDay.from(2018, 0, 0))
                .setMaximumDate(CalendarDay.from(2022, 0, 0))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        Calendar calendar = Calendar.getInstance();


        mcv.setDateSelected(calendar.getTime(), true);
        mcv.addDecorator(new OneDayDecorator());

        dayFormat = new SimpleDateFormat("d");
        monthFormat = new SimpleDateFormat("MMMM");
        yearFormat = new SimpleDateFormat("yyyy");
        dateFull = new SimpleDateFormat("yyyy-MM-dd");


        mcv.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Calendar c = date.getCalendar();
                tv_day.setText(dayFormat.format(c.getTime()));
                tv_month.setText(monthFormat.format(c.getTime()));
                tv_year.setText(yearFormat.format(c.getTime()));
                tv_day_e.setText(dayFormat.format(c.getTime()));
                tv_month_e.setText(monthFormat.format(c.getTime()));
                tv_year_e.setText(yearFormat.format(c.getTime()));
                getCalendar = dateFull.format(c.getTime());
                Toast.makeText(getApplicationContext(),getCalendar,Toast.LENGTH_SHORT).show();
                feedData();
            }
        });

    }

    private void setBottomSheet() {
        bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
        View bottomSheetView = getLayoutInflater().inflate(R.layout.view_bottomsheet_menu, null);
        bottomSheetDialog.setContentView(bottomSheetView);
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) bottomSheetView.getParent());
        bottomSheetBehavior.setPeekHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, getResources().getDisplayMetrics()));
        item_exit = (CustomTextView) bottomSheetView.findViewById(R.id.item_exit);
        item_about = (CustomTextView) bottomSheetView.findViewById(R.id.item_about);
        item_line_login = (CustomTextView) bottomSheetView.findViewById(R.id.item_line_login);
        item_line_logout = (CustomTextView) bottomSheetView.findViewById(R.id.item_line_logout);

        if (!display_name.equals("null")) {
            item_line_login.setVisibility(View.GONE);
            item_line_logout.setVisibility(View.VISIBLE);
        } else {
            item_line_logout.setVisibility(View.GONE);
            item_line_login.setVisibility(View.VISIBLE);
        }

        item_line_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
                SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = f_data.edit();
                editor.putString("user_id", "null");
                editor.putString("display_name", "null");
                editor.putString("user_code", "null");
                editor.commit();
                restartApplication();
            }
        });
    }

    private void startDialogSettingCountry() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        View mCountry = getLayoutInflater().inflate(R.layout.dialog_select_country, null);
        mBuilder.setView(mCountry);
        dialog = mBuilder.create();
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        dialog.getWindow().setLayout(900, 1100);

        btn_austria = (LinearLayout) mCountry.findViewById(R.id.btn_austria);
        btn_belgium = (LinearLayout) mCountry.findViewById(R.id.btn_belgium);
        btn_denmark = (LinearLayout) mCountry.findViewById(R.id.btn_denmark);
        btn_france = (LinearLayout) mCountry.findViewById(R.id.btn_france);
        btn_germany = (LinearLayout) mCountry.findViewById(R.id.btn_germany);
        btn_italy = (LinearLayout) mCountry.findViewById(R.id.btn_italy);
        btn_malta = (LinearLayout) mCountry.findViewById(R.id.btn_malta);
        btn_netherlands = (LinearLayout) mCountry.findViewById(R.id.btn_netherlands);
        btn_norway = (LinearLayout) mCountry.findViewById(R.id.btn_norway);
        btn_sweden = (LinearLayout) mCountry.findViewById(R.id.btn_sweden);
        btn_switzerland = (LinearLayout) mCountry.findViewById(R.id.btn_switzerland);
        btn_uk = (LinearLayout) mCountry.findViewById(R.id.btn_uk);

        btn_austria.setOnClickListener(this);
        btn_belgium.setOnClickListener(this);
        btn_denmark.setOnClickListener(this);
        btn_france.setOnClickListener(this);
        btn_germany.setOnClickListener(this);
        btn_italy.setOnClickListener(this);
        btn_malta.setOnClickListener(this);
        btn_netherlands.setOnClickListener(this);
        btn_norway.setOnClickListener(this);
        btn_sweden.setOnClickListener(this);
        btn_switzerland.setOnClickListener(this);
        btn_uk.setOnClickListener(this);
    }

    private void initView() {

        mcv = (MaterialCalendarView) findViewById(R.id.calendarView);

        btn_flag = (LinearLayout) findViewById(R.id.btn_flag);

        iv_setting = (ImageView) findViewById(R.id.iv_setting);
        iv_btn_favorite = (ImageView) findViewById(R.id.iv_btn_favorite);
        im_country = (ImageView) findViewById(R.id.im_country);

        tv_country_name = (CustomTextView) findViewById(R.id.tv_country_name);
        tv_day = (CustomTextView) findViewById(R.id.tv_day);
        tv_month = (CustomTextView) findViewById(R.id.tv_month);
        tv_year = (CustomTextView) findViewById(R.id.tv_year);
        tv_day_e = (CustomTextView) findViewById(R.id.tv_day_e);
        tv_month_e = (CustomTextView) findViewById(R.id.tv_month_e);
        tv_year_e = (CustomTextView) findViewById(R.id.tv_year_e);
        tv_user_id = (CustomTextView) findViewById(R.id.tv_user_id);
        empty_view = (CustomTextView) findViewById(R.id.empty_view);

        rv_event = (RecyclerView) findViewById(R.id.rv_event);


    }

    private void initListener() {

        btn_flag.setOnClickListener(this);
        iv_setting.setOnClickListener(this);
        iv_btn_favorite.setOnClickListener(this);
        item_exit.setOnClickListener(this);
        item_about.setOnClickListener(this);
        item_line_login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == iv_setting) {
            bottomSheetDialog.show();
        }

        if (v == btn_flag) {
            startDialogSettingCountry();
        }

        if (v == iv_btn_favorite) {
            Intent layoutFavorite = new Intent(this, FavoriteActivity.class);
            startActivity(layoutFavorite);
        }

        if (v == item_about) {
            bottomSheetDialog.dismiss();
            Intent AboutIntent = new Intent(this, AboutActivity.class);
            startActivity(AboutIntent);
        }

        if (v == item_line_login) {
            bottomSheetDialog.dismiss();
            try {
                // App to App Login
                Intent LoginIntent = LineLoginApi.getLoginIntent(v.getContext(), Constants.CHANNEL_ID);
                startActivityForResult(LoginIntent, REQUEST_CODE);
            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }
        }

        if (v == item_exit) {
            bottomSheetDialog.dismiss();
            finish();
            System.exit(0);
        }

        if (v == btn_austria) {
            dialog.dismiss();
            SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = f_data.edit();
            editor.putString("country", "Austria");
            editor.commit();
            restartApplication();
        }

        if (v == btn_belgium) {
            dialog.dismiss();
            SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = f_data.edit();
            editor.putString("country", "Belgium");
            editor.commit();
            restartApplication();
        }

        if (v == btn_denmark) {
            dialog.dismiss();
            SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = f_data.edit();
            editor.putString("country", "Denmark");
            editor.commit();
            restartApplication();
        }

        if (v == btn_france) {
            dialog.dismiss();
            SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = f_data.edit();
            editor.putString("country", "France");
            editor.commit();
            restartApplication();
        }

        if (v == btn_germany) {
            dialog.dismiss();
            SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = f_data.edit();
            editor.putString("country", "Germany");
            editor.commit();
            restartApplication();
        }

        if (v == btn_italy) {
            dialog.dismiss();
            SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = f_data.edit();
            editor.putString("country", "Italy");
            editor.commit();
            restartApplication();
        }

        if (v == btn_malta) {
            dialog.dismiss();
            SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = f_data.edit();
            editor.putString("country", "Malta");
            editor.commit();
            restartApplication();
        }

        if (v == btn_netherlands) {
            dialog.dismiss();
            SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = f_data.edit();
            editor.putString("country", "Netherlands");
            editor.commit();
            restartApplication();
        }

        if (v == btn_norway) {
            dialog.dismiss();
            SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = f_data.edit();
            editor.putString("country", "Norway");
            editor.commit();
            restartApplication();
        }

        if (v == btn_sweden) {
            dialog.dismiss();
            SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = f_data.edit();
            editor.putString("country", "Sweden");
            editor.commit();
            restartApplication();
        }

        if (v == btn_switzerland) {
            dialog.dismiss();
            SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = f_data.edit();
            editor.putString("country", "Switzerland");
            editor.commit();
            restartApplication();
        }

        if (v == btn_uk) {
            dialog.dismiss();
            SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = f_data.edit();
            editor.putString("country", "United Kingdom");
            editor.commit();
            restartApplication();
        }


    }

    private void restartApplication() {
        finish();
        Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
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

                Type type = new TypeToken<List<EventData>>() {}.getType();

                eventData = _gson.fromJson(_result, type);

                return "successfully";

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null){

                Integer eventCount = eventData.size();

                if(eventCount.equals(0)){
                    rv_event.setVisibility(View.GONE);
                    empty_view.setVisibility(View.VISIBLE);
                } else {
                    rv_event.setVisibility(View.VISIBLE);
                    empty_view.setVisibility(View.GONE);
                    rv_event.setAdapter(new RecyclerViewAdapter(eventData,getApplicationContext()));
                }

            } else {
                finish();
                Intent intent = new Intent(MainActivity.this, ErrorActivity.class);
                startActivity(intent);
            }

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
                bottomSheetDialog.dismiss();
                SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = f_data.edit();
                editor.putString("user_code", result.getLineProfile().getUserId());
                editor.putString("display_name", result.getLineProfile().getDisplayName());
                editor.commit();
                new UserAsyn().execute(UrlInterface.BASE_URL_A + "query_user.php?user_code=" + result.getLineProfile().getUserId());
                recreate();
                break;

            case CANCEL:
                Log.e("ERROR", "LINE Login Canceled by user!!");
                break;

            default:
                Log.e("ERROR", "Login FAILED!");
                Log.e("ERROR", result.getErrorData().toString());
        }
    }

    private void callDate() {
        new DateAsyn().execute(UrlInterface.BASE_URL_A + "query_dot.php?country_name="+ getCountry);
    }

    private class UserAsyn extends AsyncTask<String, Void, String> {

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

            if(result != null){

                SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = f_data.edit();
                editor.putString("user_id", user_id_data);
                editor.commit();

            } else {

                finish();
                Intent intent = new Intent(MainActivity.this, ErrorActivity.class);
                startActivity(intent);

            }

        }
    }

    public class DateAsyn extends AsyncTask<String, Void, List<CalendarDay>> {

        @Override
        protected List<CalendarDay> doInBackground(String... strings) {
            try {

                OkHttpClient _OkHttpClient = new OkHttpClient();

                Request _request = new Request.Builder().url(strings[0]).get().build();

                okhttp3.Response _response = _OkHttpClient.newCall(_request).execute();

                JSONArray array = new JSONArray(_response.body().string());

                ArrayList<CalendarDay> datese = new ArrayList<>();

                for (int i=0; i<array.length();i++) {

                    JSONObject object = array.getJSONObject(i);

                    dotList = object.getString("calendar_date");
                    int Y = Integer.parseInt(CustomDateView.setY(dotList));
                    int M = Integer.parseInt(CustomDateView.setM(dotList));
                    int D = Integer.parseInt(CustomDateView.setD(dotList));

                    Calendar cal1 = Calendar.getInstance();
                    cal1.set(Y, M, D);

                    datese.add(CalendarDay.from(cal1));

                }

                return datese;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);

            if (isFinishing()) {
                return;
            }

            int myColor = R.color.color_point_span;

            mcv.addDecorator(new EventDecorator(myColor, getApplicationContext(), calendarDays));

        }
    }
}
