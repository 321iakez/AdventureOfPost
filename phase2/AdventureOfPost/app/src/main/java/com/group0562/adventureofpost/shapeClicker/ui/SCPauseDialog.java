package com.group0562.adventureofpost.shapeClicker.ui;

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


public class SCPauseDialog extends AppCompatDialogFragment {

    private final String RETURN_NO_SAVE = "RETURN_NO_SAVE";
    private final String RETURN_SAVE = "RETURN_SAVE";
    private final String RESUME = "RESUME";

    private SCPauseDialog.SCPauseDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sc_pause, null);

        builder.setView(view)
                .setTitle("")
                .setNegativeButton("Exit without Save", (dialog, which) -> listener.saveGame(RETURN_NO_SAVE))
                .setNeutralButton("Exit with Save", (dialog, which) -> listener.saveGame(RETURN_SAVE))
                .setPositiveButton("Resume game", (dialog, which) -> listener.saveGame(RESUME));
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (SCPauseDialog.SCPauseDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Must implement SCPauseDialogListener");
        }
    }

    interface SCPauseDialogListener {
        void saveGame(String mode);
    }
}
