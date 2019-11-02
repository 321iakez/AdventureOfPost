package com.group0562.adventureofpost.sudoku.views;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.group0562.adventureofpost.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SudokuStatsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class SudokuStatsFragment extends Fragment {

    private View view;
    private OnFragmentInteractionListener mListener;

    public SudokuStatsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sudoku_stats, container, false);

        TextView moveStats = view.findViewById(R.id.statsMoveNum);
        moveStats.setText("0");

        TextView conflictStats = view.findViewById(R.id.statsConflictNum);
        conflictStats.setText("0");

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    void updateStats(int newMove, int newConflict) {
        TextView moveStats = view.findViewById(R.id.statsMoveNum);
        moveStats.setText(String.valueOf(newMove));

        TextView conflictStats = view.findViewById(R.id.statsConflictNum);
        conflictStats.setText(String.valueOf(newConflict));
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(View view);
    }
}
