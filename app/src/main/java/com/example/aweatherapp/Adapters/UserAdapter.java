package com.example.aweatherapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aweatherapp.Domains.User;
import com.example.aweatherapp.R;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.viewHolder> {
    ArrayList<User> users;
    Context context;

    public UserAdapter(ArrayList<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_user, parent, false);
        context = parent.getContext();
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.idTxt.setText(String.valueOf(users.get(position).getId()));
        holder.emailTxt.setText(String.valueOf(users.get(position).getEmail()));
        holder.passwordTxt.setText(String.valueOf(users.get(position).getPassword()));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView idTxt, emailTxt, passwordTxt;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            idTxt = itemView.findViewById(R.id.idDisplayTxtV);
            emailTxt = itemView.findViewById(R.id.emailDisplayTxtV);
            passwordTxt = itemView.findViewById(R.id.passDisplayTxtV);
        }
    }
}
