package com.kyser.weatherforecast.ui.view.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.kyser.weatherforecast.R;
import com.kyser.weatherforecast.databinding.ForecastListItemBinding;
import com.kyser.weatherforecast.databinding.HourlyListItemBinding;
import com.kyser.weatherforecast.model.Daily;
import com.kyser.weatherforecast.model.Hourly;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ForecastAdaptor extends RecyclerView.Adapter<ForecastAdaptor.Holder> {

    private List<Daily> mForecastList;
    private Context mContext;
    private itemEvent mItemEvent;

    public ForecastAdaptor(Context mContext) {
        this.mContext = mContext;
    }

    public ForecastAdaptor(Context mContext, itemEvent mItemEvent) {
        this.mContext = mContext;
        this.mItemEvent = mItemEvent;
    }

    public ForecastAdaptor(Context mContext , List<Daily> forecastList) {
        this.mForecastList = forecastList;
        this.mContext = mContext;
    }

    public void setHourlyList(List<Daily> forecastList) {
        this.mForecastList = forecastList;
        notifyDataSetChanged();
    }

    private String getDayText(int position){
        Calendar rightNow = Calendar.getInstance();
        rightNow.add(Calendar.DAY_OF_MONTH,(position));
        return new StringBuilder().append(rightNow.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()))
                .append(", ")
                .append(rightNow.get(Calendar.DAY_OF_MONTH))
                .append(" ")
                .append(rightNow.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault())).toString();
    }

    public interface itemEvent{
        void onItemCLick(String date, Daily dayForecast);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(ForecastListItemBinding.inflate(LayoutInflater.from(parent.getContext()),  parent, false))  ;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.forecastListItemBinding.forecastDay.setText(getDayText(position));
        holder.forecastListItemBinding.forecastTemp.setText(new StringBuilder().append(mForecastList.get(position).getTemp().getMin())
                .append("/")
                .append(mForecastList.get(position).getTemp().getMax())
                .append(mContext.getResources().getString(R.string.celcius)).toString());
        Glide.with(holder.forecastListItemBinding.forecastIcon).load(mContext.getString(R.string.img_icon_url,mForecastList.get(position).getWeather().get(0).getIcon()))
                .into(holder.forecastListItemBinding.forecastIcon);

        holder.forecastListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemEvent.onItemCLick(getDayText(position),mForecastList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mForecastList!=null?mForecastList.size():0;
    }

    public class Holder extends RecyclerView.ViewHolder {
        private ForecastListItemBinding forecastListItemBinding;

        public Holder(@NonNull ForecastListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.forecastListItemBinding=itemBinding;
        }
    }
}
