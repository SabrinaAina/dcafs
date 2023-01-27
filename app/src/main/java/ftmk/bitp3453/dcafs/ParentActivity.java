package ftmk.bitp3453.dcafs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import ftmk.bitp3453.dcafs.databinding.ActivityParentBinding;

public class ParentActivity extends AppCompatActivity {

    ActivityParentBinding binding;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityParentBinding.inflate(getLayoutInflater());
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
                        intent = new Intent(getApplicationContext(), ComplainActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Complaint Activity", Toast.LENGTH_SHORT).show();


                        return true;
                    case R.id.nav_logout:
                        intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        return true;
                    default:
                        return false;
                }
            }
        });
        binding.imgVwFeedback.setOnClickListener(this::fnGoFeedback);
        binding.imgVwComplaint.setOnClickListener(this::fnGoComplaint);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fnGoFeedback(View view) {

        Intent intent = new Intent(this, FeedbackActivity.class);
        startActivity(intent);
    }

    private void fnGoComplaint(View view) {
        Intent intent = new Intent(this, ComplainActivity.class);
        startActivity(intent);
    }
}