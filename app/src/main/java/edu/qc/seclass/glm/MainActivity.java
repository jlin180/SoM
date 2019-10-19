package edu.qc.seclass.glm;

import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import androidx.drawerlayout.widget.DrawerLayout;


import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

//activity
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        /**
         * make sure to use the Toolbar with android support for v7
         * we are adding the support since we got rid of the action bar
         * and we want to use our toolbar as the action bar
         * use activity_main.xml for reference
         */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        /**
         * The navigationView must be implemented for us to be
         * able to set "this" context to be the navigation listener
         * A listener represents the interfaces responsible to handle events.
         *
         * our other option was to pass a new Navigation item
         * but then we have to write a lot more onCreate methods
         */
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //the drawer and toggle are the two views that need to be synchronized
        //the following toggle is a drawerListener
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        //this takes care of the hamburger icon to get to the drawer itself
        toggle.syncState();

        /**
         * we don't want to start the app with an empty activity, so we show the list manager
         * then we can't forget to give the nav_item a "selected" state
         *
         * Now we run into an issue:
         * Remember that your app restarts every time you rotate a screen
         * or leave the app momentarily. This means whatever fragment the user is at will
         * be replaced by the ListManager, which is a poor design
         *
         * But that is fixed by checking if the saved instance was null when we get passed onCreate
         *
         * Now it will only show the ListManager when we first start it or when we leave the app completely
         */
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new NotingFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_noting_item);
        }
    }

    /**
     * We make use of this method to handle the fragments
     * we use the item ID to know which fragment we are calling
     * <p>
     * Then we replace the fragment_container (refer to activity_main)
     * with the corresponding inflater
     * The inflater is returned by the fragment's class
     * which is why we commit a new instance of the class
     *
     * @param menuItem Selected item from the navigation menu
     * @return "true" value for the selected item
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_noting_item:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new NotingFragment()).commit();
               // TextView test =  findViewById(R.id.create_list_tv);

                break;//when this case is handled we will leave the switch segment

            case R.id.nav_education_item:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new EducationFragment()).commit();
                break;

            case R.id.nav_connect_item:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ConnectFragment()).commit();
                break;

            case R.id.nav_hotlines_item:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HotlinesFragment()).commit();
                break;

            case R.id.nav_log_item:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new LogFragment()).commit();
                break;

            case R.id.nav_evaluation_item:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new EvaluationFragment()).commit();
                break;
        }
        return true;
    }




    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        }
    }
}
