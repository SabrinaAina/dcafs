package ftmk.bitp3453.dcafs;

import androidx.annotation.Nullable;
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

import ftmk.bitp3453.dcafs.databinding.ActivityCategoryBinding;
import ftmk.bitp3453.dcafs.databinding.ActivityDisplayAllCategoryBinding;

public class DisplayAllCategoryActivity extends AppCompatActivity {
    private ActivityDisplayAllCategoryBinding binding;
    private Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDisplayAllCategoryBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

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

//                String categoryID = binding.edtCategoryID.getText().toString();
                Map<String, String> params = new HashMap<>();
                params.put("selectFn", "fnAllCategory");
//                params.put("categoryID", categoryID);

                return params;
            }

        };
        requestQueue.add(stringRequest);
    }

}