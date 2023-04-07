package edu.uga.cs.project4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * The main class for generating and running quiz questions to the user.
 */
public class MainQuizClass extends AppCompatActivity {

    TextView resultTextView;
    TextView resultPercentage;
    TextView message;
    TextView dateTextView;
    Date date;
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    private Button returnHome;
    Button takeAgain;
    @SuppressLint("StaticFieldLeak")
    static RadioGroup radioGroup;
    @SuppressLint("StaticFieldLeak")
    static RadioButton answerSelected;
    static List<Questions> questionsList;
    static ArrayList<Questions> quizQuestions;
    static CountriesData quizQuestionsData;
    public Boolean correct = false;
    static String userAnswer;
    public QuizInfo activeQuiz = new QuizInfo();
    public static ArrayList<String> options;
    public static ArrayList<String> currentOptions = new ArrayList<>();
    public static final String DEBUG_TAG = "MainQuizClass";

    private static final String[] answerChoices = {
            "Africa",
            "Antarctica",
            "Asia",
            "Europe",
            "North America",
            "Oceania",
            "South America"
    };

    /**
     * Used to save the variables in the case that the app is stopped abruptly.
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        //Saves quiz questions, number of questions answered, and quiz score
        super.onSaveInstanceState(outState);
    }

    /**
     * Creates the information for the quiz questions given to the user.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_layout);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), 7);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mViewPager.setAdapter(mSectionsPagerAdapter);

        quizQuestionsData = new CountriesData(this);
        quizQuestionsData.open();
        questionsList = quizQuestionsData.retrieveAllQuizQuestions();

        quizQuestions = new ArrayList<>();
        int size = questionsList.size();
        ArrayList<Integer> list = new ArrayList<>(size);
        for (int i = 0; i <= size; i++) {
            list.add(i);
        }

        //randomly selects six countries to use for the quiz questions
        Random rand = new Random();
        for (int i = 0; i < 7; i++) {
            int index = rand.nextInt(list.size());
            if (index > 0 && index < list.size()) {
                try {
                    quizQuestions.add(questionsList.get(list.get(index)));
                } catch (Exception e) {
                    Log.d(DEBUG_TAG, e.toString());
                }
            } else {
                i--;
            }
            list.remove(index);
        }

        activeQuiz.setQA1(quizQuestions.get(0));
        activeQuiz.setQA2(quizQuestions.get(0));
        activeQuiz.setQA3(quizQuestions.get(0));
        activeQuiz.setQA4(quizQuestions.get(0));
        activeQuiz.setQA5(quizQuestions.get(0));
        activeQuiz.setQA6(quizQuestions.get(0));

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int pos;

            /**
             * Invoked when the current page is scrolled.
             *
             * @param position Position index of the first page currently being displayed.
             *                 Page position+1 will be visible if positionOffset is nonzero.
             * @param positionOffset Value from [0, 1) indicating the offset from the page at position.
             * @param positionOffsetPixels Value in pixels indicating the offset from position.
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                pos=position;
            }

            /**
             * Invoked when a page becomes selected.
             *
             * @param position Position index of the new selected page.
             */
            @Override
            public void onPageSelected(int position) {
                gradeQuestion(pos);

                //if on last page, layout will show results of this quiz
                if(position == mViewPager.getAdapter().getCount() - 1) {

                    setContentView(R.layout.quiz_result);

                    resultTextView = (TextView) findViewById(R.id.score);
                    resultPercentage = (TextView) findViewById(R.id.percentage);
                    message = (TextView) findViewById(R.id.message);
                    dateTextView = (TextView) findViewById(R.id.date);


                    //formats the score
                    int intScore = activeQuiz.getScore();
                    float f = (float) intScore;
                    float percent = (f / 6) * 100;
                    String makePercent = String.format("%.0f%%",percent);
                    String strScore = activeQuiz.getStrScore() + " out of 6";
                    resultTextView.setText(strScore);

                    String strPercentage = "(" + makePercent + ")";
                    resultPercentage.setText(strPercentage);
                    if (intScore <= 2) {
                        resultPercentage.setTextColor(Color.parseColor("#FF0000"));
                    } else if (intScore > 2 && intScore < 5) {
                        resultPercentage.setTextColor(Color.parseColor("#FFA500"));
                    } else {
                        resultPercentage.setTextColor(Color.parseColor("#3DDC84"));
                    }

                    //formats the date
                    date = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy\n   h:mm a");
                    String strDate = formatter.format(date);
                    activeQuiz.setDate(strDate);

                    //displays message to user depending on score
                    String msg = activeQuiz.setMessage(intScore);
                    message.setText(msg);

                    dateTextView.setText(strDate);

                    //allows user to immediately take another quiz
                    takeAgain = (Button) findViewById(R.id.take_again);
                    takeAgain.setOnClickListener(view -> {
                        new QuizDBWriter().execute( activeQuiz );
                        Intent switchActivityIntent = new Intent(MainQuizClass.this, MainQuizClass.class);
                        startActivity(switchActivityIntent);
                    });

                    returnHome = (Button) findViewById(R.id.return_button);
                    returnHome.setOnClickListener(view -> {
                        new QuizDBWriter().execute( activeQuiz );
                        Intent switchActivityIntent = new Intent(MainQuizClass.this, MainActivity.class);
                        startActivity(switchActivityIntent);
                    });

                }

            }

