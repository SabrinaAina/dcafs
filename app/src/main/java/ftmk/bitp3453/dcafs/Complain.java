package ftmk.bitp3453.dcafs;

import java.io.Serializable;

public class Complain implements Serializable {
    private String strCategoryType, strDescription;

    public Complain(String strCategoryType, String strDescription) {
        this.strCategoryType = strCategoryType;
        this.strDescription = strDescription;
    }
    public Complain(){

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
}
