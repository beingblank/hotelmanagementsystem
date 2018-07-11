package com.mg.hotelmanagementsystem.dialogs;

import android.support.v4.app.DialogFragment;
import android.view.ViewGroup;

/**
 * Created by moses on 7/11/18.
 */

public class BaseDialog extends DialogFragment {
    @Override
    public void onStart() {
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        super.onStart();
    }
}
