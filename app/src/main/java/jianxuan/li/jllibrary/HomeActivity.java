package jianxuan.li.jllibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import jianxuan.li.jllibrary.data.Auth;

public class HomeActivity extends AppCompatActivity {

    TextView txtWelcome;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mToggle;
    NavigationView navView;
    SharedPreferences sharedPre;

    FragmentTransaction ftgTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txtWelcome = (TextView) findViewById(R.id.txtWelcome);
        navView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        sharedPre = getSharedPreferences("user", MODE_PRIVATE);
        String user = sharedPre.getString("username", "");
        txtWelcome.setText("Welcome " + user);

        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open_nav, R.string.close_nav);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SetNavigationDrawer();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void SetNavigationDrawer() {
        navView.setNavigationItemSelectedListener(item -> {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.nav_add:
                    fragment = new AddBookFragment();
                    break;
                case R.id.nav_issue:
                    fragment = new IssueBookFragment();
                    break;
                case R.id.nav_return:
                    fragment = new ReturnBookFragment();
                    break;
                case R.id.nav_search:
                    fragment = new SearchBookFragment();
                    break;
                case R.id.nav_list:
                    fragment = new ListBookFragment();
                    break;
                case R.id.nav_logout:
                    Auth auth = Auth.getInstance();
                    auth.logout();
                    finish();
                    break;
            }

            // close the drawer
            mDrawerLayout.closeDrawer(navView);

            // replace fragment
            if (fragment != null) {
                ftgTrans = getSupportFragmentManager().beginTransaction();
                ftgTrans.replace(R.id.frame, fragment);
                ftgTrans.commit();
            }

            return true;
        });
    }


}