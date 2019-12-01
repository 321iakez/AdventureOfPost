package com.group0562.adventureofpost.sudoku.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.group0562.adventureofpost.R;


/**
 * Dialog that pops up when user finish the Sudoku puzzle.
 */
public class SudokuEndDialog extends AppCompatDialogFragment {

    private SudokuEndDialog.EndDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sudoku_pause, null);

        builder.setView(view)
                .setTitle("")
                .setNegativeButton("Exit without save stats", (dialog, which) -> listener.endGame(Modes.EXIT_NO_STAT))
                .setPositiveButton("Exit with save stats", (dialog, which) -> listener.endGame(Modes.EXIT_STAT));
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (SudokuEndDialog.EndDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Must implement PauseDialogListener");
        }
    }

    interface EndDialogListener {
        void endGame(Modes mode);
    }

    enum Modes {
        EXIT_NO_STAT, EXIT_STAT
    }
}
