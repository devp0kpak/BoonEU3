package net.dmceu.booneu.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import net.dmceu.booneu.Data.ImageEventData;
import net.dmceu.booneu.R;

public class ViewImageActivity extends AppCompatActivity {

    private ImageView btnBack;
    private ImageEventData imageEventData;
    private String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        imageEventData = getIntent().getParcelableExtra("imageUrl");
        imageUrl = imageEventData.getMedia_url();
        btnBack = (ImageView) findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
        Picasso.get().load(imageUrl).into(photoView);

    }
}
