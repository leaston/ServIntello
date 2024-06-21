package com.example.servintello;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log; // Importez la classe Log
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
    private static final String TAG = "MobilePaymentActivity"; // Définissez un tag pour le logging

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

    public void initiateUssdCode(String phoneNumber, String val) {
        String ussdCodeOrange = "*144*2*1*74838314*25000*";
        String ussdCodeMoov = "*555*2*1*63523183*25000*";

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE_PERMISSION);
        } else {
            switch (val) {
                case "Orange":
                    sendUssdCode(ussdCodeOrange, phoneNumber);
                    break;
                case "Moov":
                    sendUssdCode(ussdCodeMoov, phoneNumber);
                    break;
                default:
                    System.out.println("Faites un choix valide de l'opérateur téléphonique");
            }
        }
    }

    private void sendUssdCode(String ussdCode, String phoneNumber) {
        try {
            String ussd = ussdCode + Uri.encode("#");
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + ussd));
            startActivity(intent);
            Toast.makeText(this, "Le numéro " + phoneNumber + " vient d'envoyer la somme de 25000 FCFA au numéro 74838314", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Log.e(TAG, "Échec de l'envoi du code USSD. Veuillez réessayer.", e); // Utilisez Log.e pour l'erreur
            Toast.makeText(this, "Échec de l'envoi du code USSD. Veuillez réessayer.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL_PHONE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                String phoneNumber = getPhoneNumber();
                // Vous devez savoir quelle valeur était passée à initiateUssdCode
                // Vous pouvez la stocker comme variable membre si nécessaire.
                String val = "Orange"; // Par exemple, utilisez la valeur appropriée
                initiateUssdCode(phoneNumber, val);
            } else {
                Toast.makeText(this, "Permission refusée", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
