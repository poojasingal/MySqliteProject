package com.example.myapplication.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Pojo.Contact;
import com.example.myapplication.R;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {
    Context context;
    List<Contact> getcontactlist;

    public ContactAdapter(Context context, List<Contact> getcontactlist) {

        this.context = context;
        this.getcontactlist = getcontactlist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_adapter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.mId.setText(String.valueOf(getcontactlist.get(position).getId()));
        holder.mName.setText(getcontactlist.get(position).getName());
        holder.mPhonno.setText(getcontactlist.get(position).getPhoneno());

        Log.e("tag_name", getcontactlist.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return getcontactlist.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mId, mName, mPhonno;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mId = itemView.findViewById(R.id.id_);
            mName = itemView.findViewById(R.id.name);
            mPhonno = itemView.findViewById(R.id.phoneno);
        }
    }
}
