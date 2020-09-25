package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Pojo.Users;
import com.example.myapplication.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    Context context;
    List<Users> userList;

    public UserAdapter(Context context, List<Users> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_adapter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.mName.setText(userList.get(position).getName());
        holder.mLoc.setText(userList.get(position).getLoc());
        holder.mDesg.setText(userList.get(position).getDesg());

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mName, mLoc, mDesg;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mName = itemView.findViewById(R.id.tv_name);
            mLoc = itemView.findViewById(R.id.tv_loc);
            mDesg = itemView.findViewById(R.id.tv_desg);
        }
    }
}
