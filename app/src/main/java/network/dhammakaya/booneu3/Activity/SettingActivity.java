package network.dhammakaya.booneu3.Activity;

import android.app.AlertDialog;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import network.dhammakaya.booneu3.R;
import network.dhammakaya.booneu3.View.CustomTextView;

public class SettingActivity extends Activity implements View.OnClickListener {

    private String country = "Defult";

    private ImageView iv_back_from_setting;

    private CustomTextView tv_country_name;

    private LinearLayout btn_setting_view;
    private LinearLayout btn_setting_country;
    private LinearLayout btn_setting_language;
    private LinearLayout btn_about;

    //------ For Country -----//
    private LinearLayout btn_austria;
    private LinearLayout btn_belgium;
    private LinearLayout btn_denmark;
    private LinearLayout btn_france;
    private LinearLayout btn_germany;
    private LinearLayout btn_italy;
    private LinearLayout btn_malta;
    private LinearLayout btn_netherlands;
    private LinearLayout btn_norway;
    private LinearLayout btn_sweden;
    private LinearLayout btn_switzerland;
    private LinearLayout btn_uk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initView();
        initListener();
        getStringFromFile();
    }

    public void getStringFromFile() {
        SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
        country = f_data.getString("country", "Austria_F");
        tv_country_name.setText(country);
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

        btn_austria = (LinearLayout) mCountry.findViewById(R.id.btn_austria);
        btn_belgium = (LinearLayout) mCountry.findViewById(R.id.btn_belgium);
        btn_denmark = (LinearLayout) mCountry.findViewById(R.id.btn_denmark);
        btn_france = (LinearLayout) mCountry.findViewById(R.id.btn_france);
        btn_germany = (LinearLayout) mCountry.findViewById(R.id.btn_germany);
        btn_italy = (LinearLayout) mCountry.findViewById(R.id.btn_italy);
        btn_malta = (LinearLayout) mCountry.findViewById(R.id.btn_malta);
        btn_netherlands = (LinearLayout) mCountry.findViewById(R.id.btn_netherlands);
        btn_norway = (LinearLayout) mCountry.findViewById(R.id.btn_norway);
        btn_sweden = (LinearLayout) mCountry.findViewById(R.id.btn_sweden);
        btn_switzerland = (LinearLayout) mCountry.findViewById(R.id.btn_switzerland);
        btn_uk = (LinearLayout) mCountry.findViewById(R.id.btn_uk);

        btn_austria.setOnClickListener(this);
        btn_belgium.setOnClickListener(this);
        btn_denmark.setOnClickListener(this);
        btn_france.setOnClickListener(this);
        btn_germany.setOnClickListener(this);
        btn_italy.setOnClickListener(this);
        btn_malta.setOnClickListener(this);
        btn_netherlands.setOnClickListener(this);
        btn_norway.setOnClickListener(this);
        btn_sweden.setOnClickListener(this);
        btn_switzerland.setOnClickListener(this);
        btn_uk.setOnClickListener(this);
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

    //---------------------------------- Set View Zone -------------------------------------------//
    private void initView() {
        iv_back_from_setting = (ImageView) findViewById(R.id.iv_back_from_setting);

        tv_country_name = (CustomTextView) findViewById(R.id.tv_country_name);

        btn_setting_view = (LinearLayout) findViewById(R.id.btn_setting_view);
        btn_setting_country = (LinearLayout) findViewById(R.id.btn_setting_country);
        btn_setting_language = (LinearLayout) findViewById(R.id.btn_setting_language);
        btn_about = (LinearLayout) findViewById(R.id.btn_about);
    }

    //---------------------------------- Set OnClick Zone ----------------------------------------//
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
            Intent mainIntent = new Intent(this,MainActivity.class);
            startActivity(mainIntent);
        }

        if(v == btn_setting_view){
            //startDialogSettingView();
        }

        if(v == btn_setting_country){
            startDialogSettingCountry();
        }

        if(v == btn_setting_language){
            //startDialogSettingLanguage();
        }

        if(v == btn_about){
            Intent layoutAbout = new Intent(this, AboutActivity.class);
            startActivity(layoutAbout);
        }

        if(v == btn_austria){
            SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = f_data.edit();
            editor.putString("country", "Austria");
            editor.commit();
            restartApplication();
        }

        if(v == btn_belgium){
            SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = f_data.edit();
            editor.putString("country", "Belgium");
            editor.commit();
            restartApplication();
        }

        if(v == btn_denmark){
            SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = f_data.edit();
            editor.putString("country", "Denmark");
            editor.commit();
            restartApplication();
        }

        if(v == btn_france){
            SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = f_data.edit();
            editor.putString("country", "France");
            editor.commit();
            restartApplication();
        }

        if(v == btn_germany){
            SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = f_data.edit();
            editor.putString("country", "Germany");
            editor.commit();
            restartApplication();
        }

        if(v == btn_italy){
            SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = f_data.edit();
            editor.putString("country", "Italy");
            editor.commit();
            restartApplication();
        }

        if(v == btn_malta){
            SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = f_data.edit();
            editor.putString("country", "Malta");
            editor.commit();
            finish();
            restartApplication();
        }

        if(v == btn_netherlands){
            SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = f_data.edit();
            editor.putString("country", "Netherlands");
            editor.commit();
            restartApplication();
        }

        if(v == btn_norway){
            SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = f_data.edit();
            editor.putString("country", "Norway");
            editor.commit();
            restartApplication();
        }

        if(v == btn_sweden){
            SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = f_data.edit();
            editor.putString("country", "Sweden");
            editor.commit();
            restartApplication();
        }

        if(v == btn_switzerland){
            SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = f_data.edit();
            editor.putString("country", "Switzerland");
            editor.commit();
            restartApplication();
        }

        if(v == btn_uk){
            SharedPreferences f_data = getSharedPreferences("f_data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = f_data.edit();
            editor.putString("country", "United Kingdom");
            editor.commit();
            restartApplication();
        }

    }

    private void restartApplication(){
        finish();
        Intent i = getBaseContext().getPackageManager().
                getLaunchIntentForPackage(getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);

    }
}
