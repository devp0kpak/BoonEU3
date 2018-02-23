package network.dhammakaya.booneu3.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import network.dhammakaya.booneu3.R;

public class MainActivity extends AppCompatActivity {

    private MaterialCalendarView mcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        customCalendar();


    }

    private void customCalendar() {
        mcv.setTopbarVisible(false);
    }

    private void initView() {
        mcv = (MaterialCalendarView) findViewById(R.id.calendarView);
    }
}
