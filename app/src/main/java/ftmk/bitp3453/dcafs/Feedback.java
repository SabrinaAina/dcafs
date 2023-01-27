package ftmk.bitp3453.dcafs;

import java.io.Serializable;

public class Feedback implements Serializable {
    private String strCategoryType, strDescription, strRating;

    public Feedback(String strCategoryId, String strDescription, String strRating) {
        this.strCategoryType = strCategoryType;
        this.strDescription = strDescription;
        this.strRating = strRating;
    }

    public Feedback() {

    }

    public String getStrCategoryType() {
        return strCategoryType;
    }

    public void setStrCategoryId(String strCategoryId) {
        this.strCategoryType = strCategoryType;
    }

    public String getStrDescription() {
        return strDescription;
    }

    public void setStrDescription(String strDescription) {
        this.strDescription = strDescription;
    }

    public String getStrRating() {
        return strRating;
    }

    public void setStrRating(String strRating) {
        this.strRating = strRating;
    }
}
