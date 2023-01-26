package ftmk.bitp3453.dcafs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ftmk.bitp3453.dcafs.databinding.ActivitySearchCategoryBinding;

public class SearchCategoryActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    ActivitySearchCategoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySearchCategoryBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

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
                        intent = new Intent(getApplicationContext(), SearchCategoryActivity.class);
                        startActivity(intent);
                        return true;
//                    case R.id.nav_complaint_activity:
//                        intent = new Intent(getApplicationContext(), SecondActivityCam.class);
//                        startActivity(intent);
//                        return true;
//                    case R.id.nav_feedback_activity:_activity:
//                        intent = new Intent(getApplicationContext(), SettingActivity.class);
//                        startActivity(intent);
//                        return true;
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

        binding.btnSearch.setOnClickListener(this::fnSearch);
    }

        public void fnSearch(View view) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String strURL = "http://192.168.188.33/dcafs/category.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, strURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Toast.makeText(getApplicationContext(), "Getting some respond here",
                                    Toast.LENGTH_SHORT).show();

                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                binding.txtVwCategoryID.setText(jsonObject.getString("categoryID"));
                                binding.txtVwCategoryType.setText(jsonObject.getString("categoryType"));

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Unable to fetch info",
                        Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {

                String categoryID = binding.edtCategoryID.getText().toString();
                Map<String, String> params = new HashMap<>();
                params.put("selectFn", "fnSearchCategory");
                params.put("categoryID", categoryID);
                return params;
            }

        };
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){

        if(actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }return super.onOptionsItemSelected(item);
    }
}