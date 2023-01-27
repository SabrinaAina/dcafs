package ftmk.bitp3453.dcafs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class AdminActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout, R.string.nav_open,
                R.string.nav_close);

        actionBarDrawerToggle.syncState();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = findViewById(R.id.nav_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.nav_main_activity:
                        intent = new Intent(getApplicationContext(), AdminActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_addCategory_activity:
                        intent = new Intent(getApplicationContext(), CategoryActivity.class);
                        startActivity(intent);
                        return true;

                    case R.id.nav_categoryList_activity:
                        intent = new Intent(getApplicationContext(), DisplayAllCategoryActivity.class);
                        startActivity(intent);
                        return true;
//                    case R.id.nav_complaint_activity:
//                        intent = new Intent(getApplicationContext(), SecondActivityCam.class);
//                        startActivity(intent);
//                        return true;
//                    case R.id.nav_feedback_activity:_activity:
//                        intent = new Intent(getApplicationContext(), SettingActivity.class);
//                        startActivity(intent);
//                        return true;
                    case R.id.nav_logout:
//                        Toast.makeText(getApplicationContext(), "You are logged out! See ya!",
//                                Toast.LENGTH_SHORT).show();
//                        return true;
                        intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){

        if(actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }return super.onOptionsItemSelected(item);
    }
}