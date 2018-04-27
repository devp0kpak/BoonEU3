package network.dhammakaya.booneu3.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import network.dhammakaya.booneu3.Data.EventData;
import network.dhammakaya.booneu3.Data.FavoriteData;
import network.dhammakaya.booneu3.R;
import network.dhammakaya.booneu3.View.CustomDateView;
import network.dhammakaya.booneu3.View.FavoriteViewHolder;
import network.dhammakaya.booneu3.View.ViewHolder;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteViewHolder>{

    private ArrayList<FavoriteData> favoriteData;
    private Context context;

    public FavoriteAdapter(ArrayList<FavoriteData> favoriteData, Context context) {
        this.favoriteData = favoriteData;
        this.context = context;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.view_item_event, parent, false);
        return new FavoriteViewHolder(context,v);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {

        holder.tv_event_name.setText(favoriteData.get(position).getEvent_name());
        holder.tv_event_location.setText(favoriteData.get(position).getCenter_name_en());
        holder.tv_event_time_start.setText(CustomDateView.timeShot(favoriteData.get(position).getTime_start()));
        holder.tv_event_time_stop.setText(CustomDateView.timeShot(favoriteData.get(position).getTime_stop()));
        holder.tv_status_count.setText(CustomDateView.dateThai(favoriteData.get(position).getCalendar_date()));

        Picasso.get().load(favoriteData.get(position).getMedia_url()).into(holder.iv_event_image);

        holder.btn_event.setTag(R.id.btn_event, favoriteData.get(position));

    }

    @Override
    public int getItemCount() {
        return favoriteData.size();
    }
}
