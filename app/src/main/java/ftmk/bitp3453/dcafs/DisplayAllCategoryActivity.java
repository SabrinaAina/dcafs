package ftmk.bitp3453.dcafs;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import ftmk.bitp3453.dcafs.databinding.ActivityDisplayAllCategoryBinding;

public class DisplayAllCategoryActivity extends AppCompatActivity {

    ActivityDisplayAllCategoryBinding binding;
    private Category category;
    private Vector<Category> categories;
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all_category);
        binding = ActivityDisplayAllCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //set recyclerview for category
        categories = new Vector<>();
        categoryAdapter = new CategoryAdapter(getLayoutInflater(), categories);

        binding.rcvCategory.setLayoutManager(new LinearLayoutManager(this));
        fnGetCategory();

    }

    public void fnGetCategory() {
        String url = "http://192.168.17.94/dcafs/category.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray categoriesArray = new JSONArray(response);
                            for (int i = 0; i < categoriesArray.length(); i++) {
                                JSONObject categoryJSON = categoriesArray.getJSONObject(i);
                                String id = categoryJSON.getString("categoryID");
                                String title = categoryJSON.getString("categoryType");
                                category = new Category(id, title);
                                categories.add(category);
                            }
                            categoryAdapter.notifyItemInserted(categories.size());
                            binding.rcvCategory.setAdapter(categoryAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Unable to load the category!", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("selectFn", "fnALLCategory");
                return params;
            }
        };
          requestQueue.add(stringRequest);
    }

}