package network.dhammakaya.booneu3.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import network.dhammakaya.booneu3.R;
import network.dhammakaya.booneu3.View.CustomTextView;

public class LoginActivity extends Activity implements View.OnClickListener{

    private LinearLayout li_skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initListener();

    }

    private void initView() {
        li_skip = (LinearLayout) findViewById(R.id.li_skip);
    }

    private void initListener() {
        li_skip.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == li_skip) {
            finish();
            Intent pageMain = new Intent(this, MainActivity.class);
            startActivity(pageMain);
        }
    }
}
