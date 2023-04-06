package edu.uga.cs.project4;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * This is an adapter class for the RecyclerView to show all past quiz results.
 */
public class QuizRecyclerAdapter
        extends RecyclerView.Adapter<QuizRecyclerAdapter.CardHolder>
        implements Filterable {

    public static final String DEBUG_TAG = "QuizRecyclerAdapter";

    private final Context context;
    private List<QuizInfo> values;
    private List<QuizInfo> originalValues;

    /**
     * Constructor for {@link QuizRecyclerAdapter}.
     *
     * @param context The activities context.
     * @param quizList The quiz information.
     */
    public QuizRecyclerAdapter(Context context, List<QuizInfo> quizList ) {
        this.context = context;
        this.values = quizList;
        this.originalValues = new ArrayList<>( quizList );
    }

    /**
     * Resets the originalValues to the current contents of values.
     */
    public void sync()
    {
        originalValues = new ArrayList<>( values );
    }

    /**
     * Class for the CardHolder view that will display the score and date
     * of each past quiz.
     */
    public static class CardHolder extends RecyclerView.ViewHolder {

        TextView date;
        TextView score;

        /**
         * Card representation of each individual quiz.
         *
         * @param itemView View for each individual quiz result.
         */
        public CardHolder( View itemView ) {
            super( itemView );

            score = itemView.findViewById( R.id.Textdate );
            date = itemView.findViewById( R.id.Textscore );
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
     * @return CardHolder
     */
    @NonNull
    @Override
    public CardHolder onCreateViewHolder( ViewGroup parent, int viewType ) {
        // We need to make sure that all CardViews have the same, full width, allowed by the parent view.
        // This is a bit tricky, and we must provide the parent reference (the second param of inflate)
        // and false as the third parameter (don't attach to root).
        // Consequently, the parent view's (the RecyclerView) width will be used (match_parent).
        View view = LayoutInflater.from( parent.getContext()).inflate( R.layout.quiz_result_card, parent, false );
        return new CardHolder( view );
    }

    /**
     * Fills in the values of a holder to show the quiz results.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the list within the adapter's data set.
     */
    @Override
    public void onBindViewHolder( CardHolder holder, int position ) {

        QuizInfo quiz = values.get( position );

        Log.d( DEBUG_TAG, "onBindViewHolder: " + quiz );

        holder.date.setText( quiz.getDate());
        holder.score.setText( quiz.getScore() );
    }

    /**
     * Returns the number of items on the list.
     *
     * @return int
     */
    @Override
    public int getItemCount() {
        if( values != null )
            return values.size();
        else
            return 0;
    }

    /**
     * Returns a filter that can be used to constrain data with a filtering pattern.
     *
     * @return Filter
     */
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            /**
             * Invoked in a worker thread to filter the data according to the constraint.
             *
             * @param constraint The constraint used to filter the data.
             * @return FilterResults
             */
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<QuizInfo> list = new ArrayList<>( originalValues );
                FilterResults filterResults = new FilterResults();
                if (constraint == null || constraint.length() == 0) {
                    filterResults.count = list.size();
                    filterResults.values = list;
                } else {
                    List<QuizInfo> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for ( QuizInfo quiz : list ) {
                        if ( quiz.getDate().toLowerCase().contains( searchStr )) {
                            resultsModel.add( quiz );
                        }
                    }

                    filterResults.count = resultsModel.size();
                    filterResults.values = resultsModel;
                }

                return filterResults;
            }

            /**
             *Invoked when thread to publish the filtering results in the user interface.
             *
             * @param constraint The constraint used to filter the data.
             * @param results The results of the filtering operation.
             */
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                values = (ArrayList<QuizInfo>) results.values;
                notifyDataSetChanged();
                if( values.size() == 0 ) {
                    Toast.makeText( context, "Not Found", Toast.LENGTH_LONG).show();
                }
            }

        };
        return filter;
    }
}
