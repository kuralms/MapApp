package quick.kural.quickstart.Retrofit;

import com.google.gson.JsonObject;

import java.util.List;

import io.reactivex.Single;

import quick.kural.quickstart.Retrofit.Objects.ListingDetail.RespListingDetails;
import quick.kural.quickstart.Retrofit.Objects.MasterCat.RespMasterCategorie;
import quick.kural.quickstart.Retrofit.Objects.Review.RespReview;
import quick.kural.quickstart.Retrofit.Objects.SearchListing.RespListingSearch;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by kural mughil selvam on 08-10-2017.
 */

public interface ApiInterface {

    @GET("masters/category")
    Call<RespMasterCategorie> rf_masterCat();

    @GET("review")
    Call<RespReview> rf_review();

    @POST("listing")
    Call<RespListingSearch> rf_listing(@Body JsonObject SearchObj);

    @GET("listing/view/10")
    Call<RespListingDetails> rf_listing_detail();

    @GET("listing/popular")
    Call<RespListingSearch> rf_listing_popular();

  /*@Multipart
  @POST("user_signup")
  Call<signupStatus> rf_signUp(@Header(header) String header_value, @Part("first_name") String first_name,
                               @Part("last_name") String last_name,
                               @Part("email") String email,
                               @Part("password") String password,
                               @Part("phone") String phone,
                               @Part("zipcode") String zipcode);

  @Multipart
  @POST("user_login_process")
  Call<LoginResp> rf_signIn(@Header(header) String header_value, @Part("login_email") String login_email,
                            @Part("login_password") String login_password);


  @Multipart
  @POST("social_login_process")
  Call<LoginResp> rf_signIn_social(@Header(header) String header_value, @Part("email") String login_email);
  //https://stackoverflow.com/questions/39953457/how-to-upload-image-file-in-retrofit-2


  @Multipart
  @POST("upload_profile_picture")
  Call<UpdateAccountResp> rf_uploadPic(@Header(header) String header_value, @Part("email") String email,
                                       @Part MultipartBody.Part upload_profile_picture);*/



}