package edu.uga.cs.project4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

/**
 * The main activity class. It just sets listeners for the buttons.
 */
public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;

    /**
     * Creates an instance of the application.
     *
     * @param savedInstanceState Contains data it most recently supplied.
     */
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        // assigning ID of the toolbar to a variable
        toolbar = findViewById( R.id.toolbar );

        // Find our drawer view
        drawerLayout = (DrawerLayout) findViewById( R.id.drawer_layout );
        drawerToggle = setupDrawerToggle();

        drawerToggle.setDrawerIndicatorEnabled( true );
        drawerToggle.syncState();

        // Connect DrawerLayout events to the ActionBarToggle
        drawerLayout.addDrawerListener( drawerToggle );

        // Find the drawer view
        navigationView = findViewById( R.id.nvView );
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    selectDrawerItem( menuItem );
                    return true;
                });
    }

    /**
     * Allows user to select from a variety of tasks such as taking the quiz,
     * viewing the results of past quizzes, getting help, or closing the app.
     *
     * @param menuItem Menu information for the drawer.
     */
    public void selectDrawerItem( MenuItem menuItem ) {
        Fragment fragment = null;

        // Create a new fragment based on the used selection in the nav drawer
        switch( menuItem.getItemId() ) {
            case R.id.take_quiz:
                Intent switchActivityIntent = new Intent(this, MainQuizClass.class);
                startActivity(switchActivityIntent);
                break;
            case R.id.review_history:
                fragment = new ReviewPastQuizzesFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace( R.id.fragmentContainerView, fragment).addToBackStack("main screen" ).commit();
                break;
            //Intent move = new Intent(this, ReviewPastQuizzesFragment.class);
            //startActivity(move);
            //break;
            case R.id.help:
                fragment = new HelpFragment();
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction().replace( R.id.fragmentContainerView, fragment).addToBackStack("main screen" ).commit();
                break;
            case R.id.close:
                finish();
                break;
            default:
                return;
        }

        // Close the navigation drawer
        drawerLayout.closeDrawers();
    }

    /**
     * Generates the setup for the drawer menu.
     *
     * @return ActionBarDrawerToggle
     */
    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        //return new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open,  R.string.drawer_close );
        return new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open,  R.string.drawer_close);
    }

    /**
     * Called when activity start-up is complete after onStart().
     *
     * @param savedInstanceState Contains data it most recently supplied.
     */
    @Override
    protected void onPostCreate( Bundle savedInstanceState ) {
        super.onPostCreate( savedInstanceState );
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    /**
     * Callback method that receives a {@code Configuration} object specifying
     * the new device configuration.
     *
     * @param newConfig The new device configuration
     */
    @Override
    public void onConfigurationChanged( @NonNull Configuration newConfig ) {
        super.onConfigurationChanged( newConfig );
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged( newConfig );
    }

    /**
     * Determine if and which menu item was selected.
     *
     * @param item The MenuItem.
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
        if( drawerToggle.onOptionsItemSelected( item ) ) {
            return true;
        }
        return super.onOptionsItemSelected( item );
    }

}