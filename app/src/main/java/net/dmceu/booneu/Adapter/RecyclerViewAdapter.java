package net.dmceu.booneu.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import net.dmceu.booneu.Data.EventData;
import net.dmceu.booneu.R;
import net.dmceu.booneu.View.CustomDateView;
import net.dmceu.booneu.View.ViewHolder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
        return new ViewHolder(v,context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_event_name.setText(eventData.get(position).getEvent_name());
        holder.tv_event_location.setText(eventData.get(position).getCenter_name_en());
        holder.tv_event_time_start.setText(CustomDateView.timeShot(eventData.get(position).getTime_start()));
        holder.tv_event_time_stop.setText(CustomDateView.timeShot(eventData.get(position).getTime_stop()));

        Picasso.get().load(eventData.get(position).getMedia_url()).into(holder.iv_event_image);

        holder.btn_event.setTag(R.id.btn_event, eventData.get(position));

        Calendar today = Calendar.getInstance();
        DateFormat dateFull = new SimpleDateFormat("yyyy-MM-dd");

        String dateToday = dateFull.format(today.getTime());
        String dateData = CustomDateView.setReturn(eventData.get(position).getCalendar_date());

        int dayA = 0,monthA = 0,yearA = 0;
        int dayB = 0,monthB = 0,yearB = 0;
        try {
            Date date1 = dateFull.parse(dateToday);
            Date date2 = dateFull.parse(dateData);

            String formateddayA = new SimpleDateFormat("dd").format(date1);
            String formatedmonthA = new SimpleDateFormat("MM").format(date1);
            String formatedyearA = new SimpleDateFormat("yyyy").format(date1);
            String formateddayB = new SimpleDateFormat("dd").format(date2);
            String formatedmonthB = new SimpleDateFormat("MM").format(date2);
            String formatedyearB = new SimpleDateFormat("yyyy").format(date2);

            dayA = Integer.parseInt(formateddayA);
            monthA = Integer.parseInt(formatedmonthA);
            yearA = Integer.parseInt(formatedyearA);

            dayB = Integer.parseInt(formateddayB);
            monthB = Integer.parseInt(formatedmonthB);
            yearB = Integer.parseInt(formatedyearB);

        } catch (Exception e) {
            e.printStackTrace();
        }

        Toast.makeText(context, "This date : " + yearA+monthA+dayA + "\n Date Data : " + dayB, Toast.LENGTH_SHORT).show();

        //dateA is Today
        //dateB is Event day

        String dateStr = "2/3/2017";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(dayA > dayB || monthA > monthB || yearA > yearB){
            holder.tv_status_count.setText("จัดไปแล้ว");
            holder.tv_status_count.setBackgroundResource(R.drawable.bg_status_already);
        }

        if(dayA < dayB || monthA < monthB || yearA < yearB){
            int resultYear = monthB - monthA;
            int resultMonth = monthB - monthA;
            int resultDay = dayB - dayA;

            if (resultYear > 0) {
                holder.tv_status_count.setText("เหลืออีก " + resultYear + " ปี " + resultMonth + " เดือน " + resultDay + " วัน");
            }
            if (resultMonth > 0) {
                holder.tv_status_count.setText("เหลืออีก " + resultMonth + " เดือน " + resultDay + " วัน");
            } else {
                holder.tv_status_count.setText("เหลืออีก " + resultDay + " วัน");
            }

            holder.tv_status_count.setBackgroundResource(R.drawable.bg_status_not_arrive);
        }

        if(dayA == dayB && monthA == monthB && yearA == yearB){
            holder.tv_status_count.setText("กิจกรรมจัดวันนี้");
            holder.tv_status_count.setBackgroundResource(R.drawable.bg_status_today);
        }

    }

    @Override
    public int getItemCount() {
        return eventData.size();
    }
}
