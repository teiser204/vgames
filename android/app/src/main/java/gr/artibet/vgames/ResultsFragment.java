package gr.artibet.vgames;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ResultsFragment extends Fragment {

    // class members
    private String mSearchCriteria;

    // Required empty public constructor
    public ResultsFragment() {

    }

    // Set Search criteria
    public void setSearchCriteria(String criteria) {
        mSearchCriteria = mSearchCriteria;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_results, container, false);
    }

}
