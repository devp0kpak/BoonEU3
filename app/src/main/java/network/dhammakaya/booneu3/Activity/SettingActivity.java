package network.dhammakaya.booneu3.Activity;

import android.app.AlertDialog;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import network.dhammakaya.booneu3.R;

public class SettingActivity extends Activity implements View.OnClickListener {

    private ImageView iv_back_from_setting;

    private LinearLayout btn_setting_view;
    private LinearLayout btn_setting_country;
    private LinearLayout btn_setting_language;
    private LinearLayout btn_about;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initView();
        initListener();
    }


    //---------------------------------- Start Setting Dialog Zone -------------------------------//

    private void startDialogSettingView() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_select_view, null);
        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialog.getWindow().setLayout(600, 500);
    }

    private void startDialogSettingCountry() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        View mCountry = getLayoutInflater().inflate(R.layout.dialog_select_country, null);
        mBuilder.setView(mCountry);
        AlertDialog dialog = mBuilder.create();
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialog.getWindow().setLayout(900, 1100);
    }

    private void startDialogSettingLanguage() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        View mLanguage = getLayoutInflater().inflate(R.layout.dialog_select_language, null);
        mBuilder.setView(mLanguage);
        AlertDialog dialog = mBuilder.create();
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialog.getWindow().setLayout(600, 500);
    }


    //---------------------------------- Start Activity Zone -------------------------------------//
    private void startActivityAbout() {
        Intent layoutAbout = new Intent(this, AboutActivity.class);
        startActivity(layoutAbout);
    }

    //---------------------------------- Set View Zone -------------------------------------------//
    private void initView() {
        iv_back_from_setting = (ImageView) findViewById(R.id.iv_back_from_setting);

        btn_setting_view = (LinearLayout) findViewById(R.id.btn_setting_view);
        btn_setting_country = (LinearLayout) findViewById(R.id.btn_setting_country);
        btn_setting_language = (LinearLayout) findViewById(R.id.btn_setting_language);
        btn_about = (LinearLayout) findViewById(R.id.btn_about);
    }

    //---------------------------------- Set Onclick Zone ----------------------------------------//
    private void initListener() {
        iv_back_from_setting.setOnClickListener(this);

        btn_setting_view.setOnClickListener(this);
        btn_setting_country.setOnClickListener(this);
        btn_setting_language.setOnClickListener(this);
        btn_about.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {

        if(v == iv_back_from_setting){
            finish();
        }

        if(v == btn_setting_view){
            startDialogSettingView();
        }

        if(v == btn_setting_country){
            startDialogSettingCountry();
        }

        if(v == btn_setting_language){
            startDialogSettingLanguage();
        }

        if(v == btn_about){
            startActivityAbout();
        }

    }
}
