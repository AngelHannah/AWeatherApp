package com.example.aweatherapp.Adapters;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aweatherapp.Domains.Hourly;
import com.example.aweatherapp.R;

import java.util.ArrayList;

public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.viewHolder> {
    ArrayList<Hourly> items;
    Context context;

    public HourlyAdapter(ArrayList<Hourly> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_hourly, parent, false);
        context = parent.getContext();
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.hourTxt.setText(items.get(position).getHour());
        holder.tempTxt.setText(items.get(position).getTemperature());

        int drawableResourceId = holder.itemView.getResources().getIdentifier(items.get(position).getPicturePath(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(context).load(drawableResourceId).into(holder.picture);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView hourTxt, tempTxt;
        ImageView picture;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            hourTxt = itemView.findViewById(R.id.card_hourTxtV);
            tempTxt = itemView.findViewById(R.id.card_tempTxtV);
            picture = itemView.findViewById(R.id.card_weatherIconImgV);
        }
    }
}
