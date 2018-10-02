
package quick.kural.quickstart.Retrofit.Objects.SearchListing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Serializable
{

    @SerializedName("listing_id")
    @Expose
    private Integer listingId;
    @SerializedName("listing_title")
    @Expose
    private String listingTitle;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("m_category")
    @Expose
    private MCategory mCategory;
    @SerializedName("listing_reviews")
    @Expose
    private List<ListingReview> listingReviews = new ArrayList<ListingReview>();
    @SerializedName("listing_sliders")
    @Expose
    private List<ListingSlider> listingSliders = new ArrayList<ListingSlider>();
    private final static long serialVersionUID = -3271343346088958657L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Datum() {
    }

    /**
     * 
     * @param mCategory
     * @param location
     * @param description
     * @param longitude
     * @param latitude
     * @param listingSliders
     * @param listingReviews
     * @param listingTitle
     * @param listingId
     */
    public Datum(Integer listingId, String listingTitle, String description, String latitude, String longitude, String location, MCategory mCategory, List<ListingReview> listingReviews, List<ListingSlider> listingSliders) {
        super();
        this.listingId = listingId;
        this.listingTitle = listingTitle;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
        this.mCategory = mCategory;
        this.listingReviews = listingReviews;
        this.listingSliders = listingSliders;
    }

    public Integer getListingId() {
        return listingId;
    }

    public void setListingId(Integer listingId) {
        this.listingId = listingId;
    }

    public String getListingTitle() {
        return listingTitle;
    }

    public void setListingTitle(String listingTitle) {
        this.listingTitle = listingTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public MCategory getMCategory() {
        return mCategory;
    }

    public void setMCategory(MCategory mCategory) {
        this.mCategory = mCategory;
    }

    public List<ListingReview> getListingReviews() {
        return listingReviews;
    }

    public void setListingReviews(List<ListingReview> listingReviews) {
        this.listingReviews = listingReviews;
    }

    public List<ListingSlider> getListingSliders() {
        return listingSliders;
    }

    public void setListingSliders(List<ListingSlider> listingSliders) {
        this.listingSliders = listingSliders;
    }

}
