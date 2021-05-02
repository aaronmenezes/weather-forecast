package com.kyser.weatherforecast.ui.view.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.kyser.weatherforecast.R;
import com.kyser.weatherforecast.databinding.HourlyListItemBinding;
import com.kyser.weatherforecast.model.Hourly;

import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HourlyAdaptor  extends RecyclerView.Adapter<HourlyAdaptor.Holder> {

    private List<Hourly> mHourlyList;
    private Context mContext;
    private int mHourNow;

    public HourlyAdaptor(Context mContext) {
        this.mContext = mContext;
        setHourValue();
    }

    private void setHourValue() {
        Calendar rightNow = Calendar.getInstance();
        mHourNow = rightNow.get(Calendar.HOUR_OF_DAY);
    }

    public void setHourlyList(List<Hourly> hourlyList) {
        this.mHourlyList = hourlyList;
        notifyDataSetChanged();
    }

    private String getHourText(int position){
        StringBuilder sb  =  new StringBuilder();
        sb.append((mHourNow+position)%12);
        if((int)((mHourNow+position)/12)%2==0)
            sb.append(mContext.getString(R.string.AM));
        else
            sb.append(mContext.getString(R.string.PM));
        return  sb.toString();
    }
    public HourlyAdaptor(Context mContext , List<Hourly> hourlyList) {
        this.mHourlyList = hourlyList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(HourlyListItemBinding.inflate(LayoutInflater.from(parent.getContext()),  parent, false))  ;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.hourlyListItemBinding.itemTimeHour.setText(getHourText(position));
        holder.hourlyListItemBinding.itemTemp.setText(mHourlyList.get(position).getTemp()+mContext.getResources().getString(R.string.celcius));
        Glide.with(holder.hourlyListItemBinding.itemIcon).load(mContext.getString(R.string.img_icon_url,mHourlyList.get(position).getWeather().get(0).getIcon()))
                .into(holder.hourlyListItemBinding.itemIcon);
    }

    @Override
    public int getItemCount() {
        return mHourlyList!=null?mHourlyList.size():0;
    }

    public class Holder extends RecyclerView.ViewHolder {
        private HourlyListItemBinding hourlyListItemBinding;

        public Holder(@NonNull HourlyListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.hourlyListItemBinding=itemBinding;
        }
    }
}
