package net.dmceu.booneu.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import net.dmceu.booneu.Data.ImageEventData;
import net.dmceu.booneu.R;
import net.dmceu.booneu.View.ImageEventViewHolder;

import java.util.ArrayList;

public class ImageEventAdapter extends RecyclerView.Adapter<ImageEventViewHolder> {

    private ArrayList<ImageEventData> imageEventData;
    private Context context;

    public ImageEventAdapter(ArrayList<ImageEventData> imageEventData, Context context) {
        this.imageEventData = imageEventData;
        this.context = context;
    }

    @NonNull
    @Override
    public ImageEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.view_item_picture, parent, false);
        return new ImageEventViewHolder(v,context);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageEventViewHolder holder, int position) {
        Picasso.get().load(imageEventData.get(position).getMedia_url()).into(holder.item_image);
        holder.item_image.setTag(R.id.item_image, imageEventData.get(position));
    }

    @Override
    public int getItemCount() {
        return imageEventData.size();
    }
}
