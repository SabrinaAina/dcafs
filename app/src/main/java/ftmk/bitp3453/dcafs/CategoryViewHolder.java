package ftmk.bitp3453.dcafs;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    private final TextView lblCategoryType;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        this.lblCategoryType = itemView.findViewById(R.id.textViewCategoryType);
    }

    public void setCategory(Category category) {

            lblCategoryType.setText(category.getCategoryType());

    }
}
