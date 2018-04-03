 package network.dhammakaya.booneu3.Activity;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import network.dhammakaya.booneu3.R;
import network.dhammakaya.booneu3.View.CustomTextView;

 public class DetailActivity extends Activity implements View.OnClickListener {

     private ImageView iv_btn_back;
     private LinearLayout ll_center;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initView();
        initListener();
    }

     private void initView() {
         iv_btn_back = (ImageView) findViewById(R.id.iv_btn_back);
         ll_center = (LinearLayout) findViewById(R.id.ll_center);
     }

     private void initListener() {
         iv_btn_back.setOnClickListener(this);
         ll_center.setOnClickListener(this);
     }

     @Override
     public void onClick(View v) {

        if (v == iv_btn_back) {
            finish();
        }

        if (v == ll_center) {
            Intent intentCenter = new Intent(getApplicationContext(), CenterActivity.class);
            startActivity(intentCenter);
        }
     }
 }
