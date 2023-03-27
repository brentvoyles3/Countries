package edu.uga.cs.project4;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class QuizQuestionPagerAdapter extends FragmentStateAdapter {

    public QuizQuestionPagerAdapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    } //QuizQuestionPagerAdapter

    @Override
    public Fragment createFragment(int position){
        return QuizQuestionFragment.newInstance(position);
    } //createFragment

    @Override
    public int getItemCount() {
        return QuizQuestionFragment.getNumberOfQuestions();
    } //getItemCount

} //QuizQuestionPagerAdapter
