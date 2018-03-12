package network.dhammakaya.booneu3.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.text.Layout;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import network.dhammakaya.booneu3.Dates.OneDayDecorator;
import network.dhammakaya.booneu3.R;
import network.dhammakaya.booneu3.View.CustomTextView;

public class MainActivity extends Activity implements View.OnClickListener{

    private MaterialCalendarView mcv;

    private ImageView iv_setting;
    private ImageView iv_btn_favorite;
    private CustomTextView tv_day;
    private CustomTextView tv_month;
    private CustomTextView tv_year;

    private BottomSheetDialog bottomSheetDialog;

    private View item_event;

    //Bottom Sheet
    private CustomTextView item_setting;
    private CustomTextView item_line_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setBottomSheet();
        initListener();
        customCalendar();

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
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = date.getCalendar();
                String date_click = df.format(c.getTime());
                Toast.makeText(MainActivity.this, "Click : " + date_click, Toast.LENGTH_SHORT).show();
            }
        });

    }



    private void initView() {
        mcv = (MaterialCalendarView) findViewById(R.id.calendarView);

        iv_setting = (ImageView) findViewById(R.id.iv_setting);
        iv_btn_favorite = (ImageView) findViewById(R.id.iv_btn_favorite);
        tv_day = (CustomTextView) findViewById(R.id.tv_day);
        tv_month = (CustomTextView) findViewById(R.id.tv_month);
        tv_year = (CustomTextView) findViewById(R.id.tv_year);

        item_event = (View) findViewById(R.id.item_event);
    }

    private void initListener() {

        iv_setting.setOnClickListener(this);
        iv_btn_favorite.setOnClickListener(this);
        item_event.setOnClickListener(this);
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

        if (v == item_event) {
            Intent layoutDetail = new Intent(this, DetailActivity.class);
            startActivity(layoutDetail);
        }

        if (v == item_setting) {
            bottomSheetDialog.dismiss();
            Intent layoutSetting = new Intent(this, SettingActivity.class);
            startActivity(layoutSetting);
        }

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
}
