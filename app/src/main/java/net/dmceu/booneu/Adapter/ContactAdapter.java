package net.dmceu.booneu.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dmceu.booneu.Data.ContactData;
import net.dmceu.booneu.R;
import net.dmceu.booneu.View.ContactViewHolder;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {

    private ArrayList<ContactData> contactData;
    private Context context;

    public ContactAdapter(ArrayList<ContactData> contactData, Context context) {
        this.contactData = contactData;
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
        holder.tv_contact_data.setText(contactData.get(position).getContact_data());
    }

    @Override
    public int getItemCount() {
        return contactData.size();
    }
}
