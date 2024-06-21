package com.example.servintello;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PaymentFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment, container, false);
        Button orangeMoneyButton = view.findViewById(R.id.orange_money);
        Button moovMoneyButton = view.findViewById(R.id.moov_money);

        orangeMoneyButton.setOnClickListener(v -> {
            MobilePaymentActivity activity = (MobilePaymentActivity) getActivity();
            if (activity != null) {
                String phoneNumber = activity.getPhoneNumber();
                // String pinCode = activity.getPinCode();
                if (!phoneNumber.isEmpty()) {
                    activity.initiateUssdCode(phoneNumber, "Orange");
                } else {
                    Toast.makeText(activity, "Entrer votre numéro de téléphone", Toast.LENGTH_SHORT).show();
                }
            }
        });

        moovMoneyButton.setOnClickListener(vm -> {
            MobilePaymentActivity activitymoov = (MobilePaymentActivity) getActivity();
            if (activitymoov != null) {
                String phoneNumber = activitymoov.getPhoneNumber();
                // String pinCode = activity.getPinCode();
                if (!phoneNumber.isEmpty()) {
                    activitymoov.initiateUssdCode(phoneNumber, "Moov");
                } else {
                    Toast.makeText(activitymoov, "Entrer votre numéro de téléphone", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
