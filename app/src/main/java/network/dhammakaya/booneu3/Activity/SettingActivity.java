package network.dhammakaya.booneu3.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import network.dhammakaya.booneu3.R;

public class SettingActivity extends Activity implements View.OnClickListener {

    private ImageView iv_back_from_setting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initView();
        initListener();
    }

    private void initView() {
        iv_back_from_setting = (ImageView) findViewById(R.id.iv_back_from_setting);
    }

    private void initListener() {
        iv_back_from_setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v == iv_back_from_setting){
            finish();
            Intent layoutMain = new Intent(this, MainActivity.class);
            startActivity(layoutMain);
        }

    }
}
