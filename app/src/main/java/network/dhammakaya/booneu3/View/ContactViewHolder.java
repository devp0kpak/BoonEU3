package network.dhammakaya.booneu3.View;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import network.dhammakaya.booneu3.R;

public class ContactViewHolder extends RecyclerView.ViewHolder {

    private Context context;

    private ImageView iv_contact_icon;
    private CustomTextView tv_contact_data;
    private CustomTextView tv_contact_position;

    public ContactViewHolder(View itemView, final Context context) {
        super(itemView);
        this.context = context;

        iv_contact_icon = (ImageView) itemView.findViewById(R.id.iv_contact_icon);
        tv_contact_data = (CustomTextView) itemView.findViewById(R.id.tv_contact_data);
        tv_contact_position = (CustomTextView) itemView.findViewById(R.id.tv_contact_position);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data_number = tv_contact_data.getText().toString();
                startTel(data_number);
            }
        });

    }

    private void startTel(String data_number){
        String call_number = "tel:"+data_number;
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(call_number));
        context.startActivity(intent);
    }
}
