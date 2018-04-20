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
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import network.dhammakaya.booneu3.Adapter.ImageEventAdapter;
import network.dhammakaya.booneu3.Adapter.RecyclerViewAdapter;
import network.dhammakaya.booneu3.Data.DotData;
import network.dhammakaya.booneu3.Data.EventData;
import network.dhammakaya.booneu3.Dates.EventDecorator;
import network.dhammakaya.booneu3.Dates.OneDayDecorator;
import network.dhammakaya.booneu3.R;
import network.dhammakaya.booneu3.View.CustomDateView;
import network.dhammakaya.booneu3.View.CustomTextView;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static network.dhammakaya.booneu3.Data.EventData.BASE_URL;

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

    private String country = "Defult";

    private CustomDateView cs;
    private Object stringFromFile;

    //get Extra Value
    private String display_name;

    private String getCountry;
    private String getCalendar;
    private String dotList;

    private ArrayList<EventData> eventData;
    private List<DotData> dotData;
    private EventData eventData_data;

    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getStringFromFile();
        initView();
        setBottomSheet();
        setCurrentDay();
        customCalendar();
        //setRecyclerView();
        initListener();
        //getExtraValue();
        //setTextFromExtra();
        setTextFromFile();
        setFlagCountry();
        callDot();
    }



    @Override
    public void onResume() {
        super.onResume();
        feedData();
    }

    private void feedData() {
        new FeedAsyn().execute(BASE_URL + "query_r1.php?country_name_en="+ getCountry +"&"+"calendar_date=" + getCalendar);
    }

    private void callDot() {
        new DotAsyn().execute(BASE_URL + "query_dot.php?country_name="+ getCountry);
    }

    private void getExtraValue() {
        display_name = getIntent().getExtras().getString("display_name", "null");
    }

    public void getStringFromFile() {
        SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
        country = f_data.getString("country", "Austria");
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
        } else {
            tv_user_id.setText("Don't login");
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

    private HashSet<CalendarDay> getCalendarDaysSet(Calendar cal1) {
        HashSet<CalendarDay> setDays = new HashSet<>();
        setDays.add(CalendarDay.from(cal1));
        return setDays;
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

        dotData = new ArrayList<>();
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

            Integer eventCount = eventData.size();

            if (result != null){
                if(eventCount.equals(0)){
                    rv_event.setVisibility(View.GONE);
                    empty_view.setVisibility(View.VISIBLE);
                } else {
                    rv_event.setVisibility(View.VISIBLE);
                    empty_view.setVisibility(View.GONE);
                    rv_event.setAdapter(new RecyclerViewAdapter(eventData,getApplicationContext()));
                }
            } else {
                Toast.makeText(getApplicationContext(), "Feed Data Failure", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class DotAsyn extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {

                OkHttpClient _OkHttpClient = new OkHttpClient();

                Request _request = new Request.Builder().url(strings[0]).get().build();

                okhttp3.Response _response = _OkHttpClient.newCall(_request).execute();

                JSONArray array = new JSONArray(_response.body().string());

                for (int i=0; i<array.length();i++) {

                    JSONObject object = array.getJSONObject(i);

                    DotData data = new DotData(
                            object.getString("r1_id"),
                            object.getString("country_name_en"),
                            object.getString("calendar_date")
                    );

                    dotData.add(data);

                    dotList = object.getString("calendar_date");
                    int Y = Integer.parseInt(CustomDateView.setY(dotList));
                    int M = Integer.parseInt(CustomDateView.setM(dotList));
                    int D = Integer.parseInt(CustomDateView.setD(dotList));

                    Calendar cal1 = Calendar.getInstance();
                    cal1.set(Y,M,D);

                    HashSet<CalendarDay> setDays = getCalendarDaysSet(cal1);

                    int myColor = R.color.color_point_span;

                    mcv.addDecorator(new EventDecorator(myColor,setDays,getApplicationContext()));

                }

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
        }
    }
}
