package edu.uga.cs.project4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import java.util.List;

/**
 * Using the QuizRecyclerAdapter, this class will show all of the past quizzes completed
 * by the user.
 */
public class ReviewPastQuizzesFragment extends AppCompatActivity {

    public static final String DEBUG_TAG = "ReviewPastQuizzesFragment";

    private RecyclerView rView;
    private CountriesData qData = null;

    /**
     * Called to do the initial creation of the fragment.
     *
     * @param savedInstanceState If the fragment is being re-created from
     * a previous saved state, this is the state.
     */
    @Override
    protected void onCreate( Bundle savedInstanceState ) {

        Log.d( DEBUG_TAG, "ReviewPastQuizFragment.onCreate()" );

        super.onCreate( savedInstanceState );
        setContentView( R.layout.fragment_review_past_quizzes );
        rView = findViewById( R.id.recyclerView );
        RecyclerView.LayoutManager layManager = new LinearLayoutManager(this);
        rView.setLayoutManager(layManager);
        qData = new CountriesData( this );
        new QuizzesDBReaderTask().execute();

    }

    /**
     * Used to open and retrieve quizzes from the database.
     */
    private class QuizzesDBReaderTask extends AsyncTask<Void, List<QuizInfo>> {

        /**
         * Opens and reads all of the quizzes from the database and returns of list of them.
         *
         * @param params Void.
         * @return Lise<QuizInfo>
         */
        @Override
        protected List<QuizInfo> doInBackground( Void... params ) {

            qData.open();
            List<QuizInfo> quizList = qData.retrieveAllQuizzes();
            Log.d(DEBUG_TAG, "Size of Quiz: " + quizList.size());
            return quizList;

        }

        /**
         * Generates a {@code QuizRecycler} to set the recycler view of the adapter.
         *
         * @param quizObjectList The quiz result information.
         */
        @Override
        protected void onPostExecute( List<QuizInfo> quizObjectList ) {
            RecyclerView.Adapter<QuizRecycler.QuizHolder> rAdapter = new QuizRecycler(quizObjectList);
            rView.setAdapter(rAdapter);
        }
    }

    /**
     * Fragment has already been created but has not been made visible
     * to the user yet.
     */
    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * Fragment is visible and available for user to interact with.
     */
    @Override
    protected void onResume() {
        if( qData != null )
            qData.open();
        super.onResume();
    }

    /**
     * Fragment will no longer be visible and will go to the background.
     */
    @Override
    protected void onPause() {
        // if the app is paused then closes the database
        if( qData != null )
            qData.close();
        super.onPause();
    }

    /**
     * Fragment is about to be destroyed.
     */
    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * Fragment is going to be restarted.
     */
    @Override
    protected void onRestart() {
        super.onRestart();
    }

    /**
     * Performs any final clean up before the fragment is destroyed.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}