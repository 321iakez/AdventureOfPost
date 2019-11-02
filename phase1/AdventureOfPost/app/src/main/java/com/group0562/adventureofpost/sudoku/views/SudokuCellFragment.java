package com.group0562.adventureofpost.sudoku.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
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
 * {@link SudokuCellFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class SudokuCellFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private int[][] cells;
    private View view;

    public SudokuCellFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sudoku_cell, container, false);

        // Inflate the 2D array of cell IDs
        cells = new int[][]{
                {R.id.cell00, R.id.cell01, R.id.cell02, R.id.cell03, R.id.cell04, R.id.cell05},
                {R.id.cell10, R.id.cell11, R.id.cell12, R.id.cell13, R.id.cell14, R.id.cell15},
                {R.id.cell20, R.id.cell21, R.id.cell22, R.id.cell23, R.id.cell24, R.id.cell25},
                {R.id.cell30, R.id.cell31, R.id.cell32, R.id.cell33, R.id.cell34, R.id.cell35},
                {R.id.cell40, R.id.cell41, R.id.cell42, R.id.cell43, R.id.cell44, R.id.cell45},
                {R.id.cell50, R.id.cell51, R.id.cell52, R.id.cell53, R.id.cell54, R.id.cell55}
        };

        // Add onClickListener to all cells
        for (int[] row : cells) {
            for (int cellId : row) {
                TextView cell = view.findViewById(cellId);
                cell.setOnClickListener(v -> {
                    int viewRow = Character.getNumericValue(v.getTag().toString().charAt(0));
                    int viewCol = Character.getNumericValue(v.getTag().toString().charAt(1));
                    mListener.onFragmentInteraction(viewRow, viewCol, v);
                });
            }
        }

        return view;
    }

    /**
     * Loads a new background for cell at (row, col).
     *
     * @param row        the row number.
     * @param col        the column number.
     * @param background the drawable ID.
     */
    public void loadBackground(int row, int col, int background) {
        TextView cell = view.findViewById(cells[row][col]);
        cell.setBackgroundResource(background);
    }

    /**
     * Load given value into UI cell at (row, col).
     *
     * @param value the value to be displayed.
     * @param row   the row number.
     * @param col   the column number.
     */
    public void loadValues(int value, int row, int col) {
        TextView cell = view.findViewById(cells[row][col]);
        cell.setText(String.valueOf(value));
        cell.setTextColor(Color.BLACK);
        cell.setTypeface(null, Typeface.BOLD);
    }

    /**
     * Remove value for UI cell at (row, col).
     *
     * @param row the row number.
     * @param col the column number.
     */
    public void removeValue(int row, int col) {
        TextView cell = view.findViewById(cells[row][col]);
        cell.setText("");
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(int row, int col, View view);
    }
}
