package com.group0562.adventureofpost.sudoku.ui;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;

public class GridSizeAdaptor extends BaseAdapter {

    /**
     * the list of buttons in order
     */
    private ArrayList<Button> buttons;

    /**
     * the width and the height of a column
     */
    private int columnWidth, columnHeight;

    GridSizeAdaptor(ArrayList<Button> buttons, int columnWidth, int columnHeight) {
        this.buttons = buttons;
        this.columnWidth = columnWidth;
        this.columnHeight = columnHeight;
    }

    @Override
    public int getCount() {
        return buttons.size();
    }

    @Override
    public Object getItem(int position) {
        return buttons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button button;

        if (convertView == null) {
            button = buttons.get(position);
        } else {
            button = (Button) convertView;
        }

        AbsListView.LayoutParams params = new AbsListView.LayoutParams(columnWidth, columnHeight);
        button.setLayoutParams(params);

        return button;
    }
}
