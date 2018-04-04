package network.dhammakaya.booneu3.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import network.dhammakaya.booneu3.R;
import network.dhammakaya.booneu3.View.PhotoViewHolder;
import network.dhammakaya.booneu3.View.ViewHolder;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoViewHolder> {

    private Context context;

    public PhotoAdapter(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.view_item_picture, parent, false);
        return new PhotoViewHolder(v,context);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
