package network.dhammakaya.booneu3.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import network.dhammakaya.booneu3.R;

public class CenterActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView iv_back_from_center;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center);

        initView();
        initListener();
    }

    private void initView() {
        iv_back_from_center = (ImageView) findViewById(R.id.iv_back_from_about);
    }

    private void initListener() {
        iv_back_from_center.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if( v == iv_back_from_center){
            finish();
        }
    }
}
