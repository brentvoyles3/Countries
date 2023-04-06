package edu.uga.cs.project4;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * This is an adapter class to display the results of past completed quizzes.
 */
public class QuizRecycler extends RecyclerView.Adapter<QuizRecycler.QuizHolder> {

    public static final String DEBUG_TAG = "QuizRecycler";

    private List<QuizInfo> quizList;

    /**
     * Constructor for {@link QuizRecycler}.
     *
     * @param quizList List of quizzes.
     */
    public QuizRecycler( List<QuizInfo> quizList ) {
        this.quizList = quizList;
    }

    /**
     * Class for the CardHolder view that will display the score and date
     * of each past quiz.
     */
    class QuizHolder extends RecyclerView.ViewHolder {

        TextView dateTextView;
        TextView scoreTextView;

        /**
         * Card representation of each individual quiz.
         *
         * @param itemView View for each individual quiz result.
         */
        public QuizHolder(View itemView ) {
            super(itemView);
            dateTextView = (TextView) itemView.findViewById( R.id.Textdate);
            scoreTextView = (TextView) itemView.findViewById( R.id.Textscore);
        }
    }

    /**
     * Creates a new view holder when there is no existing view holder which the
     * {@code RecyclerView} can reuse.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return QuizHolder
     */
    @Override
    public QuizHolder onCreateViewHolder(ViewGroup parent, int viewType ) {
        View view = LayoutInflater.from( parent.getContext()).inflate( R.layout.quiz_result_card, parent, false );
        return new QuizHolder( view );
    }

    /**
     * Fills in the values of a holder to show the quiz results.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder( QuizHolder holder, int position ) {
        QuizInfo quiz = quizList.get( position );
        Log.d( DEBUG_TAG, "onBindViewHolder: " + quiz );
        Log.d(DEBUG_TAG, "" + quiz.getScore());
        holder.dateTextView.setText( "" + quiz.getScore() );
        holder.scoreTextView.setText( quiz.getDate() );
    }

    /**
     * Returns the number of items on the list.
     *
     * @return int
     */
    @Override
    public int getItemCount() {
        if (quizList != null)
            return quizList.size();
        else
            return 0;
    }

}