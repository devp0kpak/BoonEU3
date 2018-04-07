package network.dhammakaya.booneu3.View;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import network.dhammakaya.booneu3.Activity.ViewImageActivity;
import network.dhammakaya.booneu3.R;

public class PhotoViewHolder extends RecyclerView.ViewHolder {

    private Context context;

    private ImageView item_image;

    public PhotoViewHolder(View itemView, final Context context) {
        super(itemView);
        this.context = context;
        item_image = (ImageView) itemView.findViewById(R.id.item_image);

        final String URL = "http://booncalendar.dhammakaya.network/images/boocharkhawphra.jpg";

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewImageActivity.class);
                intent.putExtra("imageUri", URL);
                context.startActivity(intent);
            }
        });
    }
}
