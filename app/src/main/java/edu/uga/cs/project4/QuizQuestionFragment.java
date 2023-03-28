package edu.uga.cs.project4;

import android.content.Context;
import android.os.Bundle;

import java.util.Random;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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

        titleView.setText(questions[quizNum]);

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

} //QuizQuestionFragment