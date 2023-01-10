package ftmk.bitp3453.dcafs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ftmk.bitp3453.dcafs.databinding.ActivityDisplayCategoryBinding;

public class DisplayCategoryActivity extends AppCompatActivity {

    ActivityDisplayCategoryBinding activityDisplayCategoryBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityDisplayCategoryBinding = ActivityDisplayCategoryBinding.inflate(getLayoutInflater());

        setContentView(activityDisplayCategoryBinding.getRoot());

        activityDisplayCategoryBinding.btnSearch.setOnClickListener(this::fnSearch);
    }
    public void fnSearch(View view) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String strURL = "http://192.168.188.33/RESTAPI/rest_api.php";
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
                                activityDisplayCategoryBinding.txtVwCategoryID.setText(jsonObject.getString("categoryID"));
                                activityDisplayCategoryBinding.txtVwCategoryType.setText(jsonObject.getString("categoryType"));

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

                String strStudNo = activityDisplayCategoryBinding.edtCategoryID.getText().toString();
                Map<String, String> params = new HashMap<>();
                params.put("selectFn", "fnSearchCategory");
                params.put("categoryID", categoryID);
                return params;
            }

        };
        requestQueue.add(stringRequest);
    }
}