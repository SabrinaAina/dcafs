package ftmk.bitp3453.dcafs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ftmk.bitp3453.dcafs.databinding.ActivityFeedbackBinding;

public class FeedbackActivity extends AppCompatActivity {

    ArrayList<String> categoryList = new ArrayList<>();
    ArrayAdapter<String> categoryAdapter;
    ActivityFeedbackBinding binding;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    String fDate, fTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFeedbackBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        actionBarDrawerToggle.syncState();

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = findViewById(R.id.nav_parent_menu);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.nav_parent_activity:
                        intent = new Intent(getApplicationContext(), ParentActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_feedback_activity:
                        intent = new Intent(getApplicationContext(), FeedbackActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_complaint_activity:
//                        intent = new Intent(getApplicationContext(), ComplaintActivity.class);
//                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Complaint Activity", Toast.LENGTH_SHORT).show();

                        return true;
                    case R.id.nav_logout:
                        intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                        return true;
                    default:
                        return false;
                }
            }
        });

        setTitle("Feedback");

        GetCurrentDateTime dateTime = new GetCurrentDateTime(this);
        dateTime.getDateTime(new GetCurrentDateTime.VolleyCallBack() {
            @Override
            public void onGetDateTime(String year, String month, String day, String time, String seconds) {
                fDate = day + "/" + month + "/" + year;
                fTime = time + ":" + seconds;
            }
        });

        fnDisplayCategory();
        binding.btnSubmit.setOnClickListener(this::fnSubmitFeedback);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fnDisplayCategory() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String strUrl = "http://192.168.17.94/dcafs/feedback.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, strUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                categoryList.add(jsonObject.getString("categoryType"));
                                categoryAdapter = new ArrayAdapter<>(FeedbackActivity.this, android.R.layout.simple_spinner_item, categoryList);
                                categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                binding.spinnerCategory.setAdapter(categoryAdapter);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Unable to fetch category info", Toast.LENGTH_SHORT).show();
            }
        })
            {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("selectDisplay", "fnDisplayCat");
                    return params;
                }
            };

        requestQueue.add(stringRequest);
    }

    private void fnSubmitFeedback(View view) {

        String url = "http://192.168.17.94/dcafs/feedback.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
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

            }
        })

            {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    String catType = binding.spinnerCategory.getSelectedItem().toString();
                    String feedbackDesc = binding.edtFDesc.getText().toString();
                    String rating = String.valueOf(binding.ratingBarFeedback.getRating());

                    Map<String, String> params = new HashMap<>();
                    params.put("selectFn", "fnSendFeedback");
                    params.put("categoryType", catType);
                    params.put("feedbackDate", fDate);
                    params.put("feedbackTime", fTime);
                    params.put("feedbackDescription", feedbackDesc);
                    params.put("feedbackRating", rating);
                    return params;
                }
            };
        requestQueue.add(stringRequest);
    }
}