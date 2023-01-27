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

import ftmk.bitp3453.dcafs.databinding.ActivityComplainBinding;

public class ComplainActivity extends AppCompatActivity {

    ArrayList<String> categoryList = new ArrayList<>();
    ArrayAdapter<String> categoryAdapter;
    ActivityComplainBinding complaintBinding;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    String cDate, cTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        complaintBinding = ActivityComplainBinding.inflate(getLayoutInflater());
        setContentView(complaintBinding.getRoot());

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
                        intent = new Intent(getApplicationContext(), ComplainActivity.class);
                        startActivity(intent);
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

        setTitle("Complaint");

        GetCurrentDateTime dateTime = new GetCurrentDateTime(this);
        dateTime.getDateTime(new GetCurrentDateTime.VolleyCallBack() {
            @Override
            public void onGetDateTime(String year, String month, String day, String time, String seconds) {
                cDate = day + "/" + month + "/" + year;
                cTime = time + ":" + seconds;
            }
        });

        fnDisplayCategory();
        complaintBinding.btnSubmit.setOnClickListener(this::fnSubmitComplaint);


    }

    private void fnDisplayCategory() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String strUrl = "http://192.168.17.94/dcafs/complaint.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, strUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                categoryList.add(jsonObject.getString("categoryType"));
                                categoryAdapter = new ArrayAdapter<>(ComplainActivity.this, android.R.layout.simple_spinner_item, categoryList);
                                categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                complaintBinding.spinnerCategory.setAdapter(categoryAdapter);
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


    private void fnSubmitComplaint(View view) {

        String url = "http://192.168.17.94/dcafs/complaint.php";
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

                String catType = complaintBinding.spinnerCategory.getSelectedItem().toString();
                String complaintDesc = complaintBinding.edtCDesc.getText().toString();

                Map<String, String> params = new HashMap<>();
                params.put("selectFn", "fnSendComplaint");
                params.put("categoryType", catType);
                params.put("complaintDate", cDate);
                params.put("complaintTime", cTime);
                params.put("complaintDescription", complaintDesc);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}