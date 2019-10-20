package edu.qc.seclass.glm;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private static final float DEFAULT_ZOOM = 15f;
    private static final String TAG = "MapActivity";
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION= Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private Boolean mLocationPermissionGranted = false;

    //widgets
    private EditText mSearchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //mSearchText =new EditText();

        setContentView(R.layout.map_activity);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    private void init(){
//        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener(){
//            @Override
//            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent){
//                if(actionId == EditorInfo.IME_ACTION_SEARCH
//                || actionId == EditorInfo.IME_ACTION_DONE
//                || keyEvent.getAction() == KeyEvent.ACTION_DOWN
//                || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER){
//                    //execute method for searching
//                    geoLocate();
//
//                }
//                return false;
//            }
//        });
    }
//    private void geoLocate(){
//        //String searchString = mSearchText.getText().toString();
//        Geocoder geocoder = new Geocoder(MapActivity.this);
//
//        List<Address> list= new ArrayList<>();
//        try{
//            list = geocoder.getFromLocationName("hospital",1);
//            Log.e("Geo Locate called", "eay");
//        }catch(IOException e){
//            Log.e("GEO", e.getMessage());
//        }
//        if(list.size() > 0){
//            Address address = list.get(0);
//            Log.e("dasjkl", list.get(0).toString());
//            Toast.makeText(this, address.toString(),Toast.LENGTH_LONG).show();
//            moveCamera(new LatLng(address.getLatitude(),address.getLatitude()),DEFAULT_ZOOM,address.getAddressLine(0));
//        }
//
//    }

    private void getDeviceLocation(){
        Log.v("Get Device Location", "In MapActivity Function");
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        try{
            Task location = mFusedLocationProviderClient.getLastLocation();
            location.addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if(task.isSuccessful()){
                        Log.d("MapActivity","Found Location");
                        Location currentLocation = (Location) task.getResult();
                        moveCamera(new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude()), DEFAULT_ZOOM,
                                "My Location");

                    }else{
                        Log.d("MapActivity", "cannot find location");
                        Toast.makeText(MapActivity.this,"unable to get current location", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }catch(SecurityException e){
            Log.e("MapActivity", e.getMessage());
        }

    }

    public void moveCamera(LatLng latlng, float zoom,String title){
        Log.d("MapActivitiy", "Moving Camera to Lat: " +latlng.latitude + " Lon: "+ latlng.longitude );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, zoom));
        MarkerOptions options = new MarkerOptions()
                .position(latlng)
                .title(title);
        mMap.addMarker(options);
    }





    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        getDeviceLocation();
        //puts blue dot for where you are
        mMap.setMyLocationEnabled(true);
        //gets rid of location button
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        mMap.getUiSettings().setCompassEnabled(true);





    }
}

