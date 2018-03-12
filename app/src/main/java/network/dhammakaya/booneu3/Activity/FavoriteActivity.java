package network.dhammakaya.booneu3.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import network.dhammakaya.booneu3.R;

public class FavoriteActivity extends Activity implements View.OnClickListener {

    private ImageView iv_btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        initView();
        initListener();
    }

    private void initView() {
        iv_btn_back = (ImageView) findViewById(R.id.iv_btn_back);
    }

    private void initListener() {

        iv_btn_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == iv_btn_back) {
            finish();
        }
    }
}
