package quick.kural.quickstart.activitys;

import android.Manifest;
import android.location.Location;
import android.location.LocationListener;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;
import quick.kural.quickstart.Adapters.AdapterResultsBelowmap;
import quick.kural.quickstart.R;
import quick.kural.quickstart.Retrofit.ApiInterface;
import quick.kural.quickstart.Retrofit.ApiUtils;

import quick.kural.quickstart.Retrofit.Objects.ListingDetail.RespListingDetails;
import quick.kural.quickstart.Retrofit.Objects.MasterCat.RespMasterCategorie;
import quick.kural.quickstart.Retrofit.Objects.Obj_vp_data;
import quick.kural.quickstart.Retrofit.Objects.SearchListing.Datum;
import quick.kural.quickstart.activitys.FragmentDialouge.FragmentListingDetail;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RuntimePermissions
public class MapsActivity extends
        FragmentActivity implements
        OnMapReadyCallback,
        AdapterResultsBelowmap.RecylerGridInterface,
        LocationListener,
        FragmentListingDetail.FrgamentInterface{


    BottomSheetBehavior sheetBehavior;
    ApiInterface mAPIService;

    FragmentListingDetail fragmentListingDetail;

    List<quick.kural.quickstart.Retrofit.Objects.SearchListing.Datum> list_search_list = new ArrayList<>();
    quick.kural.quickstart.Retrofit.Objects.SearchListing.Datum obj_search_list;


    @BindView(R.id.bootom_sheet_with_recycler)
    LinearLayout ll_btmSheet;

    @BindView(R.id.recycler_view_map)
    RecyclerView rv_grid;

    AdapterResultsBelowmap rv_adapter;
    private GoogleMap gMap;
    private Boolean iScurentLocatoinMarked = false;

    quick.kural.quickstart.Retrofit.Objects.ListingDetail.Data ListingItemDetail;



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
        setContentView(R.layout.activity_map_with_btm_list);
        ButterKnife.bind(this);
        mAPIService = ApiUtils.getAPIService();
        MapsActivityPermissionsDispatcher.getLocationWithPermissionCheck(this);

        try {
            list_search_list = (List<Datum>) getIntent().getSerializableExtra("searchObj");
        } catch (Exception e) {
            e.printStackTrace();
        }


        mtd_list_sample();

        sheetBehavior = BottomSheetBehavior.from(ll_btmSheet);

        sheetBehavior.setPeekHeight(100);
        /**
         * bottom sheet state change listener
         * we are changing button text when sheet changed state
         * */
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {

                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {

                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }





    private void mtd_list_sample() {




        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);


        DividerItemDecoration itemDecorator = new DividerItemDecoration(getApplicationContext(),
                DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getApplicationContext(),
                                                   R.drawable.grid_recycler_divider));
        rv_grid.addItemDecoration(itemDecorator);
        rv_grid.setLayoutManager(gridLayoutManager);
        rv_adapter = new AdapterResultsBelowmap(getApplicationContext(),MapsActivity.this,list_search_list);
        rv_grid.setAdapter(rv_adapter);


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
            googleMap.setMyLocationEnabled(true);
        } catch (SecurityException se) {

        }

        //Edit the following as per you needs
        googleMap.setTrafficEnabled(false);
        googleMap.setIndoorEnabled(true);
        googleMap.setBuildingsEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);


        for (int i = 0; i <list_search_list.size() ; i++) {
            Double lat,lon;

            lat = Double.parseDouble(list_search_list.get(i).getLatitude());
            lon = Double.parseDouble(list_search_list.get(i).getLongitude());

            LatLng placeLocation = new LatLng(lat, lon); //Make them global
            gMap.addMarker(new MarkerOptions().position(placeLocation));

            if(i == 0){

                gMap.moveCamera(CameraUpdateFactory.newLatLng(placeLocation));
                gMap.animateCamera(CameraUpdateFactory.zoomTo(10), 1000, null);
            }

            //Log.d("loccc",""+lat+"--"+lon);

        }


    }

    public void mtd_showDialougeFragment(){

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        fragmentListingDetail = fragmentListingDetail.newInstance("details",1);
        fragmentListingDetail.setCancelable(false);
        fragmentListingDetail.show(ft, "verify");


    }


    @Override
    public void recyclerItemClicked(int positionOfClick) {



        mtd_get_listing_details();
    }

    private void mtd_get_listing_details() {

        mAPIService.rf_listing_detail().enqueue(new Callback<RespListingDetails>() {
            @Override
            public void onResponse(Call<RespListingDetails> call, Response<RespListingDetails> response) {

                if(response.body().getStatus().equals(200)) {
                    ListingItemDetail = response.body().getData();
                    mtd_showDialougeFragment();
                }

            }

            @Override
            public void onFailure(Call<RespListingDetails> call, Throwable t) {



            }
        } );
        }

    @Override
    public void onLocationChanged(Location location) {

        /*if(!iScurentLocatoinMarked) {
            LatLng placeLocation = new LatLng(location.getLatitude(), location.getLongitude()); //Make them global
            gMap.addMarker(new MarkerOptions().position(placeLocation)
                    .title("YOu Are here"));
            gMap.moveCamera(CameraUpdateFactory.newLatLng(placeLocation));
            gMap.animateCamera(CameraUpdateFactory.zoomTo(5), 15000, null);
        }
*/
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

    @Override
    public void btn_on_fragamnet_item(Boolean accepted) {

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();




    }
}
