package com.example.servintello;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TrainingActivity extends AppCompatActivity {

    private TextView gaTextView;
    private final Handler handler = new Handler();
    private int currentImageIndex = 0;
    private final int[] images = {R.drawable.gaxl150, R.drawable.ga2xl150};
    Button retour1;
    TextView ipeTextView, ipadoTextView, ipaduproTextView, flc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_training);

        retour1 = findViewById(R.id.retour1);
        ipeTextView = findViewById(R.id.ipe);
        ipadoTextView = findViewById(R.id.ipado);
        ipaduproTextView = findViewById(R.id.ipadupro);
        flc = findViewById(R.id.flc);

        gaTextView = findViewById(R.id.ga);
        startImageSwitcher();

        retour1.setOnClickListener(v -> {
            Intent retour = new Intent(TrainingActivity.this, MainActivity.class);
            startActivity(retour);
        });

        String htmlText = getString(R.string.informatique_pour_enfants);
        String htmlTextAdo = getString(R.string.informatique_pour_adolescents);
        String htmlTextPro = getString(R.string.informatique_pour_adultes_et_professionnels);
        String htmlTextFlc = getString(R.string.faso_learning_code);

        ipeTextView.setText(Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY));
        ipadoTextView.setText(Html.fromHtml(htmlTextAdo, Html.FROM_HTML_MODE_LEGACY));
        ipaduproTextView.setText(Html.fromHtml(htmlTextPro, Html.FROM_HTML_MODE_LEGACY));
        flc.setText(Html.fromHtml(htmlTextFlc, Html.FROM_HTML_MODE_LEGACY));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void startImageSwitcher() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // Switch the background image
                gaTextView.setBackgroundResource(images[currentImageIndex]);
                currentImageIndex = (currentImageIndex + 1) % images.length;
                handler.postDelayed(this, 5000); // 30 seconds
            }
        };
        handler.post(runnable);
    }
}
