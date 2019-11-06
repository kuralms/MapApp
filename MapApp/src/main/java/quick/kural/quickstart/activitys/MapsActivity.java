package quick.kural.quickstart.activitys;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.multidex.MultiDex;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import butterknife.ButterKnife;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;
import quick.kural.quickstart.R;
import quick.kural.quickstart.activitys.FragmentDialouge.FragmentListingDetail;


@RuntimePermissions
public class MapsActivity extends
        FragmentActivity implements
        OnMapReadyCallback,
        FragmentListingDetail.FrgamentInterface {


    private GoogleMap gMap;
    LocationManager locationManager;
    LocationListener locationListener;




    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    void getLocation(){

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // NOTE: delegate the permission handling to generated method
        MapsActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);

        MapsActivityPermissionsDispatcher.getLocationWithPermissionCheck(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        gMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        try {
            googleMap.setMyLocationEnabled(false);
            googleMap.setTrafficEnabled(false);
            googleMap.setIndoorEnabled(true);
            googleMap.setBuildingsEnabled(true);
            googleMap.getUiSettings().setZoomControlsEnabled(false);
        } catch (SecurityException se) {

        }


           /* Double lat,lon;

            lat = Double.parseDouble("80.199947");
            lon = Double.parseDouble("12.912586");

            LatLng placeLocation = new LatLng(lon, lat); //Make them global
            gMap.addMarker(new MarkerOptions().position(placeLocation));

            gMap.moveCamera(CameraUpdateFactory.newLatLng(placeLocation));
            gMap.animateCamera(CameraUpdateFactory.zoomTo(10), 1000, null);*/


            gMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {

                  //  Toast.makeText(getApplicationContext(),""+latLng,Toast.LENGTH_LONG).show();
                    gMap.addMarker(new MarkerOptions().position(latLng));

                    showPopUpFragment(latLng);
                }
            });

        locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                centreMapOnLocation(location,"Your Location");
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            centreMapOnLocation(lastKnownLocation,"Your Location");
        } else {

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }


    }

    private void showPopUpFragment(LatLng latLng) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }


        ft.addToBackStack(null);
        FragmentListingDetail fragmentListingDetail;
        fragmentListingDetail = FragmentListingDetail.newInstance(""+latLng,0);
        fragmentListingDetail.setCancelable(true);
        fragmentListingDetail.show(ft, "Details");

    }


    public void centreMapOnLocation(Location location, String title){


        LatLng placeLocation = new LatLng(location.getLatitude(), location.getLongitude()); //Make them global
        gMap.addMarker(new MarkerOptions().position(placeLocation)
                .title("You Are here"));
        gMap.moveCamera(CameraUpdateFactory.newLatLng(placeLocation));
        gMap.animateCamera(CameraUpdateFactory.zoomTo(15), 1500, null);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();


    }

    @Override
    public void btn_on_fragamnet_item(Boolean accepted) {

    }
}
