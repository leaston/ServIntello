package com.example.servintello;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class GaActivity extends AppCompatActivity {

    private EditText nomPere, nomMere, telephone, email, nomEnfant, descriptionMaladie;
    private Spinner classeEnfant, ageEnfant;
    private RadioButton radioOui, radioNon;
    private Button bouton_envoyer;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ga);

        // Initialiser les vues
        nomPere = findViewById(R.id.nom_pere);
        nomMere = findViewById(R.id.nom_mere);
        telephone = findViewById(R.id.telephone);
        email = findViewById(R.id.email);
        nomEnfant = findViewById(R.id.nom_enfant);
        ageEnfant = findViewById(R.id.age_enfant);
        radioOui = findViewById(R.id.radio_oui);
        radioNon = findViewById(R.id.radio_non);
        classeEnfant = findViewById(R.id.classe_enfant);
        descriptionMaladie = findViewById(R.id.description_maladie);
        RadioGroup maladieRadioGroup = findViewById(R.id.maladie_radio_group);
        Button boutonEnvoyer = findViewById(R.id.bouton_envoyer);
        Button retour1 = findViewById(R.id.retour1);

        // Gérer la visibilité du champ de description de la maladie
        maladieRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radio_oui) {
                descriptionMaladie.setVisibility(View.VISIBLE);
            } else if (checkedId == R.id.radio_non) {
                descriptionMaladie.setVisibility(View.GONE);
            }
        });

        retour1.setOnClickListener(v -> {
            Intent retour = new Intent(GaActivity.this, TrainingActivity.class);
            startActivity(retour);
        });

        // Gérer le clic sur le bouton Envoyer
        boutonEnvoyer.setOnClickListener(v -> envoyerFormulaire());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void envoyerFormulaire() {
        // Récupérer les valeurs des champs
        String nomPereValue = nomPere.getText().toString();
        String nomMereValue = nomMere.getText().toString();
        String telephoneValue = telephone.getText().toString();
        String emailValue = email.getText().toString();
        String nomEnfantValue = nomEnfant.getText().toString();
        String ageEnfantValue = ageEnfant.getSelectedItem().toString();
        String classeEnfantValue = classeEnfant.getSelectedItem().toString();
        String maladieValue = radioOui.isChecked() ? "Oui" : "Non";
        String descriptionMaladieValue = descriptionMaladie.getText().toString();

        // Valider et envoyer les données
        if (nomPereValue.isEmpty() || nomMereValue.isEmpty() || telephoneValue.isEmpty() ||
                emailValue.isEmpty() || nomEnfantValue.isEmpty() || ageEnfantValue.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs obligatoires", Toast.LENGTH_LONG).show();
        } else {
            // Envoyer les données (à implémenter selon vos besoins, par exemple en utilisant une API)
            Toast.makeText(this, "Formulaire envoyé avec succès", Toast.LENGTH_LONG).show();
            Intent paiementOm = new Intent(GaActivity.this, OrangeActivity.class);
            startActivity(paiementOm);
        }
    }

    public RadioButton getRadioNon() {
        return radioNon;
    }

    public void setRadioNon(RadioButton radioNon) {
        this.radioNon = radioNon;
    }
}



