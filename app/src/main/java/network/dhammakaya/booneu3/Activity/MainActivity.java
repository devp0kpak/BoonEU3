package network.dhammakaya.booneu3.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
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
    private CustomTextView tv_day;
    private CustomTextView tv_month;
    private CustomTextView tv_year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
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
        tv_day = (CustomTextView) findViewById(R.id.tv_day);
        tv_month = (CustomTextView) findViewById(R.id.tv_month);
        tv_year = (CustomTextView) findViewById(R.id.tv_year);
    }

    private void initListener() {

        iv_setting.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v == iv_setting){
            Intent layoutSetting = new Intent(this, SettingActivity.class);
            startActivity(layoutSetting);
        }

    }
}
