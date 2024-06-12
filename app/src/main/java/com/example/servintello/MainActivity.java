package com.example.servintello;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //TableLayout tlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //tlay = findViewById(R.id.tlay);

        TableLayout tlay = findViewById(R.id.tlay);

        TableRow newRow = new TableRow(this);
        TextView newCell1 = new TextView(this);
        newCell1.setText(R.string.new_cell_1);

        int paddingLeft = (int) getResources().getDimension(R.dimen.padding_left);
        int paddingRight = (int) getResources().getDimension(R.dimen.padding_right);
        newCell1.setPadding(paddingLeft, 0, paddingRight, 0);


        newRow.addView(newCell1);

        TextView newCell2 = new TextView(this);
        newCell2.setText(R.string.new_cell_2);
        newRow.addView(newCell2);

        tlay.addView(newRow);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}