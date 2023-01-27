package ftmk.bitp3453.dcafs;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ComplainViewHolder extends RecyclerView.ViewHolder {

    private  final TextView lblCategoryType, lblDescription;

    public ComplainViewHolder(@NonNull View itemView) {
        super(itemView);
        this.lblCategoryType = itemView.findViewById(R.id.textViewCategory);
        this.lblDescription = itemView.findViewById(R.id.textViewComplainDesc);
    }

    public void setComplain(Complain complain) {
        lblCategoryType.setText("Category: " + complain.getStrCategoryType());
        lblDescription.setText("Complain: " + complain.getStrDescription());
    }
}
