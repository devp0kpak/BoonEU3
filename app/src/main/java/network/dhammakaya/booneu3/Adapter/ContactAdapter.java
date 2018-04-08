package network.dhammakaya.booneu3.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import network.dhammakaya.booneu3.R;
import network.dhammakaya.booneu3.View.ContactViewHolder;
import network.dhammakaya.booneu3.View.PhotoViewHolder;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {
    private Context context;

    public ContactAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.view_item_contact, parent, false);
        return new ContactViewHolder(v,context);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
