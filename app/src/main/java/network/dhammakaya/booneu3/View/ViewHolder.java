package network.dhammakaya.booneu3.View;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;
import network.dhammakaya.booneu3.R;

/**
 * Created by Delux on 3/20/2018.
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    public CircleImageView iv_event_image;
    public CustomTextView tv_event_name;
    public CustomTextView tv_event_location;
    public CustomTextView tv_event_time;

    public ViewHolder(View itemView) {
        super(itemView);

        iv_event_image = (CircleImageView) itemView.findViewById(R.id.iv_event_image);
        tv_event_name = (CustomTextView) itemView.findViewById(R.id.tv_event_name);
        tv_event_location = (CustomTextView) itemView.findViewById(R.id.tv_event_location);
        tv_event_time = (CustomTextView) itemView.findViewById(R.id.tv_event_time);

    }

}
