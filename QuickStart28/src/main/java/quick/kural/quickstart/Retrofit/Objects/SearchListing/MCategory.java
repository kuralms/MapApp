
package quick.kural.quickstart.Retrofit.Objects.SearchListing;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MCategory implements Serializable
{

    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    private final static long serialVersionUID = 3906460650450487093L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MCategory() {
    }

    /**
     * 
     * @param categoryName
     * @param categoryId
     */
    public MCategory(Integer categoryId, String categoryName) {
        super();
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
