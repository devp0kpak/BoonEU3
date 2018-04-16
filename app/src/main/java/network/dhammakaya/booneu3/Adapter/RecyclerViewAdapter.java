package network.dhammakaya.booneu3.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import network.dhammakaya.booneu3.Data.EventData;
import network.dhammakaya.booneu3.R;
import network.dhammakaya.booneu3.View.CustomDateView;
import network.dhammakaya.booneu3.View.ViewHolder;
import retrofit2.Callback;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private ArrayList<EventData> eventData;
    private Context context;

    public RecyclerViewAdapter(ArrayList<EventData> eventData, Context context) {
        this.eventData = eventData;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.view_item_event, parent, false);
        return new ViewHolder(context,v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_event_name.setText(eventData.get(position).getEvent_name());
        holder.tv_event_location.setText(eventData.get(position).getCenter_name_en());
        holder.tv_event_time_start.setText(CustomDateView.timeShot(eventData.get(position).getTime_start()));
        holder.tv_event_time_stop.setText(CustomDateView.timeShot(eventData.get(position).getTime_stop()));

        Picasso.get().load(eventData.get(position).getImage_url()).into(holder.iv_event_image);

        holder.btn_event.setTag(R.id.btn_event, eventData.get(position));

    }

    @Override
    public int getItemCount() {
        return eventData.size();
    }
}
