package jianxuan.li.jllibrary;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class InvalidDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Username or password is incorrect")
                .setTitle("Invalid Login")
                .setPositiveButton("OK", (dialog, which) -> {
                    // do nothing
                });
        return builder.create();
    }
}
