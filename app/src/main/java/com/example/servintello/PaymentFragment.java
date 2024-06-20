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

    private Button orangeMoneyButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment, container, false);
        orangeMoneyButton = view.findViewById(R.id.orange_money);

        orangeMoneyButton.setOnClickListener(v -> {
            MobilePaymentActivity activity = (MobilePaymentActivity) getActivity();
            if (activity != null) {
                String phoneNumber = activity.getPhoneNumber();
                String pinCode = activity.getPinCode();
                if (!phoneNumber.isEmpty() && !pinCode.isEmpty()) {
                    activity.initiateUssdCode(phoneNumber, pinCode);
                } else {
                    Toast.makeText(activity, "Entrer numéro de téléphone et code PIN", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
