package ftmk.bitp3453.dcafs;

import java.io.Serializable;

public class Feedback implements Serializable {
    private String strCategoryType, strDescription;
    private int strRating;

    public Feedback(String strCategoryType, String strDescription, int strRating) {
        this.strCategoryType = strCategoryType;
        this.strDescription = strDescription;
        this.strRating = strRating;
    }

    public Feedback() {

    }

    public String getStrCategoryType() {
        return strCategoryType;
    }

    public void setStrCategoryType(String strCategoryType) {
        this.strCategoryType = strCategoryType;
    }

    public String getStrDescription() {
        return strDescription;
    }

    public void setStrDescription(String strDescription) {
        this.strDescription = strDescription;
    }

    public double getStrRating() {
        return strRating;
    }

    public void setStrRating(int strRating) {
        this.strRating = strRating;
    }
}
