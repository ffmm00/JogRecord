package com.fm.JogRecord;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class SaveConfirmDialogFragment extends DialogFragment {

    private static final String ARG_TITLE = "title";
    private static final String ARG_MESSAGE = "message";

    private int mTitle;
    private String mMessage;

    public static SaveConfirmDialogFragment newInstance(int title, String message) {
        SaveConfirmDialogFragment fragment = new SaveConfirmDialogFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TITLE, title);
        args.putString(ARG_MESSAGE, message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (getArguments() != null) {
            mTitle = getArguments().getInt(ARG_TITLE);
            mMessage = getArguments().getString(ARG_MESSAGE);
        }
        return new AlertDialog.Builder(getActivity()).setTitle(mTitle).setMessage(mMessage)
                .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        }
                ).setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        ((MapsActivity) getActivity()).saveJogViaCTP();
                    }
                }).create();

    }
    }
