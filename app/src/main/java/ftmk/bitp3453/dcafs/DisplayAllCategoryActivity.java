package ftmk.bitp3453.dcafs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
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
import com.google.android.material.navigation.NavigationView;

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

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
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
        setTitle("List of Category");

        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout, R.string.nav_open,
                R.string.nav_close);

        actionBarDrawerToggle.syncState();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = findViewById(R.id.nav_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.nav_main_activity:
                        intent = new Intent(getApplicationContext(), AdminActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_addCategory_activity:
                        intent = new Intent(getApplicationContext(), CategoryActivity.class);
                        startActivity(intent);
                        return true;

                    case R.id.nav_categoryList_activity:
                        intent = new Intent(getApplicationContext(), DisplayAllCategoryActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_complaint_activity:
                        intent = new Intent(getApplicationContext(), ComplaintListActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_feedback_activity:_activity:
                    intent = new Intent(getApplicationContext(), FeedbackListActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_logout:
//                        Toast.makeText(getApplicationContext(), "You are logged out! See ya!",
//                                Toast.LENGTH_SHORT).show();
//                        return true;
                        intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        return true;
                    default:
                        return false;
                }
            }
        });
        //set recyclerview for category
        categories = new Vector<>();
        categoryAdapter = new CategoryAdapter(getLayoutInflater(), categories);

        fnGetCategory();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){

        if(actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }return super.onOptionsItemSelected(item);
    }

    public void fnGetCategory() {
        String url = "http://192.168.17.94/dcafs/category.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray categoriesArray = new JSONArray(response);
                            for (int i = 0; i < categoriesArray.length(); i++) {
                                JSONObject obj = categoriesArray.getJSONObject(i);
                                String categoryType = obj.getString("categoryType");
                                category = new Category(categoryType);
                                categories.add(category);
                            }
                            binding.rcvCategory.setLayoutManager(new LinearLayoutManager(DisplayAllCategoryActivity.this));
                            binding.rcvCategory.setAdapter(categoryAdapter);
                            categoryAdapter.notifyItemInserted(categories.size());

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
                params.put("selectFn", "fnAllCategory");
                return params;
            }
        };
          requestQueue.add(stringRequest);
    }

}