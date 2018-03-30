package network.dhammakaya.booneu3.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import network.dhammakaya.booneu3.R;
import network.dhammakaya.booneu3.View.ViewHolder;

/**
 * Created by Delux on 3/20/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context context;

    public RecyclerViewAdapter(Context context){
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.view_item_event, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_event_name.setText("Boocharkawphra : "+position);
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
