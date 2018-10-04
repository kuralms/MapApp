package quick.kural.quickstart.activitys;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import quick.kural.quickstart.Adapters.AdapterSearchResults;
import quick.kural.quickstart.R;
import quick.kural.quickstart.Retrofit.ApiInterface;
import quick.kural.quickstart.Retrofit.ApiUtils;
import quick.kural.quickstart.Retrofit.Objects.MasterCat.Datum;
import quick.kural.quickstart.Retrofit.Objects.MasterCat.RespMasterCategorie;
import quick.kural.quickstart.Retrofit.Objects.Obj_vp_data;
import quick.kural.quickstart.Retrofit.Objects.Review.RespReview;
import quick.kural.quickstart.Retrofit.Objects.SearchListing.RespListingSearch;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchActivity extends BaseActivity
        implements AdapterSearchResults.RecylerGridInterface,
        BottomNavigationView.OnNavigationItemReselectedListener {

    @BindView(R.id.spinner_search_cat)
    Spinner spinner_cat;

    @BindView(R.id.recycler_view_grid)
    RecyclerView rv_grid;

    @BindView(R.id.editText_search_string)
    EditText et_searchbar;

    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView btmView;

    AdapterSearchResults rv_adapter;
    private  String All_cat = "-- All --";
    private static final int TIME_INTERVAL = 1500;
    private long mBackPressed;

    SlidingMenu menu;

    CompositeDisposable disposableSpacexResp = new CompositeDisposable();
    ApiInterface mAPIService;


    List<Datum> list_respMasterCategorie = new ArrayList<>();
    Datum obj_respMasterCategorie;



    List<quick.kural.quickstart.Retrofit.Objects.SearchListing.Datum> list_search_list = new ArrayList<>();
    quick.kural.quickstart.Retrofit.Objects.SearchListing.Datum obj_search_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        mAPIService = ApiUtils.getAPIService();


       /* menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.RIGHT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.com_facebook_auth_dialog_background);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.sliding_menu);
*/
        btmView.setOnNavigationItemReselectedListener(this);


        mtd_call_mainCat_api();

      //  mtd_call_review_api();

        et_searchbar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    search_clicked();
                    return true;
                }
                return false;
            }
        });


    }

    @OnClick(R.id.button_search)
    void search_clicked(){

        mtd_call_searchResults(et_searchbar.getText().toString());
        hideKeyboard(et_searchbar);

     /*   if(et_searchbar.getText().length() >= 2 ){
            mtd_call_searchResults(et_searchbar.getText().toString());
            hideKeyboard(et_searchbar);

        }else {

            et_searchbar.setError("Min 2 char");
        }*/


    }
    private void mtd_call_popular() {


        mAPIService.rf_listing_popular().enqueue(new Callback<RespListingSearch>() {
            @Override
            public void onResponse(Call<RespListingSearch> call, Response<RespListingSearch> response) {

                if(response.body().getStatus().equals(200)) {

                    list_search_list.clear();
                    for (int i = 0; i < response.body().getData().size(); i++) {

                        obj_search_list = new quick.kural.quickstart.Retrofit.Objects.SearchListing.Datum(
                                response.body().getData().get(i).getListingId(),
                                response.body().getData().get(i).getListingTitle(),
                                response.body().getData().get(i).getDescription(),
                                response.body().getData().get(i).getLatitude(),
                                response.body().getData().get(i).getLongitude(),
                                response.body().getData().get(i).getLocation(),
                                response.body().getData().get(i).getMCategory(),
                                response.body().getData().get(i).getListingReviews(),
                                response.body().getData().get(i).getListingSliders());

                        list_search_list.add(obj_search_list);
                    }

                   mtd_update_rv_list(list_search_list);


                }
            }

            @Override
            public void onFailure(Call<RespListingSearch> call, Throwable t) {

            }
        });

    }

    private void mtd_call_searchResults(String s) {

        JsonObject jsonObject = new JsonObject();
        if(!spinner_cat.getSelectedItem().toString().equalsIgnoreCase(All_cat) ) {

            jsonObject.addProperty("searchTerm", s);

            jsonObject.addProperty("categoryId", list_respMasterCategorie.get(spinner_cat.getSelectedItemPosition()).getCategoryId());
        }else {
            jsonObject.addProperty("searchTerm", "");

            jsonObject.addProperty("categoryId", 0);

        }



        mAPIService.rf_listing(jsonObject).enqueue(new Callback<RespListingSearch>() {
            @Override
            public void onResponse(Call<RespListingSearch> call, Response<RespListingSearch> response) {

                if(response.body().getStatus().equals(200)) {

                    list_search_list.clear();
                       for (int i = 0; i < response.body().getData().size(); i++) {

                        obj_search_list = new quick.kural.quickstart.Retrofit.Objects.SearchListing.Datum(
                                response.body().getData().get(i).getListingId(),
                                response.body().getData().get(i).getListingTitle(),
                                response.body().getData().get(i).getDescription(),
                                response.body().getData().get(i).getLatitude(),
                                response.body().getData().get(i).getLongitude(),
                                response.body().getData().get(i).getLocation(),
                                response.body().getData().get(i).getMCategory(),
                                response.body().getData().get(i).getListingReviews(),
                                response.body().getData().get(i).getListingSliders());

                       list_search_list.add(obj_search_list);
                    }

                   // mtd_update_rv_list(list_search_list);

                    Intent in_map = new Intent(SearchActivity.this,MapsActivity.class);
                    in_map.putExtra("searchObj", (Serializable) list_search_list);
                    startActivity(in_map);


                }
            }

            @Override
            public void onFailure(Call<RespListingSearch> call, Throwable t) {

            }
        });

    }

    private void mtd_call_review_api() {

        mAPIService.rf_review().enqueue(new Callback<RespReview>() {


            @Override
            public void onResponse(Call<RespReview> call, Response<RespReview> response) {

            }

            @Override
            public void onFailure(Call<RespReview> call, Throwable t) {

            }});


        }




    private void mtd_call_mainCat_api() {


       mAPIService.rf_masterCat().enqueue(new Callback<RespMasterCategorie>() {
           @Override
           public void onResponse(Call<RespMasterCategorie> call, Response<RespMasterCategorie> response) {

               if(response.body().getStatus().equals(200)){

                   ArrayList<String> cate_title = new ArrayList<>();

                   for (int i = 0; i < response.body().getData().size() ; i++) {

                       obj_respMasterCategorie = new Datum(response.body().getData().get(i).getCategoryId(),
                               response.body().getData().get(i).getCategoryName(),
                               response.body().getData().get(i).getCategoryImg(),
                               response.body().getData().get(i).getShortCode(),
                               response.body().getData().get(i).getCreatedAt(),
                               response.body().getData().get(i).getUpdatedAt(),
                               response.body().getData().get(i).getStatus());

                        list_respMasterCategorie.add(obj_respMasterCategorie);
                        if(i == 0){
                        cate_title.add("-- All --");}
                        cate_title.add(response.body().getData().get(i).getCategoryName());
                   }




                   ArrayAdapter<CharSequence> adapter = new ArrayAdapter(SearchActivity.this,
                           android.R.layout.simple_spinner_item, cate_title);
                // Specify the layout to use when the list of choices appears
                   adapter.setDropDownViewResource(android.R.layout.select_dialog_item);
                // Apply the adapter to the spinner
                   spinner_cat.setAdapter(adapter);

                   mtd_call_popular();

               }else {
                   Toast.makeText(SearchActivity.this, "Failed", Toast.LENGTH_SHORT).show();
               }

           }

           @Override
           public void onFailure(Call<RespMasterCategorie> call, Throwable t) {


           }
       });




    }

    private float mtd_calc_width() {

        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;

        return dpWidth;
    }

    @OnClick(R.id.tb_normal_menu)
        void show_hide_menu(){
          //  menu.toggle();
        }

        @OnClick(R.id.editText_search_string)
        void  get_search_string(){
        et_searchbar.requestFocus();


        }

    private void mtd_update_rv_list(List<quick.kural.quickstart.Retrofit.Objects.SearchListing.Datum> list_respMasterCategorie) {


        rv_grid.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        rv_adapter = new AdapterSearchResults(getApplicationContext(),SearchActivity.this, list_respMasterCategorie);
        rv_grid.setAdapter(rv_adapter);

        rv_adapter.notifyDataSetChanged();





    }








    @Override
    public void recyclerItemClicked(int position) {
        //Toast.makeText(this, "Recycler Item CLicked---"+position, Toast.LENGTH_SHORT).show();
        Intent in_map = new Intent(SearchActivity.this,MapsActivity.class);
        in_map.putExtra("searchObj", (Serializable) list_search_list);
        startActivity(in_map);


    }

    @Override
    public void onBackPressed() {



        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();
            return;
        }
        else { Toast.makeText(getBaseContext(), "Tap Back again to exit.", Toast.LENGTH_SHORT).show(); }

        mBackPressed = System.currentTimeMillis();


    }


    @Override
    public void onNavigationItemReselected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.action_search: {
            }
            break;
            case R.id.action_settings: {



            }
            break;
            case R.id.action_navigation: {
            }
            break;

        }
    }
}
