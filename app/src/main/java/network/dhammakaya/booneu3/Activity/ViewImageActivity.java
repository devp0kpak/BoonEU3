package network.dhammakaya.booneu3.Activity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import network.dhammakaya.booneu3.Data.EventData;
import network.dhammakaya.booneu3.Data.ImageEventData;
import network.dhammakaya.booneu3.R;

public class ViewImageActivity extends AppCompatActivity {

    private ImageView btnBack;
    private ImageEventData imageEventData;
    private String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        imageEventData = getIntent().getParcelableExtra("imageUrl");
        imageUrl = imageEventData.getImage_url();
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
