package edu.uga.cs.project4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

/**
 * Initial page for the user displaying an introduction to the app
 * and a transition to get started with the app.
 */
public class SplashActivity extends AppCompatActivity {

    private static final String DEBUG_TAG = "SplashActivity";

    private Button button;

    /**
     * Creates an instance of the application.
     *
     * @param savedInstanceState Contains data it most recently supplied.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(DEBUG_TAG, "SplashActivity.onCreate(): savedInstanceState: " + savedInstanceState);

        // this call will create the UI based on the screen in portrait orientation.
        setContentView(R.layout.activity_splash);

        button = findViewById(R.id.get_started);

        button.setOnClickListener(new View.OnClickListener() {

            /**
             *
             * Called when a view has been clicked and creates a new {@code Intent},
             * which directs the user to the next activity where the user can select
             * the company they would like more information about.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(intent);

            } //onClick
        }); //setOnClickListener

    } //onCreate

    /**
     * Activity has already been created but has not been made visible
     * to the user yet.
     */
    @Override
    protected void onStart() {
        Log.d(DEBUG_TAG, "MainActivity.onStart()");
        super.onStart();
    } //onStart

    /**
     * Activity is visible and available for user to interact with.
     */
    @Override
    protected void onResume() {
        Log.d(DEBUG_TAG, "MainActivity.onResume()");
        super.onResume();
    } //onResume

    /**
     * Activity will no longer be visible and will go to the background.
     */
    @Override
    protected void onPause() {
        Log.d(DEBUG_TAG, "MainActivity.onPause()");
        super.onPause();
    } //onPause

    /**
     * Activity is about to be destroyed.
     */
    @Override
    protected void onStop() {
        Log.d(DEBUG_TAG, "MainActivity.onStop()");
        super.onStop();
    } //on

    /**
     * The current activity is being restarted and transitions from
     * invisible to visible.
     */
    @Override
    protected void onRestart() {
        Log.d(DEBUG_TAG, "MainActivity.onRestart()");
        super.onRestart();
    }//onRestart

    /**
     * Performs any final clean up before an activity is destroyed.
     */
    @Override
    protected void onDestroy() {
        Log.d(DEBUG_TAG, "MainActivity.onDestroy()");
        super.onDestroy();
    } //on

} //SplashActivity