package com.mg.hotelmanagementsystem.dialogs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mg.hotelmanagementsystem.R;
import com.mg.hotelmanagementsystem.database.FirebaseTransaction;
import com.mg.hotelmanagementsystem.models.Table;

/**
 * Created by moses on 7/12/18.
 */

public class AddTableDialog extends BaseDialog {

    private TextInputLayout tableNameEditText;
    private Button cancelButton;
    private Button submitButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_add_table, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tableNameEditText = view.findViewById(R.id.tableNameEditText);
        cancelButton = view.findViewById(R.id.cancelButton);
        submitButton = view.findViewById(R.id.saveButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitTable();
            }
        });
    }

    public boolean validate() {
        if (tableNameEditText.getEditText().getText().toString().isEmpty()) {
            tableNameEditText.setError("Table Name cannot be empty");
            return false;
        }
        return true;
    }

    public void submitTable(){
        if(validate()){
            getDialog().dismiss();

            new FirebaseTransaction(getContext())
                    .child("tables")
                    .push()
                    .setValue(new Table(tableNameEditText.getEditText().getText().toString()));
        }
    }
}