            /**
             * Called when the scroll state changes.
             *
             * @param state The new scroll state.
             */
            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d(DEBUG_TAG, "pos: " + pos);
            }

            /**
             * Method to determine if the user's selected answer choice was correct.
             *
             * @param position Page position.
             */
            public void gradeQuestion(int position) {
                if(position != 6) {
                    userAnswer = (String) answerSelected.getText();
                    correct = quizQuestions.get(position).gradeQuestion(String.valueOf(userAnswer));
                    if(correct) {
                        activeQuiz.incrementScore();
                    }
                    int questionsAnswered = position + 1;
                    Log.d(DEBUG_TAG, "Selected Answer: " + userAnswer);
                    Log.d(DEBUG_TAG, "Correct Answer: " + quizQuestions.get(position).getContinent());
                    Log.d(DEBUG_TAG, "isCorrect: " + correct);
                    Log.d(DEBUG_TAG, "Current Score: " + activeQuiz.getScore() + "/" + questionsAnswered);
                }
            }
        });

    }

    /**
     * Draws from FragmentPagerAdapter to represent each page as a fragment.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private final int mSize;

        /**
         * Constructor for {@link SectionsPagerAdapter}.
         */
        public SectionsPagerAdapter(FragmentManager fragmentManager, int size) {
            super(fragmentManager);
            this.mSize = size;
        }

        /**
         * Returns the fragment associated with that position.
         *
         * @param position Page position.
         * @return Fragment
         */
        @NonNull
        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        /**
         * Returns the number of views available.
         *
         * @return int
         */
        @Override
        public int getCount() {
            return mSize;
        }

        /**
         * Obtains a title screen to describe the specified page.
         *
         * @param position The position of the title requested
         * @return CharSequence
         */
        @Override
        public CharSequence getPageTitle(int position) {
            int questionsAnswered = position + 1;
            return "Section " + questionsAnswered;
        }

    }

    /**
     * A {@code Fragment} which is laid over the ViewPager.
     */
    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";
        private int questionNum;
        private TextView question;
        private RadioButton option1;
        private RadioButton option2;
        private RadioButton option3;
        View rootView;

        /**
         * Creates a new instance of a {@code PlaceholderFragment}.
         *
         * @param sectionNumber Section number the quiz is currently on.
         * @return PlaceholderFragment
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        /**
         * Constructor for {@link PlaceholderFragment}.
         */
        public PlaceholderFragment() {

        }

        /**
         * Creates the fragment of the quiz.
         *
         * @param savedInstanceState If the fragment is being re-created from
         * a previous saved state, this is the state.
         */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            if (getArguments() == null) {
                questionNum = -1;
            } else {
                questionNum = getArguments().getInt(ARG_SECTION_NUMBER);
            }

        }

        /**
         * Creates the view of the quiz question to display for the user to take the quiz.
         *
         * @param inflater The LayoutInflater object that can be used to inflate
         * any views in the fragment,
         * @param container If non-null, this is the parent view that the fragment's
         * UI should be attached to.  The fragment should not add the view itself,
         * but this can be used to generate the LayoutParams of the view.
         * @param savedInstanceState If non-null, this fragment is being re-constructed
         * from a previous saved state as given here.
         *
         * @return View
         */
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.quiz_question, container, false);
            initialize();
            return rootView;
        }

        /**
         * Fragment is visible and available for user to interact with.
         */
        @Override
        public void onResume() {
            super.onResume();
            initialize();
        }

        /**
         * Initializes the views of the quiz.
         */
        //initializes the variables for the UI controls on the quiz
        public void initialize() {
            question = (TextView) rootView.findViewById(R.id.textView1);
            option1 = (RadioButton) rootView.findViewById(R.id.radioButton);
            option2 = (RadioButton) rootView.findViewById(R.id.radioButton2);
            option3 = (RadioButton) rootView.findViewById(R.id.radioButton3);
            radioGroup = rootView.findViewById(R.id.radioGroup);

            Log.d(DEBUG_TAG, "onCreateView called");
            radioGroup.setOnCheckedChangeListener((group, checkedId) -> answerSelected = rootView.findViewById(checkedId));
        }

        /**
         * Called when the fragment's activity has been created. Populates quiz
         * questions with random answer choices and one correct answer choice,
         * and randomizes the order of these answer choices.
         *
         * @param savedInstanceState If the fragment is being re-created from
         * a previous saved state, this is the state.
         */
        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            Log.d( DEBUG_TAG, "onBindViewHolder: " + quizQuestions );

            if(savedInstanceState == null) {
                ArrayList<Integer> intList = new ArrayList<>(3);
                for (int i = 0; i <= 2; i++) {
                    intList.add(i);
                }

                options = new ArrayList<>();

                String A = quizQuestions.get(questionNum - 1).getContinent();
                Log.d(DEBUG_TAG, "A: " + A);
                options.add(quizQuestions.get(questionNum - 1).getContinent());

                Random ran = new Random();
                int ranChoiceB = ran.nextInt(6);
                while (A.equals(answerChoices[ranChoiceB])) {
                    Log.d(DEBUG_TAG, "A and B are the same, try again");
                    ranChoiceB = getRandomWithExclusion(ran, 0, 6, ranChoiceB);
                }
                Log.d(DEBUG_TAG, "B: " + answerChoices[ranChoiceB]);
                quizQuestions.get(questionNum - 1).setOption1(answerChoices[ranChoiceB]);
                options.add(quizQuestions.get(questionNum - 1).getOption1());

                int ranChoiceC = getRandomWithExclusion(ran, 0, 6, ranChoiceB);
                while (A.equals(answerChoices[ranChoiceC])) {
                    Log.d(DEBUG_TAG, "Two choices are the same, try again");
                    ranChoiceC = getRandomWithExclusion(ran, 0, 6, ranChoiceB, ranChoiceC);
                } //while
                quizQuestions.get(questionNum - 1).setOption2(answerChoices[ranChoiceC]);
                options.add(quizQuestions.get(questionNum - 1).getOption2());

                Collections.shuffle(options);
            }

            //displays the question to be asked on each page
            String questions = questionNum + ". What continent is \n" + quizQuestions.get(questionNum - 1).getCountry() + " in?";
            int questionLength = questions.length() -  4;
            SpannableString ss = new SpannableString(questions);
            ss.setSpan(new StyleSpan(Typeface.BOLD), 21, questionLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(new StyleSpan(Typeface.ITALIC), 21, questionLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            question.setText(ss);

            String opt1 = options.get(0);
            String opt2 = options.get(1);
            String opt3 = options.get(2);
            option1.setText(opt1);
            option2.setText(opt2);
            option3.setText(opt3);

        }

        /**
         * Gets a random value and ensures that random values are not reused.
         *
         * @param rand Random number.
         * @param start Start boundary.
         * @param end End boundary.
         * @param exclude Numbers to exclude.
         * @return int
         */
        public int getRandomWithExclusion(Random rand, int start, int end, int... exclude) {
            int random = start + rand.nextInt(end - start + 1 - exclude.length);
            for (int ex : exclude) {
                if (random < ex) {
                    break;
                }
                random++;
            }
            return random;
        }

        /**
         * Called to ask fragment to save its current dynamic state, so that later
         * it can be reconstructed in a new instance of its process.
         *
         * @param outState Bundle in which to place your saved state.
         */
        @Override
        public void onSaveInstanceState(@NonNull Bundle outState) {
            super.onSaveInstanceState(outState);
        }

        /**
         * Fragment will no longer be visible and will go to the background.
         */
        @Override
        public void onPause() {
            super.onPause();
            currentOptions = options;
        }

        /**
         * Performs any final clean up before the fragment is destroyed.
         */
        @Override
        public void onDestroy()
        {
            super.onDestroy();
        }
    }

    /**
     * An AsyncTask to store quizzes taken by the user to the database as the quizzes
     * are completed.
     */
    public class QuizDBWriter extends AsyncTask<QuizInfo, QuizInfo> {

        /**
         * Runs as a background process to write into the db.
         * @param quiz The quiz object.
         * @return QuizInfo
         */
        @Override
        protected QuizInfo doInBackground( QuizInfo... quiz ) {
            quizQuestionsData.storeQuiz( quiz[0] );
            return quiz[0];
        }

        /**
         * This method will be automatically called by Android once the writing to the database
         * in a background process has finished.
         * @param quiz The quiz object.
         */
        @Override
        protected void onPostExecute( QuizInfo quiz ) {
            // Show a quick confirmation message
            //Toast.makeText( getActivity(), "Quiz result created for " + quiz.getId(),Toast.LENGTH_SHORT).show();

            Log.d( "QUIZDBWRITER", "Quiz saved: " + quiz );
        }
    }

}