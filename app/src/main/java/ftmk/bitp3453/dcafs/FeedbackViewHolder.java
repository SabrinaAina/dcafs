package ftmk.bitp3453.dcafs;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FeedbackViewHolder extends RecyclerView.ViewHolder {

    private  final TextView lblCategoryType, lblDescription, lblRating;

    public FeedbackViewHolder(@NonNull View itemView) {
        super(itemView);
        this.lblCategoryType = itemView.findViewById(R.id.textViewCategory);
        this.lblDescription = itemView.findViewById(R.id.textViewFeedbackDesc);
        this.lblRating = itemView.findViewById(R.id.textViewFeedbackRate);
    }

    public void setFeedback(Feedback feedback) {
        lblCategoryType.setText("Category: " + feedback.getStrCategoryType());
        lblDescription.setText("Feedback: " + feedback.getStrDescription());
        lblRating.setText("Rating: " + feedback.getStrRating());
    }
}
