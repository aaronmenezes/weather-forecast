package com.kyser.weatherforecast.ui.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kyser.weatherforecast.R;
import com.kyser.weatherforecast.databinding.FragmentForecastListBinding;
import com.kyser.weatherforecast.model.Daily;
import com.kyser.weatherforecast.ui.view.components.ForecastAdaptor;
import com.kyser.weatherforecast.ui.viewmodel.CurrentWeather;

import java.util.List;

public class ForecastList extends Fragment implements ForecastAdaptor.itemEvent{

    private FragmentForecastListBinding mForecastListBinding;
    private ForecastAdaptor mForecastAdaptor;

    public ForecastList() {  }

    public static ForecastList newInstance() {
        return new ForecastList();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        mForecastListBinding = FragmentForecastListBinding.inflate(inflater, container, false);
        return mForecastListBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mForecastAdaptor = new ForecastAdaptor(getContext(), this);
        CurrentWeather mViewmodel = new ViewModelProvider(getActivity()).get(CurrentWeather.class);
        mViewmodel.getmForecastObservable().observe(getViewLifecycleOwner(),forecast ->{
            setDailyForecast(forecast.getDaily());
        });
    }
    private void setDailyForecast(List<Daily> daily) {
        mForecastListBinding.mainForecastList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
        mForecastListBinding.mainForecastList.setAdapter(mForecastAdaptor);
        mForecastAdaptor.setHourlyList(daily);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mForecastListBinding.mainForecastList.getContext(),RecyclerView.VERTICAL);
        mForecastListBinding.mainForecastList.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onItemCLick(String date, Daily dayForecast) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("daily",dayForecast);
        bundle.putSerializable("date",date);
        ((MainActivity)getActivity()).getNavController().navigate(R.id.action_forecastList_to_forecastDetails,bundle);
    }
}