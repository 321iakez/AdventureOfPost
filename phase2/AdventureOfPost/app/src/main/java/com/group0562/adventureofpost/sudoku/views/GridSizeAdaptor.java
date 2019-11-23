package com.group0562.adventureofpost.sudoku.views;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;

public class GridSizeAdaptor extends BaseAdapter {

    /**
     * the list of buttons in order
     */
    private ArrayList<Button> mButtons;

    /**
     * the width and the height of a column
     */
    private int mColumnWidth, mColumnHeight;

    GridSizeAdaptor(ArrayList<Button> buttons, int columnWidth, int columnHeight) {
        mButtons = buttons;
        mColumnWidth = columnWidth;
        mColumnHeight = columnHeight;
    }

    @Override
    public int getCount() {
        return mButtons.size();
    }

    @Override
    public Object getItem(int position) {
        return mButtons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button button;

        if (convertView == null) {
            button = mButtons.get(position);
        } else {
            button = (Button) convertView;
        }

        android.widget.AbsListView.LayoutParams params =
                new android.widget.AbsListView.LayoutParams(mColumnWidth, mColumnHeight);
        button.setLayoutParams(params);

        return button;
    }
}
