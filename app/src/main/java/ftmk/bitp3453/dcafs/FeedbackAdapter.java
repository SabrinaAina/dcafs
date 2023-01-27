package ftmk.bitp3453.dcafs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Vector;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackViewHolder> {

    private final LayoutInflater layoutInflater;
    private final Vector<Feedback> feedbacks;

    public FeedbackAdapter(LayoutInflater layoutInflater, Vector<Feedback> feedbacks) {
        this.layoutInflater = layoutInflater;
        this.feedbacks = feedbacks;
    }

    @NonNull
    @Override
    public FeedbackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FeedbackViewHolder(layoutInflater.inflate(R.layout.item_feedback,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackViewHolder holder, int position) {
        holder.setFeedback(feedbacks.get(position));
    }

    @Override
    public int getItemCount() {
        return feedbacks.size();
    }
}
