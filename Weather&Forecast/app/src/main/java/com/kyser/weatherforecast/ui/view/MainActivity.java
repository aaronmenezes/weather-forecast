package com.kyser.weatherforecast.ui.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.kyser.weatherforecast.R;
import com.kyser.weatherforecast.databinding.ActivityMainBinding;
import com.kyser.weatherforecast.model.Hourly;
import com.kyser.weatherforecast.services.WallpaperService;
import com.kyser.weatherforecast.ui.view.components.HourlyAdaptor;
import com.kyser.weatherforecast.ui.viewmodel.CurrentWeather;

import org.apache.commons.lang3.StringUtils;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private long LOCATION_REFRESH_TIME=1000;
    private float  LOCATION_REFRESH_DISTANCE= 5.5F;
    private ActivityMainBinding mainBinding;
    private HourlyAdaptor mHourlyListAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        WallpaperService.getInstance().getCityWallpaper("Mumbai", url -> {
            Glide.with(mainBinding.getRoot()).load(url).into(mainBinding.cityBackground);
        });
        mHourlyListAdaptor = new HourlyAdaptor(this);

        checkPermission();
        CurrentWeather vm =ViewModelProviders.of(this).get(CurrentWeather.class);
        vm.getmCurrentWeatherObservable().observe(this, currentModel -> {
            mainBinding.curWeather.setText(StringUtils.capitalize(currentModel.getWeather().get(0).getDescription()));
            mainBinding.curTemp.setText(new StringBuilder().append(currentModel.getMain().getTemp()).append(" ").append(getString(R.string.celcius)).toString());
            mainBinding.curFeels.setText(new StringBuilder().append(getString(R.string.feels_lbl)).append(" ").append(currentModel.getMain().getFeels_like()).append(" ").append(getString(R.string.celcius)).toString());
            mainBinding.curWind.setText(new StringBuilder().append(getString(R.string.cur_wind_lbl)).append(currentModel.getWind().getSpeed()).toString());
            mainBinding.curHumidity.setText(new StringBuilder().append(getString(R.string.cur_humidity_lbl)).append(currentModel.getMain().getHumidity()).toString());
            mainBinding.curUv.setText(new StringBuilder().append(getString(R.string.cur_uv_index_lbl)).append("1.0").toString());
            mainBinding.curPressure.setText(new StringBuilder().append(getString(R.string.cur_pressure_lbl)).append(currentModel.getMain().getPressure()).toString());
            mainBinding.curVisibility.setText(new StringBuilder().append(getString(R.string.cur_visibility_lbl)).append(currentModel.getVisibility()).toString());
            mainBinding.curDew.setText(new StringBuilder().append(getString(R.string.cur_dew_point_lbl)).append(dewPointCalc(currentModel.getMain().getTemp(), currentModel.getMain().getHumidity())).toString());
            Glide.with(mainBinding.getRoot()).load(getString(R.string.img_icon_url,currentModel.getWeather().get(0).getIcon())).into(mainBinding.curIcon);
        });
        vm.getmForecastObservable().observe(this,forecast ->{
            setHourlyList(forecast.getHourly());
        });
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navHostFragment.getChildFragmentManager().getFragments();

        LocationManager mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
    }

    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission( this,   Manifest.permission.ACCESS_FINE_LOCATION  ) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(  this,   Manifest.permission.ACCESS_COARSE_LOCATION  ) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(  this, new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION  }, 100  ) ;
        }
    }

    private void setHourlyList(List<Hourly> hourly) {
        mainBinding.hourList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false));
        mainBinding.hourList.setAdapter(mHourlyListAdaptor);
        mHourlyListAdaptor.setHourlyList(hourly);
    }

    private String dewPointCalc(String temp, String humidity){
        float T = Float.parseFloat(temp);
        float RH = Float.parseFloat(humidity);
        return String.valueOf((int)(T - ((100 - RH)/5)));
    }

    public NavController getNavController() {
        return Navigation.findNavController(mainBinding.navHostFragment);
    }
}