package network.dhammakaya.booneu3.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import network.dhammakaya.booneu3.Adapter.RecyclerViewAdapter;
import network.dhammakaya.booneu3.Dates.OneDayDecorator;
import network.dhammakaya.booneu3.R;
import network.dhammakaya.booneu3.View.CustomDateView;
import network.dhammakaya.booneu3.View.CustomTextView;

public class MainActivity extends Activity implements View.OnClickListener{

    private MaterialCalendarView mcv;

    private ImageView iv_setting;
    private ImageView iv_btn_favorite;
    private ImageView im_country;
    private CustomTextView tv_country_name;
    private CustomTextView tv_day;
    private CustomTextView tv_month;
    private CustomTextView tv_year;
    private CustomTextView tv_user_id;

    private BottomSheetDialog bottomSheetDialog;

    private RecyclerView rv_event;

    //Bottom Sheet
    private CustomTextView item_setting;
    private CustomTextView item_line_login;

    private String date,month,year;
    private String country = "Defult";

    private CustomDateView cs;
    private Object stringFromFile;

    //get Extra Value
    private String display_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getStringFromFile();
        initView();
        setBottomSheet();
        setCurrentDay();
        initListener();
        customCalendar();
        setRecyclerView();

        //getExtraValue();
        //setTextFromExtra();

        setTextFromFile();
        setFlagCountry();

    }

    private void getExtraValue() {
        display_name = getIntent().getExtras().getString("display_name","null");
    }

    public void getStringFromFile() {
        SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
        country = f_data.getString("country", "Austria");
    }

    private void setTextFromFile() {
        tv_country_name.setText(country);
    }

    @SuppressLint("ResourceAsColor")
    private void setTextFromExtra() {
        if (!display_name.equals("null")){
            tv_user_id.setText(display_name);
            tv_user_id.setTextColor(getResources().getColor(R.color.green_500));
            tv_user_id.setBackgroundColor(getResources().getColor(R.color.white));
            tv_user_id.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
        } else {
            tv_user_id.setText("Don't login");
        }

    }

    private void setFlagCountry() {
        if(country.equals("Austria")){ im_country.setImageResource(R.drawable.austria);}
        if(country.equals("Belgium")){ im_country.setImageResource(R.drawable.belgium);}
        if(country.equals("Denmark")){ im_country.setImageResource(R.drawable.denmark);}
        if(country.equals("France")){ im_country.setImageResource(R.drawable.france);}
        if(country.equals("Germany")){ im_country.setImageResource(R.drawable.germany);}
        if(country.equals("Italy")){ im_country.setImageResource(R.drawable.italy);}
        if(country.equals("Malta")){ im_country.setImageResource(R.drawable.malta);}
        if(country.equals("Netherlands")){ im_country.setImageResource(R.drawable.netherlands);}
        if(country.equals("Norway")){ im_country.setImageResource(R.drawable.norway);}
        if(country.equals("Sweden")){ im_country.setImageResource(R.drawable.sweden);}
        if(country.equals("Switzerland")){ im_country.setImageResource(R.drawable.switzerland);}
        if(country.equals("United Kingdom")){ im_country.setImageResource(R.drawable.united_kingdom);}
    }

    private void setRecyclerView() {
        rv_event.setAdapter(new RecyclerViewAdapter(this));
    }

    private void setCurrentDay() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        tv_day.setText(dayFormat.format(c));
        tv_month.setText(monthFormat.format(c));
        tv_year.setText(yearFormat.format(c));
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

        mcv.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                DateFormat dayFormat = new SimpleDateFormat("dd");
                DateFormat monthFormat = new SimpleDateFormat("MMMM");
                DateFormat yearFormat = new SimpleDateFormat("yyyy");
                Calendar c = date.getCalendar();
                tv_day.setText(dayFormat.format(c.getTime()));
                tv_month.setText(monthFormat.format(c.getTime()));
                tv_year.setText(yearFormat.format(c.getTime()));
            }
        });

    }

    private void setBottomSheet() {
        bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
        View bottomSheetView = getLayoutInflater().inflate(R.layout.view_bottomsheet_menu, null);
        bottomSheetDialog.setContentView(bottomSheetView);
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) bottomSheetView.getParent());
        bottomSheetBehavior.setPeekHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, getResources().getDisplayMetrics()));
        item_setting = (CustomTextView) bottomSheetView.findViewById(R.id.item_setting);
        item_line_login = (CustomTextView) bottomSheetView.findViewById(R.id.item_line_login);
    }

    private void initView() {

        mcv = (MaterialCalendarView) findViewById(R.id.calendarView);

        iv_setting = (ImageView) findViewById(R.id.iv_setting);
        iv_btn_favorite = (ImageView) findViewById(R.id.iv_btn_favorite);
        im_country = (ImageView) findViewById(R.id.im_country);

        tv_country_name = (CustomTextView) findViewById(R.id.tv_country_name);
        tv_day = (CustomTextView) findViewById(R.id.tv_day);
        tv_month = (CustomTextView) findViewById(R.id.tv_month);
        tv_year = (CustomTextView) findViewById(R.id.tv_year);
        tv_user_id = (CustomTextView) findViewById(R.id.tv_user_id);

        rv_event = (RecyclerView) findViewById(R.id.rv_event);
    }

    private void initListener() {

        iv_setting.setOnClickListener(this);
        iv_btn_favorite.setOnClickListener(this);
        item_setting.setOnClickListener(this);
        item_line_login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == iv_setting){
            bottomSheetDialog.show();
        }

        if (v == iv_btn_favorite) {
            Intent layoutFavorite = new Intent(this, FavoriteActivity.class);
            startActivity(layoutFavorite);
        }

        if (v == item_setting) {
            bottomSheetDialog.dismiss();
            finish();
            Intent layoutSetting = new Intent(this, SettingActivity.class);
            startActivity(layoutSetting);
        }

    }
}
