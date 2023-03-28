package edu.uga.cs.project4;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizQuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizQuestionFragment extends Fragment {

    private static final String[] questions = {
            "Which continent is this country in?",
            "Which continent is this country in?",
            "Which continent is this country in?",
            "Which continent is this country in?",
            "Which continent is this country in?",
            "Which continent is this country in?"
    };

    // Array of Quiz Questions
    private static final String[] answerChoices = {
            "North America \nEurope \nAsia",
            "South America \nAfrica \nEurope",
            "Europe \nNorth America \nAsia",
            "Asia \nAntarctica \nAfrica",
            "South America \nNorth America \nEurope",
            "Asia \nSouth America \nEurope"
    };

    // which Android version to display in the fragment
    private int quizNum;

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
        TextView highlightsView = view.findViewById(R.id.highlightsView);



        titleView.setText(questions[quizNum]);
        highlightsView.setText( answerChoices[quizNum]);
    } //onViewCreated

    public static int getNumberOfQuestions() {
        return questions.length;
    } //getNumberOfQuestions

} //QuizQuestionFragment