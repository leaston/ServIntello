package com.example.servintello;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TrainingActivity extends AppCompatActivity {

    // Gestion de l'animation des images
    private TextView gaTextView;
    private final Handler handler = new Handler(); // Handler : Gère l'exécution périodique du changement d'image
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
        String htmlText_ado = getString(R.string.informatique_pour_adolescents);
        String htmlText_pro = getString(R.string.informatique_pour_adultes_et_professionnels);
        String htmlText_flc = getString(R.string.faso_learning_code);

        ipeTextView.setText(Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY));
        ipadoTextView.setText(Html.fromHtml(htmlText_ado, Html.FROM_HTML_MODE_LEGACY));
        ipaduproTextView.setText(Html.fromHtml(htmlText_pro, Html.FROM_HTML_MODE_LEGACY));
        flc.setText(Html.fromHtml(htmlText_flc, Html.FROM_HTML_MODE_LEGACY));

        /*******************************************************************
         * GESTION DE L'INSERTION DE L'ICONE
         *******************************************************************/

        //Récupère le texte depuis le fichier XML : string.xml
        String text = getString(R.string.faso_learning_code);
        String text_ipe = getString(R.string.informatique_pour_enfants);

        // Interpréter les balises HTML
        Spanned html_Text = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY);
        Spanned html_Text_ipe = Html.fromHtml(text_ipe, Html.FROM_HTML_MODE_LEGACY);

        // Créer un SpannableStringBuilder pour manipuler le texte et ajouter l'icône
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(html_Text);

        // Charger l'icône
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_icon_flc);

        if (drawable != null) {
            //setBounds(left, top, right, bottom)
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

            // Créer un ImageSpan avec l'icône
            ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);

            // Placer l'icône à l'endroit souhaité (ici, à la fin du texte)
            spannableStringBuilder.append(" ");
            spannableStringBuilder.setSpan(imageSpan, spannableStringBuilder.length() - 1, spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            // Appliquer le SpannableStringBuilder au TextView
            flc.setText(spannableStringBuilder);
        }

        // Créer un SpannableStringBuilder pour le texte ipe
        SpannableStringBuilder spannableStringBuilderIpe = new SpannableStringBuilder(html_Text_ipe);

        // Définir les partie du texte que l'on veut rendre cliquable
        int start = text_ipe.indexOf("Cliquez ici");
        int end = start + "Cliquez ici".length();

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                // Action à effectuer
                Intent details_ga = new Intent(TrainingActivity.this, GaActivity.class);
                startActivity(details_ga);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);

            }
        };

        spannableStringBuilderIpe.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Appliquer le SpannableString au TextView
        ipeTextView.setText(spannableStringBuilderIpe);
        ipeTextView.setMovementMethod(LinkMovementMethod.getInstance());


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void startImageSwitcher() {
        Runnable runnable = new Runnable() { // Runnable : Code exécuté par le Handler pour changer l'image
            @Override
            public void run() {
                // Switch the background image
                gaTextView.setBackgroundResource(images[currentImageIndex]);
                currentImageIndex = (currentImageIndex + 1) % images.length; // % images.length : Garantit que l'index est cyclique, revenant à 0 après la dernière image
                // postDelayed : Replanifie le Runnable pour qu'il s'exécute toutes les 5 secondes
                handler.postDelayed(this, 5000); // 5 seconds
            }
        };
        handler.post(runnable);
    }
}
