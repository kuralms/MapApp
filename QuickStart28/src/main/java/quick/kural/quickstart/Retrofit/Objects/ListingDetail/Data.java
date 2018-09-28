
package quick.kural.quickstart.Retrofit.Objects.ListingDetail;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable
{

    @SerializedName("listing_id")
    @Expose
    private Integer listingId;
    @SerializedName("listing_title")
    @Expose
    private String listingTitle;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("sub_category_id")
    @Expose
    private Object subCategoryId;
    @SerializedName("img_path")
    @Expose
    private Object imgPath;
    @SerializedName("keywords")
    @Expose
    private String keywords;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("signature")
    @Expose
    private String signature;
    @SerializedName("working_hours")
    @Expose
    private String workingHours;
    @SerializedName("facebook_link")
    @Expose
    private String facebookLink;
    @SerializedName("twitter_link")
    @Expose
    private String twitterLink;
    @SerializedName("google_link")
    @Expose
    private String googleLink;
    @SerializedName("view_count")
    @Expose
    private Object viewCount;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("m_category")
    @Expose
    private MCategory mCategory;
    @SerializedName("listing_reviews")
    @Expose
    private List<Object> listingReviews = null;
    @SerializedName("listing_tags")
    @Expose
    private List<Object> listingTags = null;
    @SerializedName("listing_opening_hours")
    @Expose
    private List<Object> listingOpeningHours = null;
    @SerializedName("listing_sliders")
    @Expose
    private List<Object> listingSliders = null;
    private final static long serialVersionUID = 8630298320073333788L;

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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Object getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Object subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public Object getImgPath() {
        return imgPath;
    }

    public void setImgPath(Object imgPath) {
        this.imgPath = imgPath;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    public String getTwitterLink() {
        return twitterLink;
    }

    public void setTwitterLink(String twitterLink) {
        this.twitterLink = twitterLink;
    }

    public String getGoogleLink() {
        return googleLink;
    }

    public void setGoogleLink(String googleLink) {
        this.googleLink = googleLink;
    }

    public Object getViewCount() {
        return viewCount;
    }

    public void setViewCount(Object viewCount) {
        this.viewCount = viewCount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public MCategory getMCategory() {
        return mCategory;
    }

    public void setMCategory(MCategory mCategory) {
        this.mCategory = mCategory;
    }

    public List<Object> getListingReviews() {
        return listingReviews;
    }

    public void setListingReviews(List<Object> listingReviews) {
        this.listingReviews = listingReviews;
    }

    public List<Object> getListingTags() {
        return listingTags;
    }

    public void setListingTags(List<Object> listingTags) {
        this.listingTags = listingTags;
    }

    public List<Object> getListingOpeningHours() {
        return listingOpeningHours;
    }

    public void setListingOpeningHours(List<Object> listingOpeningHours) {
        this.listingOpeningHours = listingOpeningHours;
    }

    public List<Object> getListingSliders() {
        return listingSliders;
    }

    public void setListingSliders(List<Object> listingSliders) {
        this.listingSliders = listingSliders;
    }

}
