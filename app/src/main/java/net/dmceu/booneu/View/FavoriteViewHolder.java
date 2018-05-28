package net.dmceu.booneu.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import net.dmceu.booneu.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class FavoriteViewHolder extends RecyclerView.ViewHolder{

    //view of item
    public CircleImageView iv_event_image;
    public CustomTextView tv_event_name;
    public CustomTextView tv_event_location;
    public CustomTextView tv_event_time_start;
    public CustomTextView tv_event_time_stop;
    public CustomTextView tv_status_count;

    public FrameLayout btn_event;

    private Context context;

    public FavoriteViewHolder(final Context context, View itemView) {
        super(itemView);
        this.context = context;

        iv_event_image = (CircleImageView) itemView.findViewById(R.id.iv_event_image);
        tv_event_name = (CustomTextView) itemView.findViewById(R.id.tv_event_name);
        tv_event_location = (CustomTextView) itemView.findViewById(R.id.tv_event_location);
        tv_event_time_start = (CustomTextView) itemView.findViewById(R.id.tv_event_time_start);
        tv_event_time_stop = (CustomTextView) itemView.findViewById(R.id.tv_event_time_stop);
        btn_event = (FrameLayout) itemView.findViewById(R.id.btn_event);
        tv_status_count  = (CustomTextView) itemView.findViewById(R.id.tv_status_count);
/*
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("event_data", (FavoriteData) btn_event.getTag(R.id.btn_event));
                context.startActivity(intent);
            }
        });
*/

    }

}
