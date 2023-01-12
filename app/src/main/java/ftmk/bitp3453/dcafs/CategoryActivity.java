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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import ftmk.bitp3453.dcafs.databinding.ActivityCategoryBinding;

public class CategoryActivity extends AppCompatActivity {

    private ActivityCategoryBinding categoryMainBinding;
    private Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        categoryMainBinding = ActivityCategoryBinding.inflate(getLayoutInflater());

        setContentView(categoryMainBinding.getRoot());

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
}