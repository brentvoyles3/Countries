package edu.uga.cs.project4;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Using the QuizRecyclerAdapter, this class will show all of the past quizzes completed
 * by the user.
 */
public class ReviewPastQuizzesFragment extends Fragment {

    private static final String TAG = "ReviewPastQuizzesFragment";

    private CountriesData quizData = null;
    private List<QuizInfo> quizList;
    private RecyclerView recyclerView;
    private QuizRecyclerAdapter recyclerAdapter;


    /**
     * Constructor for {@link ReviewPastQuizzesFragment}.
     */
    public ReviewPastQuizzesFragment() {
        // Required empty public constructor
    }

    /**
     * Creates the new instance of the {@link ReviewPastQuizzesFragment}.
     *
     * @return ReviewPastQuizzesFragment
     */
    public static ReviewPastQuizzesFragment newInstance() {
        ReviewPastQuizzesFragment fragment = new ReviewPastQuizzesFragment();
        return fragment;
    }

    /**
     * Called to do the initial creation of the fragment.
     *
     * @param savedInstanceState If the fragment is being re-created from
     * a previous saved state, this is the state.
     */
    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        // Enable the search menu population.
        // When the parameter of this method is true, Android will call onCreateOptionsMenu on
        // this fragment, when the options menu is being built for the hosting activity.
        setHasOptionsMenu( true );
    }

    /**
     * Creates a view containing the past quizzes and their results.
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return
     */
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_review_past_quizzes, container, false );
    }

    /**
     * Ensures that the view is fully created.
     *
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    @Override
    public void onViewCreated( @NonNull View view, Bundle savedInstanceState ) {
        super.onViewCreated( view, savedInstanceState );

        // use a linear layout manager for the recycler view
        recyclerView = getView().findViewById( R.id.recyclerView );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( getActivity() );
        recyclerView.setLayoutManager( layoutManager );

        quizList = new ArrayList<>();

        // Create a JobLeadsData instance, since we will need to save a new JobLead to the dn.
        // Note that even though more activities may create their own instances of the JobLeadsData
        // class, we will be using a single instance of the JobLeadsDBHelper object, since
        // that class is a singleton class.
        quizData = new CountriesData( getActivity() );

        // Open that database for reading of the full list of job leads.
        // Note that onResume() hasn't been called yet, so the db open in it
        // was not called yet!
        quizData.open();

        // Execute the retrieval of the job leads in an asynchronous way,
        // without blocking the main UI thread.
        new QuizDBReader().execute();

    }

    /**
     * An AsyncTask class to perform DB reading of, asynchronously.
     */
    private class QuizDBReader extends AsyncTask<Void, List<QuizInfo>> {

        /**
         * Runs as a background process to read from the db and returns a list of retrieved past quizzes.
         * @param params Parameters.
         * @return List<QuizInfo>
         */
        @Override
        protected List<QuizInfo> doInBackground( Void... params ) {
            List<QuizInfo> quizList = quizData.retrieveAllQuizzes();

            Log.d( "QuizDBReader", "ReviewPastQuizzesFragment: Quizzes retrieved: " + quizList.size() );

            return quizList;
        }

        /**
         * Automatically called once the db reading background process is finished. It will then create
         * and set an adapter to provide values for the RecycleView.
         * @param qList The list of past quizzes.
         */
        @Override
        protected void onPostExecute( List<QuizInfo> qList ) {
            Log.d( "QuizDBReade", "QuizDBReader: qList.size(): " + qList.size() );
            quizList.addAll( qList );

            // create the RecyclerAdapter and set it for the RecyclerView
            recyclerAdapter = new QuizRecyclerAdapter( getActivity(), quizList );
            recyclerView.setAdapter( recyclerAdapter );
        }
    }

    /**
     * Creates the menu of option for the user to select from.
     *
     * @param menu The options menu in which you place your items.
     *
     * @param inflater The inflater.
     */
    @Override
    public void onCreateOptionsMenu( @NonNull Menu menu, MenuInflater inflater ) {
        // inflate the menu
        inflater.inflate( R.menu.search_menu, menu );

        // Get the search view
        MenuItem searchMenu = menu.findItem( R.id.appSearchBar );
        SearchView searchView = (SearchView) searchMenu.getActionView();

        // Provide a search hint
        searchView.setQueryHint( "Search words" );

        // Change the background, text, and hint text colors in the search box
        EditText searchEditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text );
        searchEditText.setBackgroundColor( getResources().getColor( R.color.white ) );
        searchEditText.setTextColor( getResources().getColor( R.color.colorPrimaryDark ) );
        searchEditText.setHintTextColor( getResources().getColor( R.color.colorPrimary ) );

        // Set the listener for the search box
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            /**
             * Returns true if the query has been submitted.
             *
             * @param query the query text that is to be submitted.
             *
             * @return boolean
             */
            @Override
            public boolean onQueryTextSubmit( String query ) {
                Log.d( "QuizDBReader", "Query submitted" );
                return false;
            }

            /**
             * Implements an incremental search for the search words.
             * @param newText the new content of the query text field.
             *
             * @return boolean
             */
            @Override
            public boolean onQueryTextChange( String newText ) {
                recyclerAdapter.getFilter().filter( newText );
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    /**
     * Fragment is visible and available for user to interact with.
     */
    @Override
    public void onResume() {
        super.onResume();

        // Open the database
        if( quizData != null && !quizData.isDBOpen() ) {
            quizData.open();
            Log.d( "QuizDBReader", "ReviewPastQuizzesFragment.onResume(): opening DB" );
        }

        // Update the app name in the Action Bar to be the same as the app's name
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle( getResources().getString( R.string.app_name ) );
    }

    /**
     * Fragment will no longer be visible and will go to the background.
     */
    @Override
    public void onPause() {
        super.onPause();

        // close the database in onPause
        if( quizData != null ) {
            quizData.close();
            Log.d( "QuizDBReader", "ReviewPastQuizzesFragment.onPause(): closing DB" );
        }
    }
}