package network.dhammakaya.booneu3.View;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;
import network.dhammakaya.booneu3.Activity.DetailActivity;
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

    private Context context;

    public ViewHolder(final Context context, View itemView) {
        super(itemView);
        this.context = context;

        iv_event_image = (CircleImageView) itemView.findViewById(R.id.iv_event_image);
        tv_event_name = (CustomTextView) itemView.findViewById(R.id.tv_event_name);
        tv_event_location = (CustomTextView) itemView.findViewById(R.id.tv_event_location);
        tv_event_time_start = (CustomTextView) itemView.findViewById(R.id.tv_event_time_start);
        tv_event_time_stop = (CustomTextView) itemView.findViewById(R.id.tv_event_time_stop);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);

//                Toast.makeText(ViewHolder.this.context, "Hello", Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });

    }

}
