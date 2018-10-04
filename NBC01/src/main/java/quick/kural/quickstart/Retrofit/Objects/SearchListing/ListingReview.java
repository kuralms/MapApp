
package quick.kural.quickstart.Retrofit.Objects.SearchListing;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListingReview implements Serializable
{

    @SerializedName("review_id")
    @Expose
    private Integer reviewId;
    @SerializedName("listing_id")
    @Expose
    private Integer listingId;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("user_image")
    @Expose
    private String userImage;
    @SerializedName("review")
    @Expose
    private String review;
    @SerializedName("star_rating")
    @Expose
    private Integer starRating;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("status")
    @Expose
    private Integer status;
    private final static long serialVersionUID = 1213487642184127552L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ListingReview() {
    }

    /**
     * 
     * @param updatedAt
     * @param starRating
     * @param status
     * @param createdAt
     * @param userImage
     * @param userName
     * @param userEmail
     * @param listingId
     * @param reviewId
     * @param review
     */
    public ListingReview(Integer reviewId, Integer listingId, String userName, String userEmail, String userImage, String review, Integer starRating, String createdAt, String updatedAt, Integer status) {
        super();
        this.reviewId = reviewId;
        this.listingId = listingId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userImage = userImage;
        this.review = review;
        this.starRating = starRating;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getListingId() {
        return listingId;
    }

    public void setListingId(Integer listingId) {
        this.listingId = listingId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getStarRating() {
        return starRating;
    }

    public void setStarRating(Integer starRating) {
        this.starRating = starRating;
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

}
