package ftmk.bitp3453.dcafs;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    private final TextView lblCategoryId, lblCategoryType;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        this.lblCategoryId = itemView.findViewById(R.id.textViewCategoryId);
        this.lblCategoryType = itemView.findViewById(R.id.textViewCategoryType);
    }

    public void setCategory(Category category) {
        lblCategoryId.setText(category.getCategoryID());
        lblCategoryType.setText(category.getCategoryType() + "\n");
    }
}
