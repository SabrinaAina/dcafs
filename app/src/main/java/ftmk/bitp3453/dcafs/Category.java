package ftmk.bitp3453.dcafs;

import java.io.Serializable;

public class Category implements Serializable {
    private String categoryType;
    private String categoryID;

    public Category(String categoryType, String categoryID) {
        this.categoryType = categoryType;
        this.categoryID = categoryID;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }
}


