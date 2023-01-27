package ftmk.bitp3453.dcafs;

import android.app.Activity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class GetCurrentDateTime {

    Activity activity;
    String url = "https://www.timeapi.io/api/Time/current/coordinate?latitude=3.185092&longitude=101.679337";
    RequestQueue requestQueue;

    public GetCurrentDateTime(Activity activity) {
        this.activity = activity;

        requestQueue = Volley.newRequestQueue(activity);
    }

    public void getDateTime(VolleyCallBack volleyCallBack) {

        JSONObject jsonObject = new JSONObject();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    volleyCallBack.onGetDateTime(response.getString("year"), response.getString("month"), response.getString("day"),
                            response.getString("time"), response.getString("seconds"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
    }

    public interface VolleyCallBack {

        void onGetDateTime(String year, String month, String day, String time, String seconds);
    }
}
