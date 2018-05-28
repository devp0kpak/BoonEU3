package net.dmceu.booneu.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import net.dmceu.booneu.Activity.ViewImageActivity;
import net.dmceu.booneu.Data.ImageEventData;
import net.dmceu.booneu.R;

public class ImageEventViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    public ImageView item_image;

    public ImageEventViewHolder(View itemView, final Context context) {
        super(itemView);
        this.context = context;

        item_image = (ImageView) itemView.findViewById(R.id.item_image);

        itemView.getTag();

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewImageActivity.class);
                intent.putExtra("imageUrl", (ImageEventData) item_image.getTag(R.id.item_image));
                context.startActivity(intent);
            }
        });
    }
}