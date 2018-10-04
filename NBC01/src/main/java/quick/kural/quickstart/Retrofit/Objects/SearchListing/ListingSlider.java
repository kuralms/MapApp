
package quick.kural.quickstart.Retrofit.Objects.SearchListing;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListingSlider implements Serializable
{

    @SerializedName("slider_id")
    @Expose
    private Integer sliderId;
    @SerializedName("listing_id")
    @Expose
    private Integer listingId;
    @SerializedName("img_path")
    @Expose
    private String imgPath;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("status")
    @Expose
    private Integer status;
    private final static long serialVersionUID = -2017035703394989462L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ListingSlider() {
    }

    /**
     * 
     * @param updatedAt
     * @param status
     * @param createdAt
     * @param imgPath
     * @param sliderId
     * @param listingId
     */
    public ListingSlider(Integer sliderId, Integer listingId, String imgPath, String createdAt, String updatedAt, Integer status) {
        super();
        this.sliderId = sliderId;
        this.listingId = listingId;
        this.imgPath = imgPath;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public Integer getSliderId() {
        return sliderId;
    }

    public void setSliderId(Integer sliderId) {
        this.sliderId = sliderId;
    }

    public Integer getListingId() {
        return listingId;
    }

    public void setListingId(Integer listingId) {
        this.listingId = listingId;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
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
