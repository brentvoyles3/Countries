package edu.uga.cs.project4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;

/**
 * Outlines the {@code Fragment} of the Help screen for the user.
 */
public class HelpFragment extends Fragment {

    /**
     * Constructor for {@link HelpFragment}.
     */
    public HelpFragment() {

    }

    /**
     * Creates a new instance of a fragment for the Help screen.
     *
     * @return HelpFragment
     */
    public static HelpFragment newInstance() {
        HelpFragment fragment = new HelpFragment();
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
        super.onCreate(savedInstanceState);
    }

    /**
     * Creates a view containing the fragment for the help screen, which outlines
     * to the user information about how the app works and how to navigate through
     * the app.
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
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        // Inflates the layout for the fragment
        return inflater.inflate( R.layout.fragment_help, container, false );
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
        super.onViewCreated(view,savedInstanceState);

        TextView textView = getView().findViewById( R.id.textView3 );
        String text = getResources().getString( R.string.help_text);
        textView.setText( HtmlCompat.fromHtml( text, HtmlCompat.FROM_HTML_MODE_LEGACY ) );
    }

}
