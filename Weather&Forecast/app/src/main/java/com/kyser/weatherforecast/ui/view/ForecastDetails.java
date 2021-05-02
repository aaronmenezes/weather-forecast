package com.kyser.weatherforecast.ui.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.kyser.weatherforecast.R;
import com.kyser.weatherforecast.databinding.FragmentForecastDetailsBinding;
import com.kyser.weatherforecast.model.Daily;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ForecastDetails extends Fragment {

    private FragmentForecastDetailsBinding mForecastDetailsBinding;

    public ForecastDetails() { }

    public static ForecastDetails newInstance() {
        return new ForecastDetails();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        mForecastDetailsBinding = FragmentForecastDetailsBinding.inflate(inflater, container, false);
        return mForecastDetailsBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Daily details = (Daily) getArguments().get("daily");
        setDescDetails((String) getArguments().get("date"), details);
    }

    private String getTime(int time){
        Date date = new Date(time * 1000L);
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(date);
    }
    private void setDescDetails(String date, Daily details) {
        mForecastDetailsBinding.descDate.setText(date);
        mForecastDetailsBinding.descMain.setText(details.getWeather().get(0).getMain()+"\n"+details.getWeather().get(0).getDescription());
        mForecastDetailsBinding.descTemp.setText(details.getTemp().getMin()+" / "+ details.getTemp().getMax()+ getContext().getString(R.string.celcius));
        Glide.with(mForecastDetailsBinding.descIcon).load(getContext().getString(R.string.img_icon_url,details.getWeather().get(0).getIcon()))
                .into(mForecastDetailsBinding.descIcon);
        mForecastDetailsBinding.descWindText.setText(details.getWindSpeed()+"m/s");
        mForecastDetailsBinding.descPressureText.setText(details.getPressure()+"hPa");
        mForecastDetailsBinding.descHumidityText.setText(details.getHumidity()+"%");
        mForecastDetailsBinding.descUviText.setText(details.getUvi()+"");
        mForecastDetailsBinding.descSunriseText.setText( getTime(details.getSunrise()));
        mForecastDetailsBinding.descSunsetText.setText(getTime(details.getSunset()));
        mForecastDetailsBinding.backBtn.setOnClickListener(v -> getActivity().onBackPressed());
    }
}