package ftmk.bitp3453.dcafs;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

public class ComplainAdapter extends RecyclerView.Adapter<ComplainViewHolder> {

    private final LayoutInflater layoutInflater;
    private final Vector<Complain> complains;

    public ComplainAdapter(LayoutInflater layoutInflater, Vector<Complain> complains) {
        this.layoutInflater = layoutInflater;
        this.complains = complains;
    }

    @NonNull
    @Override
    public ComplainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ComplainViewHolder(layoutInflater.inflate(R.layout.item_complain,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ComplainViewHolder holder, int position) {
        holder.setComplain(complains.get(position));
    }

    @Override
    public int getItemCount() {
        return complains.size();
    }
}

