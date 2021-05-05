package com.kyser.weatherforecast.ui.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.bumptech.glide.Glide;
import com.kyser.weatherforecast.R;
import com.kyser.weatherforecast.databinding.ActivityMainBinding;
import com.kyser.weatherforecast.model.CurrentModel;
import com.kyser.weatherforecast.model.Hourly;
import com.kyser.weatherforecast.services.WallpaperService;
import com.kyser.weatherforecast.ui.view.components.HourlyAdaptor;
import com.kyser.weatherforecast.ui.viewmodel.CurrentWeather;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements LocationListener{

    private long LOCATION_REFRESH_TIME=1000;
    private float  LOCATION_REFRESH_DISTANCE= 5.5F;
    private ActivityMainBinding mainBinding;
    private HourlyAdaptor mHourlyListAdaptor;
    private LocationManager mLocationManager;
    private Location mLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        mHourlyListAdaptor = new HourlyAdaptor(this);
        setLocationManger();
        startSplashAnimation();
        CurrentWeather vm = ViewModelProviders.of(this).get(CurrentWeather.class);
        vm.getmCurrentWeatherObservable().observe(this, currentModel -> {
            setCurrentWeatherValues(currentModel);
            mainBinding.splash.animate().translationYBy(2000).setDuration(2000).setInterpolator(new AccelerateInterpolator()).start();
        });
        vm.getmForecastObservable().observe(this,forecast -> setHourlyList(forecast.getHourly()));
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        navHostFragment.getChildFragmentManager().getFragments();
    }

    private void setCurrentWeatherValues(CurrentModel currentModel){
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
    }

    private void startSplashAnimation() {
        mainBinding.splashImg.startAnimation( AnimationUtils.loadAnimation(this, R.anim.splash_icon));
    }

    private void setLocationManger() {
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (checkPermission()){
            if (isGPSEnabled) {
                if (mLocation == null) {
                    mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME, LOCATION_REFRESH_DISTANCE, this);
                    if (mLocationManager != null) {
                        mLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (mLocation != null) {
                            String city = updateGeocode(mLocation);
                            getCityWallpaper(city);
                            ViewModelProviders.of(this).get(CurrentWeather.class).updateCityInfo(city, mLocation);
                            mLocationManager.removeUpdates(this);
                        }
                    }
                }
            }
           else showSettingsAlert(this);
        }
    }

    private void getCityWallpaper(String city) {
        WallpaperService.getInstance().getCityWallpaper(city, url -> {
            Glide.with(mainBinding.getRoot()).load(url).into(mainBinding.cityBackground);
        });
    }

    private String updateGeocode(Location location) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
           return addresses.get(0).getLocality();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    @Override
    protected void onResume() {
        super.onResume(); setLocationManger();
    }


    private boolean checkPermission() {
        if (ActivityCompat.checkSelfPermission( this,   Manifest.permission.ACCESS_FINE_LOCATION  ) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(  this,   Manifest.permission.ACCESS_COARSE_LOCATION  ) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(  this, new String[]{
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION  }, 100  ) ;
            return false;
        }else {
            return true;
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

    @Override
    public void onLocationChanged(Location location) {
        mLocation= location;
        String city = updateGeocode(location);
        getCityWallpaper(city);
        ViewModelProviders.of(this).get(CurrentWeather.class).updateCityInfo(city, location);
        mLocationManager.removeUpdates(this);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {  }

    @Override
    public void onProviderEnabled(String provider) { }

    @Override
    public void onProviderDisabled(String provider) {  }

    public void showSettingsAlert(Context context){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(R.string.gps_access_title);
        alertDialog.setMessage(R.string.gps_access_desc);
        alertDialog.setPositiveButton(R.string.gps_access_settings, (dialog, which) -> {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            context.startActivity(intent);
        });
        alertDialog.setNegativeButton(R.string.cancle, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }
}