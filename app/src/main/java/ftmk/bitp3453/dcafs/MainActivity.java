package ftmk.bitp3453.dcafs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import ftmk.bitp3453.dcafs.databinding.ActivityMainBinding;
import ftmk.bitp3453.dcafs.databinding.ActivityParentBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgVwRegister.setOnClickListener(this::fnGoRegister);
        binding.imgVwLogin.setOnClickListener(this::fnGoLogin);
        binding.imgVwLoginAdmin.setOnClickListener(this::fnGoLoginAdmin);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fnGoRegister(View view) {

        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }

    private void fnGoLogin(View view) {

        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    private void fnGoLoginAdmin(View view) {

        Intent intent = new Intent(this, LoginAdmin.class);
        startActivity(intent);
    }
}