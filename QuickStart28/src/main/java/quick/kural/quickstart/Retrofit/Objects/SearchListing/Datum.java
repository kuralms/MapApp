
package quick.kural.quickstart.Retrofit.Objects.SearchListing;

import java.io.Serializable;
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
    private List<ListingReview> listingReviews = null;
    @SerializedName("listing_tags")
    @Expose
    private List<ListingTag> listingTags = null;
    private final static long serialVersionUID = -5777958476190667790L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Datum() {
    }

    /**
     * 
     * @param listingTags
     * @param mCategory
     * @param location
     * @param description
     * @param longitude
     * @param latitude
     * @param listingReviews
     * @param listingTitle
     * @param listingId
     */
    public Datum(Integer listingId,
                 String listingTitle,
                 String description,
                 String latitude,
                 String longitude,
                 String location,
                 MCategory mCategory,
                 List<ListingReview> listingReviews,
                 List<ListingTag> listingTags) {
        super();
        this.listingId = listingId;
        this.listingTitle = listingTitle;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
        this.mCategory = mCategory;
        this.listingReviews = listingReviews;
        this.listingTags = listingTags;
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

    public List<ListingTag> getListingTags() {
        return listingTags;
    }

    public void setListingTags(List<ListingTag> listingTags) {
        this.listingTags = listingTags;
    }

}
