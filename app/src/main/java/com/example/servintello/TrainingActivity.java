package com.example.servintello;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TrainingActivity extends AppCompatActivity {

    private TextView gaTextView;
    private final Handler handler = new Handler();
    private int currentImageIndex = 0;
    private final int[] images = {R.drawable.gaxl150, R.drawable.ga2xl150, R.drawable.gapdxl150, R.drawable.gapd2xl150};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_training);

        Button retour1 = findViewById(R.id.retour1);
        TextView ipeTextView = findViewById(R.id.ipe);
        TextView ipadoTextView = findViewById(R.id.ipado);
        TextView ipaduproTextView = findViewById(R.id.ipadupro);
        TextView flcTextView = findViewById(R.id.flc);
        ImageView iconImageView = findViewById(R.id.icon);

        gaTextView = findViewById(R.id.ga);
        startImageSwitcher();

        retour1.setOnClickListener(v -> {
            Intent retour = new Intent(TrainingActivity.this, MainActivity.class);
            startActivity(retour);
        });

        // La Nomenclature des variables suivantes (htmlText, htmlTextAdo, htmlTextPro, htmlTextFlc)
        // permet d'éviter des longues lignes
        String htmlTextIpe = getString(R.string.informatique_pour_enfants);
        String htmlTextAdo = getString(R.string.informatique_pour_adolescents);
        String htmlTextPro = getString(R.string.informatique_pour_adultes_et_professionnels);
        String htmlTextFlc = getString(R.string.faso_learning_code);

        // Interpréter les balises HTML
        Spanned spannedTextIpe = Html.fromHtml(htmlTextIpe, Html.FROM_HTML_MODE_LEGACY);
        Spanned spannedTextAdo = Html.fromHtml(htmlTextAdo, Html.FROM_HTML_MODE_LEGACY);
        Spanned spannedTextPro = Html.fromHtml(htmlTextPro, Html.FROM_HTML_MODE_LEGACY);
        Spanned spannedTextFlc = Html.fromHtml(htmlTextFlc, Html.FROM_HTML_MODE_LEGACY);

        // Définir la partie du texte que l'on souhaite rendre cliquable
        String clickableText = "Cliquez ici";
        int start = spannedTextIpe.toString().indexOf(clickableText);
        // La ligne ci-dessous est à revoir plus tard
        int end = start + clickableText.length();

        SpannableString spannableString = new SpannableString(spannedTextIpe);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                // Action à effectuer
                Intent ga_details = new Intent(TrainingActivity.this, GaActivity.class);
                startActivity(ga_details);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds){
                super.updateDrawState(ds);
                ds.setUnderlineText(false); // Pour désactiver le soulignement du texte de lien
            }
        };

        spannableString.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Appliquer le spannableString au TextView ipe
        ipeTextView.setText(spannableString);
        ipeTextView.setMovementMethod(LinkMovementMethod.getInstance());

        // Appliquer les textes interprétés avec HTML aux autres TextViews
        ipeTextView.setText(spannedTextIpe);
        ipadoTextView.setText(spannedTextAdo);
        ipaduproTextView.setText(spannedTextPro);
        flcTextView.setText(spannedTextFlc);

        // Ajouter une icône au TextView flc
        //Cette méthode ajoute une icône à un TextView à la position spécifiée (haut à droite ou
        // bas à droite). Elle utilise SpannableStringBuilder pour créer le texte final avec l'icône.
        addIconToTextView(flcTextView, spannedTextFlc, iconImageView);

        // Le paramètre ViewCompat.setOnApplyWindowInsetsListener est formaté pour être plus
        // lisible et ne pas dépasser les 120 caractères par ligne.
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
                handler.postDelayed(this, 5000); // 5 seconds
            }
        };
        handler.post(runnable);
    }
    private void addIconToTextView(TextView textView, Spanned text, ImageView iconImageView) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(text);
        textView.setText(spannableStringBuilder);

        // Positionner l'icône en haut à droite
        iconImageView.setVisibility(View.VISIBLE);
    }

}
