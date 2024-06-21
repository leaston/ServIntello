package com.example.servintello;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FormFragment extends Fragment {

    private EditText telephoneEditText;
    //private EditText pinEditText;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form, container, false);
        telephoneEditText = view.findViewById(R.id.telephone);
        //pinEditText = view.findViewById(R.id.pin);

        return view;
    }

    public String getPhoneNumber() {
        if (telephoneEditText != null) {
            return telephoneEditText.getText().toString();
        }
        return "";
    }

    /*public String getPinCode() {
        if (pinEditText != null) {
            return pinEditText.getText().toString();
        }
        return "";
    }*/

}
