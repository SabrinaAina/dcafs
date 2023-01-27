package ftmk.bitp3453.dcafs;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private final LayoutInflater layoutInflater;
    private final Vector<Category> categories;

    public CategoryAdapter(LayoutInflater layoutInflater, Vector<Category> categories) {
        this.layoutInflater = layoutInflater;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(layoutInflater.inflate(R.layout.item_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.setCategory(categories.get(position));

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
