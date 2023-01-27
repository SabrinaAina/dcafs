package ftmk.bitp3453.dcafs;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

import ftmk.bitp3453.dcafs.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {

    ActivityLoginBinding loginBinding;
    private UserRegister userRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());

        loginBinding.btnLogin.setOnClickListener(this::fnChange);
    }

    private void fnChange(View view) {

        if (TextUtils.isEmpty(loginBinding.edtEmail.getText().toString()) || TextUtils.isEmpty(loginBinding.edtPassword.getText().toString())) {
            Toast.makeText(Login.this, "Empty field not allowed!",Toast.LENGTH_SHORT).show();
        }
        else {
            String strURL = "http://192.168.188.33/dcafs/User.php";
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, strURL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response);
                        Toast.makeText(getApplicationContext(), "Respond from server: " + jsonObject.getString("respond"), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ParentActivity.class);
                        startActivity(intent);

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
                    String email = loginBinding.edtEmail.getText().toString();
                    String password = loginBinding.edtPassword.getText().toString();

                    Map<String, String> params = new HashMap<>();
                    params.put("selectLogin", "fnLogin");
                    params.put("email", email);
                    params.put("password", password);

                    return params;
                }
            };
            requestQueue.add(stringRequest);
        }

    }
}