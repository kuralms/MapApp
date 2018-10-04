
package quick.kural.quickstart.Retrofit.Objects.SearchListing;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListingTag implements Serializable
{

    @SerializedName("tag_id")
    @Expose
    private Integer tagId;
    @SerializedName("listing_id")
    @Expose
    private Integer listingId;
    @SerializedName("tag_name")
    @Expose
    private String tagName;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("status")
    @Expose
    private Integer status;
    private final static long serialVersionUID = -1067426904301989832L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ListingTag() {
    }

    /**
     * 
     * @param updatedAt
     * @param status
     * @param createdAt
     * @param tagName
     * @param listingId
     * @param tagId
     */
    public ListingTag(Integer tagId, Integer listingId, String tagName, String createdAt, String updatedAt, Integer status) {
        super();
        this.tagId = tagId;
        this.listingId = listingId;
        this.tagName = tagName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getListingId() {
        return listingId;
    }

    public void setListingId(Integer listingId) {
        this.listingId = listingId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
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
