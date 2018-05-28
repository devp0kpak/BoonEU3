package net.dmceu.booneu.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import net.dmceu.booneu.R;

public class AboutActivity extends Activity implements View.OnClickListener{

    private ImageView iv_back_from_about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        initView();
        initListener();
    }

    private void initView() {
        iv_back_from_about = (ImageView) findViewById(R.id.iv_back_from_about);
    }

    private void initListener() {
        iv_back_from_about.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if( v == iv_back_from_about){
            finish();
        }
    }
}
