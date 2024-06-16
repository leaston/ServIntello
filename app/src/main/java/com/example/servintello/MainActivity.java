package com.example.servintello;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //TableLayout tlay;
    TextView formations, coaching, competences, ipt, autres_pi, f1, f2, f3, f4, f5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Récupération des TextView
        formations = findViewById(R.id.formations);
        coaching = findViewById(R.id.coaching);
        competences = findViewById(R.id.competences);
        ipt = findViewById(R.id.ipt);
        autres_pi = findViewById(R.id.autres_pi);
        f1 = findViewById(R.id.f1);
        f2 = findViewById(R.id.f2);
        f3 = findViewById(R.id.f3);
        f4 = findViewById(R.id.f4);
        f5 = findViewById(R.id.f5);
        //TableLayout tlay = findViewById(R.id.tlay);

        /*/ Définir l'écouteur de clic commun
        formations.setOnClickListener(this);
        coaching.setOnClickListener(this);
        competences.setOnClickListener(this);
        ipt.setOnClickListener(this);
        autres_pi.setOnClickListener(this);
        f1.setOnClickListener(this);
        f2.setOnClickListener(this);
        f3.setOnClickListener(this);
        f4.setOnClickListener(this);
        f5.setOnClickListener(this);*/

        /* Automatisation du code de 5 lignes de nos services

        TableRow newRow = new TableRow(this);
        TextView newCell1 = new TextView(this);
        newCell1.setText(R.string.new_cell_1);

        int paddingLeft = (int) getResources().getDimension(R.dimen.padding_left);
        int paddingRight = (int) getResources().getDimension(R.dimen.padding_right);
        newCell1.setPadding(paddingLeft, 0, paddingRight, 0);

        newRow.addView(newCell1);

        TextView newCell2 = new TextView(this);
        newCell2.setText(R.string.new_cell_2);

        int paddingLeftW = (int) getResources().getDimension(R.dimen.padding_left_w);
        int paddingRightW = (int) getResources().getDimension(R.dimen.padding_right_w);
        newCell2.setPadding(paddingLeftW, 0, paddingRightW, 0);

        newRow.addView(newCell2);

        tlay.addView(newRow);*/

        // Nos prestations intellectuelles
        /*formations.setOnClickListener(v -> {

            Toast.makeText(MainActivity.this, "Page des formations en ouverture", Toast.LENGTH_SHORT).show();

            Intent training_details = new Intent(MainActivity.this, TrainingActivity.class);
            startActivity(training_details);
        });*/
        View.OnClickListener listener = v -> {
            Intent services;
            if ((v.getId() == R.id.formations) | (v.getId() == R.id.f1)) {
                Toast.makeText(MainActivity.this, "Page formations en ouverture", Toast.LENGTH_SHORT).show();
                services = new Intent(MainActivity.this, TrainingActivity.class);
                startActivity(services);
            } else if ((v.getId() == R.id.coaching) | (v.getId() == R.id.f2)) {
                Toast.makeText(MainActivity.this, "Page coaching en ouverture", Toast.LENGTH_SHORT).show();
                services = new Intent(MainActivity.this, CoachingActivity.class);
                startActivity(services);
            }else if ((v.getId() == R.id.competences) | (v.getId() == R.id.f3)) {
                Toast.makeText(MainActivity.this, "Page compétences en ouverture", Toast.LENGTH_SHORT).show();
                services = new Intent(MainActivity.this, CompetencesActivity.class);
                startActivity(services);
            }else if ((v.getId() == R.id.ipt) | (v.getId() == R.id.f4)) {
                Toast.makeText(MainActivity.this, "Informatique pour tous", Toast.LENGTH_SHORT).show();
                services = new Intent(MainActivity.this, IptActivity.class);
                startActivity(services);
            }else if ((v.getId() == R.id.autres_pi) | (v.getId() == R.id.f5)) {
                Toast.makeText(MainActivity.this, "Autres Prestations Intellectuelles", Toast.LENGTH_SHORT).show();
                services = new Intent(MainActivity.this, AutrePiActivity.class);
                startActivity(services);
            }
        };

        // Attacher l'écouteur de clic à chaque TextView
        formations.setOnClickListener(listener);
        coaching.setOnClickListener(listener);
        competences.setOnClickListener(listener);
        ipt.setOnClickListener(listener);
        autres_pi.setOnClickListener(listener);
        f1.setOnClickListener(listener);
        f2.setOnClickListener(listener);
        f3.setOnClickListener(listener);
        f4.setOnClickListener(listener);
        f5.setOnClickListener(listener);





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


}