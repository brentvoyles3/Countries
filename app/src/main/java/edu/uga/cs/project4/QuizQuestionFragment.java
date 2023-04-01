package edu.uga.cs.project4;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizQuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizQuestionFragment extends Fragment {

    static List<Countries> countryQuestions;
    CountriesData quizData;


    private static final String[] questions = {
            "1. Which continent is this country in?",
            "2. Which continent is this country in?",
            "3. Which continent is this country in?",
            "4. Which continent is this country in?",
            "5. Which continent is this country in?",
            "6. Which continent is this country in?"
    };

    // Array of Quiz Questions
    private static final String[] answerChoices = {
            "North America",
            "South America",
            "Europe",
            "Africa",
            "Antarctica",
            "Asia",
            "Oceania"
    };

    // which Android version to display in the fragment
    private int quizNum;

    /////////
    private CountriesDBHelper db;
    /*
    // pairs with the commented out "private class QuizDBReader extends AsyncTask<Void, List<Countries>> {}" class at the bottom
    private static final String TAG = "QuizQuestionFragment";
    private CountriesData countriesData = null;
    private List<Countries> countriesList;
    private RecyclerView recyclerView;
    private QuizRecyclerAdapter recyclerAdapter;
    */

    public QuizQuestionFragment() {
        // Required empty public constructor
    } //QuizQuestionFragment Constructor


    public static QuizQuestionFragment newInstance(int quizNum) {
        QuizQuestionFragment fragment = new QuizQuestionFragment();
        Bundle args = new Bundle();
        args.putInt("quizNum", quizNum);
        fragment.setArguments(args);
        return fragment;
    } //newInstance

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            quizNum = getArguments().getInt("quizNum");
        } //if
    } //onCreate

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_question, container, false);
    } //onCreateView

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        //public void onActivityCreated(Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView titleView = view.findViewById(R.id.titleView);

        ////////
        TextView countryName = view.findViewById(R.id.getCountry);

        titleView.setText(questions[quizNum]);

        ////////
        //Random ranCountry = new Random();
        //int findCountry = ranCountry.nextInt(195);
        //db = new CountriesDBHelper(this);
        //Cursor select = db.getData(2);
        /*
        Context context = null;
        CountriesData countries = new CountriesData(context);
        //List<Countries> list = new ArrayList<Countries>(195);
        countries.retrieveAllCountries();
        List<Countries> list = new ArrayList<Countries>();
        list.get(4);
        countryName.setText("dbCountry");
         */
        //////////
        /*
        List<Countries> countriesList;
        CountriesData countriesData = null;
        countriesList = new ArrayList<Countries>();
        countriesData = new CountriesData( getActivity() );
        //countriesData.open();
        countriesList = countriesData.retrieveAllCountries();
        Countries c = countriesList.get(4);
        String s = c.getCountryName();
        countryName.setText(s);
         */
        //////////
        /*
        CountriesData countriesData = new CountriesData( getActivity() );
        List<Countries> countriesList = countriesData.retrieveAllCountries();
        Countries c = countriesList.get(0);
        String s = c.getCountryName();
        countryName.setText(s);
         */
        //////////

        RadioButton radioButtonA = (RadioButton) view.findViewById(R.id.radioButtonA);
        RadioButton radioButtonB = (RadioButton) view.findViewById(R.id.radioButtonB);
        RadioButton radioButtonC = (RadioButton) view.findViewById(R.id.radioButtonC);

        Random ran = new Random();
        int choiceA = ran.nextInt(6);
        radioButtonA.setText(answerChoices[choiceA]);
        int choiceB = ran.nextInt(6);
        while (choiceB == choiceA) {
            choiceB = ran.nextInt(6);
        } //while
        radioButtonB.setText(answerChoices[choiceB]);
        int choiceC = ran.nextInt(6);
        while (choiceC == choiceA || choiceC == choiceB) {
            choiceC = ran.nextInt(6);
        } //while
        radioButtonC.setText(answerChoices[choiceC]);
    } //onViewCreated

    public static int getNumberOfQuestions() {
        return questions.length;
    } //getNumberOfQuestions




    /*
    //look at ReviewJobLeadsFragment for readfromdb
    //look at addJobLeadFragment for writetodb
    //incorporate the doInBackground method

    // This is an AsyncTask class (it extends AsyncTask) to perform DB reading of job leads, asynchronously.
    private class QuizDBReader extends AsyncTask<Void, List<Countries>> {
        // This method will run as a background process to read from db.
        // It returns a list of retrieved JobLead objects.
        // It will be automatically invoked by Android, when we call the execute method
        // in the onCreate callback (the job leads review activity is started).
        @Override
        protected List<Countries> doInBackground( Void... params ) {
            List<Countries> cList = countriesData.retrieveAllCountries();

            Log.d( TAG, "QuizDBReader: Job leads retrieved: " + countriesList.size() );

            return countriesList;
        }

        // This method will be automatically called by Android once the db reading
        // background process is finished.  It will then create and set an adapter to provide
        // values for the RecyclerView.
        // onPostExecute is like the notify method in an asynchronous method call discussed in class.
        @Override
        protected void onPostExecute( List<Countries> list ) {
            Log.d( TAG, "QuizDBReader: countriesList.size(): " + list.size() );
            list.addAll( list );

            // create the RecyclerAdapter and set it for the RecyclerView
            recyclerAdapter = new QuizRecyclerAdapter( getActivity(), list );
            recyclerView.setAdapter( recyclerAdapter );
        }
    }
     */

} //QuizQuestionFragment