package com.group0562.adventureofpost.sudoku.ui;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;


/**
 * A GridView adaptor that changes the buttons on the GridView based on the grid size user selects.
 */
public class GridSizeAdaptor extends BaseAdapter {

    /**
     * The list of buttons in order.
     */
    private ArrayList<Button> buttons;

    /**
     * A flag determine whether the GridView is a numpad or not.
     */
    private boolean isNumpad;

    /**
     * The width and the height of a column.
     */
    private int columnWidth, columnHeight;

    GridSizeAdaptor(ArrayList<Button> buttons, int columnWidth, int columnHeight, boolean isNumpad) {
        this.buttons = buttons;
        this.isNumpad = isNumpad;
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

        if (!isNumpad) {
            button.setLayoutParams(new AbsListView.LayoutParams(columnWidth, columnHeight));
        }

        return button;
    }
}
