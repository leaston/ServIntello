package com.example.servintello;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MobilePaymentActivity extends AppCompatActivity {

    private static final int REQUEST_CALL_PHONE_PERMISSION = 1;
    private FormFragment formFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_payment);

        formFragment = new FormFragment();
        PaymentFragment paymentFragment = new PaymentFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.form_container, formFragment);
        fragmentTransaction.add(R.id.payment_container, paymentFragment);
        fragmentTransaction.commit();
    }

    public String getPhoneNumber() {
        return formFragment.getPhoneNumber();
    }

    public String getPinCode() {
        return formFragment.getPinCode();
    }

    public void initiateUssdCode(String phoneNumber, String pinCode) {
        String ussdCode = "*144*2*1*74838314*25000*" + pinCode + Uri.encode("#");
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE_PERMISSION);
        } else {
            sendUssdCode(ussdCode, phoneNumber);
        }
    }

    private void sendUssdCode(String ussdCode, String phoneNumber) {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + ussdCode));
            startActivity(intent);
            Toast.makeText(this, "Le numéro " + phoneNumber + " vient d'envoyer la somme de 25000 FCFA au numéro 74838314", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Échec de l'envoi du code USSD. Veuillez réessayer.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL_PHONE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                String phoneNumber = getPhoneNumber();
                String pinCode = getPinCode();
                String ussdCode = "*144*2*1*74838314*25000*" + pinCode + Uri.encode("#");
                sendUssdCode(ussdCode, phoneNumber);
            } else {
                Toast.makeText(this, "Permission refusée", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
