package com.fm.JogRecord;

import android.app.LoaderManager;
import android.location.Address;
import android.location.LocationListener;
import android.net.wifi.WifiManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Chronometer;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,LocationListener,
        LoaderManager.LoaderCallbacks<Address>
{

    private static final int ADDRESSLOADER_ID=0;
    private static final int INTERVAL=500;
    private static final int FASTESTINTERVAL=16;

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private static final LocationRequest REQUEST=LocationRequest.create().setInterval(INTERVAL)
            .setFastestInterval(FASTESTINTERVAL).setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

    private FusedLocationProviderApi fusedLocationProviderApi= LocationServices.FusedLocationApi;
    private List<LatLng> mRunList=new ArrayList<LatLng>();
    private WifiManager mWifi;
    private boolean mWifiOff=false;
    private long mStartTimeMillis;
    private double mMeter=0.0;
    private double elapsedTime=0.0;
    private double mSpeed=0.0;
    private DatabaseHelper mDpHelper;
    private boolean mStart=false;
    private boolean mFirst=false;
    private boolean mStop=false;
    private boolean mAsked=false;
    private Chronometer mChronometer;


    @Override
    protected  void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putBoolean("ASKED",mAsked);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }
}
