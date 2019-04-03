package gr.artibet.vgames;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import gr.artibet.vgames.models.Game;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTop extends Fragment {

    // Class members
    private List<Game> mGameList = null;
    View mView;

    // Default contractor
    public FragmentTop() {
        // Required empty public constructor
    }

    // Set game list
    public void setGameList(List<Game> gameList) {
        mGameList = gameList;
        if (mView != null) {
            setFragment();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_top, container, false);

        setFragment();

        return mView;
    }

    // Set fragment based on game list
    private void setFragment() {

        // Set wait fragment if mGameList is null
        // Otherwise set Results Fragment
        Fragment fragment;
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if (mGameList == null) {
            fragment = new WaitFragment();
            ft.add(R.id.topFragmentContainer, fragment, null);
        } else {
            fragment = new ResultsFragment();
            ((ResultsFragment) fragment).setGameList(mGameList);
            ft.replace(R.id.topFragmentContainer, fragment, null);
        }

        ft.commit();
    }

}
