package com.example.pwarren.biblegame;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Button;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String textChunkName = "John 3:16";
        String textChunk = "For God So";

        final Button button1 = findViewById(R.id.button1);
        final Button button2 = findViewById(R.id.button2);
        final Button button3 = findViewById(R.id.button3);
        final Button button4 = findViewById(R.id.button4);
        final Button button5 = findViewById(R.id.button5);
        final Button button6 = findViewById(R.id.button6);


        ProgressBar prog = findViewById(R.id.progressBar);
        prog.setMax(100);

        button1.setText("For");
        button2.setText("x");
        button3.setText("x");
        button4.setText("God");
        button5.setText("x");
        button6.setText("So");



        TextView txtTextName = findViewById(R.id.chunkTextName);

        txtTextName.setText(textChunkName);

    }

}
