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

import ftmk.bitp3453.dcafs.databinding.ActivityCategoryBinding;
import ftmk.bitp3453.dcafs.databinding.ActivityRegistrationBinding;

public class Registration extends AppCompatActivity {

    private ActivityRegistrationBinding RegistrationBinding;
    private UserRegister userRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        RegistrationBinding = ActivityRegistrationBinding.inflate(getLayoutInflater());

        setContentView(RegistrationBinding.getRoot());

        RegistrationBinding.buttonReg.setOnClickListener(this::fnUserReg);
    }

    private void fnUserReg(View view) {

        String con1 = RegistrationBinding.edtTextName.getText().toString();
        String con2 = RegistrationBinding.edtTextEmail.getText().toString();
        String con3 = RegistrationBinding.edtTextPhoneNumber.getText().toString();
        String con4 = RegistrationBinding.edtTextPassword.getText().toString();


        if (TextUtils.isEmpty(con1) || TextUtils.isEmpty(con2)|| TextUtils.isEmpty(con3)|| TextUtils.isEmpty(con4)) {
            Toast.makeText(Registration.this, "Empty field not allowed!",Toast.LENGTH_SHORT).show();
        }
        else {
            String strURL = "http://192.168.188.82/dcafs/User.php";
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
                    String name = RegistrationBinding.edtTextName.getText().toString();
                    String email = RegistrationBinding.edtTextEmail.getText().toString();
                    String phoneNumber = RegistrationBinding.edtTextPhoneNumber.getText().toString();
                    String password = RegistrationBinding.edtTextPassword.getText().toString();

                    Map<String, String> params = new HashMap<>();
                    params.put("selectFn", "fnSaveData");
                    params.put("name", name);
                    params.put("email", email);
                    params.put("phoneNumber", phoneNumber);
                    params.put("password", password);
                    params.put("userType", "admin");

                    return params;
                }
            };
            requestQueue.add(stringRequest);
        }


    }
}