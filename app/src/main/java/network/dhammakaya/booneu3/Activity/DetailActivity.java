 package network.dhammakaya.booneu3.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import network.dhammakaya.booneu3.Adapter.ContactAdapter;
import network.dhammakaya.booneu3.Adapter.PhotoAdapter;
import network.dhammakaya.booneu3.R;
import network.dhammakaya.booneu3.View.CustomTextView;

 public class DetailActivity extends Activity implements View.OnClickListener {

     private ImageView iv_btn_back;
     private CustomTextView btn_favorite;
     private LinearLayout ll_center;

     private RecyclerView rv_image;
     private RecyclerView rv_contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initView();
        initListener();
        setRecyclerViewImage();
        setRecyclerViewContact();

    }

     private void setRecyclerViewImage() {
        rv_image.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        rv_image.setHasFixedSize(true);
        rv_image.setAdapter(new PhotoAdapter(getApplicationContext()));
     }

     private void setRecyclerViewContact() {
         rv_contact.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
         rv_contact.setHasFixedSize(true);
         rv_contact.setAdapter(new ContactAdapter(getApplicationContext()));
     }

     private void startDialogConfirmFavorite() {
         AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
         View mView = getLayoutInflater().inflate(R.layout.dialog_confirm_favorite, null);
         mBuilder.setView(mView);
         AlertDialog dialog = mBuilder.create();
         dialog.setCancelable(true);
         dialog.setCanceledOnTouchOutside(false);
         dialog.show();
         dialog.getWindow().setLayout(600, 500);
     }

     private void initView() {
         iv_btn_back = (ImageView) findViewById(R.id.iv_btn_back);
         btn_favorite = (CustomTextView) findViewById(R.id.btn_favorite);
         ll_center = (LinearLayout) findViewById(R.id.ll_center);

         rv_image = (RecyclerView) findViewById(R.id.rv_image);
         rv_contact = (RecyclerView) findViewById(R.id.rv_contact);
     }

     private void initListener() {
         iv_btn_back.setOnClickListener(this);
         btn_favorite.setOnClickListener(this);
         ll_center.setOnClickListener(this);
     }

     @Override
     public void onClick(View v) {

        if (v == iv_btn_back) {
            finish();
        }

        if (v == btn_favorite) {
            startDialogConfirmFavorite();
        }

        if (v == ll_center) {
            //Intent intentCenter = new Intent(getApplicationContext(), CenterActivity.class);
            //startActivity(intentCenter);
        }
     }
 }
