package edu.qc.seclass.glm;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
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
import android.widget.ZoomControls;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    //Community
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private static final float DEFAULT_ZOOM = 15f;
    private static final LatLng baruchLL = new LatLng(40.741797, -73.9832391);
    private static final LatLng communityGame = new LatLng(40.7438994, -73.9835627);
    private static final LatLng recreationCenter = new LatLng(40.7361824, -73.9757926);
    private static final LatLng GraceChurch = new LatLng(40.7320622, -73.9909958);
    private static final LatLng StuyvesantCC = new LatLng(40.7319563,-73.9805938);


    //Education
    private static final LatLng MPPsySer = new LatLng(40.7420327,-73.9861764);
    private static final LatLng ArtTherapy = new LatLng(40.7386522,-73.9824137);
    private static final LatLng familyCenterBipolar = new LatLng(40.7341009,-73.982765);
    private static final LatLng creativeMental = new LatLng(40.7500527,-73.9816891);
    private static final LatLng yoga = new LatLng(40.7457325,-73.989472);


    //Volunteer
    private static final LatLng Gotham = new LatLng(40.7288563,-73.9793671);
    private static final LatLng children = new LatLng(40.739222,-73.9756799);
    private static final LatLng PresentTense = new LatLng(40.7401037,-73.9855668);


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

    public void addLocation(LatLng latlng, String title) {
        Log.d("MapActivitiy", "Moving Camera to Lat: " + latlng.latitude + " Lon: " + latlng.longitude);
        MarkerOptions options = new MarkerOptions()
                .position(latlng)
                .title(title);
        mMap.addMarker(options);
    }
    public void myLocation(LatLng latlng, float zoom, String title) {
        Log.d("MapActivitiy", "Moving Camera to Lat: " + latlng.latitude + " Lon: " + latlng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, zoom));
        MarkerOptions options = new MarkerOptions();
        options.title(title);
        options.position(latlng);
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMap.addMarker(options);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        Intent in = getIntent();
        String which = in.getStringExtra("whichLocation");

        if (which.equals("comun")) {
            myLocation(baruchLL, DEFAULT_ZOOM, "Baruch College");
            addLocation(communityGame, "Community Gaming");
            addLocation(recreationCenter, "Asser Levy Recreation Center");
            addLocation(GraceChurch, "Grace Church");
            addLocation(StuyvesantCC, "Stuyvesant Town Community Center");
        } else if(which.equals("edu")){
            myLocation(baruchLL, DEFAULT_ZOOM, "Baruch College");
            addLocation(MPPsySer, "Madison Park Psychological Services");
            addLocation(ArtTherapy, "The Art Therapy Practice");
            addLocation(familyCenterBipolar, "Family Center For Bipolar Disorder");
            addLocation(creativeMental, "Creativity Mental Health Counseling");
            addLocation(yoga, "Yoga Union");
        } else if(which.equals("vol")){
            myLocation(baruchLL, DEFAULT_ZOOM , "Baruch College");
            addLocation(Gotham, "NYC Health + Hospitals/ Gotham Health");
            addLocation(children,"Children of Bellevue");
            addLocation(PresentTense, "Present Tense NYC");

        }




    }

}








//Unused Functions and Constants
//          private static final String TAG = "MapActivity";
//          private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
//          private static final String COARSE_LOCATION= Manifest.permission.ACCESS_COARSE_LOCATION;
//          private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
//          private Boolean mLocationPermissionGranted = false;
//    private void init(){
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
//    }
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

//    private void getDeviceLocation() {
//        Log.v("Get Device Location", "In MapActivity Function");
//        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
//        try {
//            Task location = mFusedLocationProviderClient.getLastLocation();
//            location.addOnCompleteListener(new OnCompleteListener() {
//                @Override
//                public void onComplete(@NonNull Task task) {
//                    if (task.isSuccessful()) {
//                        Log.d("MapActivity", "Found Location");
//                        Location currentLocation = (Location) task.getResult();
//                        moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), DEFAULT_ZOOM,
//                                "My Location");
//
//                    } else {
//                        Log.d("MapActivity", "cannot find location");
//                        Toast.makeText(MapActivity.this, "unable to get current location", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//
//        } catch (SecurityException e) {
//            Log.e("MapActivity", e.getMessage());
//        }
//
//    }


