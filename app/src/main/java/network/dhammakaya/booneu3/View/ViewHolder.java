package network.dhammakaya.booneu3.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import de.hdodenhof.circleimageview.CircleImageView;
import network.dhammakaya.booneu3.Activity.DetailActivity;
import network.dhammakaya.booneu3.Data.EventData;
import network.dhammakaya.booneu3.R;

/**
 * Created by Delux on 3/20/2018.
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    //view of item
    public CircleImageView iv_event_image;
    public CustomTextView tv_event_name;
    public CustomTextView tv_event_location;
    public CustomTextView tv_event_time_start;
    public CustomTextView tv_event_time_stop;
    public CustomTextView tv_status_count;

    public FrameLayout btn_event;

    private Context context;

    public ViewHolder(View itemView, final Context context) {
        super(itemView);
        this.context = context;

        Log.e("LAST","PART-1");

        iv_event_image = (CircleImageView) itemView.findViewById(R.id.iv_event_image);
        tv_event_name = (CustomTextView) itemView.findViewById(R.id.tv_event_name);
        tv_event_location = (CustomTextView) itemView.findViewById(R.id.tv_event_location);
        tv_event_time_start = (CustomTextView) itemView.findViewById(R.id.tv_event_time_start);
        tv_event_time_stop = (CustomTextView) itemView.findViewById(R.id.tv_event_time_stop);
        btn_event = (FrameLayout) itemView.findViewById(R.id.btn_event);
        tv_status_count  = (CustomTextView) itemView.findViewById(R.id.tv_status_count);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        Log.e("LAST","PART-2");
                Intent intent = new Intent(context, DetailActivity.class);
                        Log.e("LAST","PART-3");
                intent.putExtra("event_data", (EventData) btn_event.getTag(R.id.btn_event));
//                Toast.makeText(ViewHolder.this.context, "Hello", Toast.LENGTH_SHORT).show();
                        Log.e("LAST","PART-4");
                context.startActivity(intent);
                        Log.e("LAST","PART-5");
            }
        });

    }

}
