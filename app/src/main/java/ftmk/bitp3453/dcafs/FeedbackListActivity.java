package ftmk.bitp3453.dcafs;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import ftmk.bitp3453.dcafs.databinding.ActivityFeedbackListBinding;

public class FeedbackListActivity extends AppCompatActivity {

    ActivityFeedbackListBinding binding;
    private Vector<Feedback> feedbacks;
    private Feedback feedback;
    private FeedbackAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("List of Feedback");
        binding = ActivityFeedbackListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        feedbacks = new Vector<>();
        adapter = new FeedbackAdapter(getLayoutInflater(), feedbacks);
        fnDisplayFeedback();

    }

    private void fnDisplayFeedback() {
        String url = "http://192.168.17.94/dcafs/feedback.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray categoriesArray = new JSONArray(response);
                            for (int i = 0; i < categoriesArray.length(); i++) {
                                JSONObject obj = categoriesArray.getJSONObject(i);
                                String category = obj.getString("categoryType");
                                String desc = obj.getString("feedbackDescription");
                                String rating = obj.getString("feedbackRating");
                                feedback = new Feedback(category, desc, rating);
                                feedbacks.add(feedback);


                            }
                            binding.rcvFeedback.setLayoutManager(new LinearLayoutManager(FeedbackListActivity.this));
                            binding.rcvFeedback.setAdapter(adapter);
                            adapter.notifyItemInserted(feedbacks.size());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Unable to load the feedback!", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("selectFn", "fnDisplayFeedback");
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}