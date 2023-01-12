package ftmk.bitp3453.dcafs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import ftmk.bitp3453.dcafs.databinding.ActivityCategoryBinding;

public class CategoryActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    private ActivityCategoryBinding categoryMainBinding;
    private Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        categoryMainBinding = ActivityCategoryBinding.inflate(getLayoutInflater());

        setContentView(categoryMainBinding.getRoot());

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

        categoryMainBinding.fabAdd.setOnClickListener(this::fnAddToRest);
    }

    private void fnAddToRest(View view) {
        String strURL = "http://192.168.188.33/dcafs/category.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, strURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    Toast.makeText(getApplicationContext(), "Respond from server: " + jsonObject.getString("respond"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Cannot receive respond: ", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String categoryType = categoryMainBinding.edtCategoryType.getText().toString();

                Map<String, String> params = new HashMap<>();
                params.put("selectFn", "fnSaveData");
                params.put("categoryType", categoryType);
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