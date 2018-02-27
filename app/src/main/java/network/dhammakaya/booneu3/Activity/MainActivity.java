package network.dhammakaya.booneu3.Activity;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import network.dhammakaya.booneu3.R;

public class MainActivity extends Activity implements View.OnClickListener{

    private MaterialCalendarView mcv;

    private ImageView iv_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
        customCalendar();


    }

    private void customCalendar() {
        mcv.setTopbarVisible(false);
    }

    private void initView() {
        mcv = (MaterialCalendarView) findViewById(R.id.calendarView);

        iv_setting = (ImageView) findViewById(R.id.iv_setting);
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
