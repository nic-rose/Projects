package edu.taylor.cse.pig.quiqstiq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class GameSelection extends AppCompatActivity {

    private static final String TAG = "GameSelection";
    private int myPackageIndex;
    private int myLineIndex;

    private Button wordSelectionButton;
    private Button letterSelectionButton;
    private Button lilyPadButton;
    private Button chooseForMeButton;
    private TextView title;
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_selection);

        /* Get the chosen package from the previous segment */
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        myPackageIndex = (int) bundle.getSerializable("packageIndex");
        myLineIndex = (int) bundle.getSerializable("lineIndex");
        PackageCollection packageCollection = (PackageCollection) getApplicationContext(); /* Required to get data from singleton application */
        /* Demo proficiency incrementing */
        title = findViewById(R.id.title);
        content = findViewById(R.id.content);

        title.setText(packageCollection.getLineName(myPackageIndex,myLineIndex));
        content.setText(packageCollection.getLineContent(myPackageIndex,myLineIndex));

        Log.d(TAG, "onCreate: Unpacked bundle");

        wordSelectionButton = findViewById(R.id.buttonGame0);
        wordSelectionButton.setText("Word Selection");
        wordSelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), WordSelection.class);
                openSelectedGame(myPackageIndex, myLineIndex, intent);
            }
        });

        letterSelectionButton = findViewById(R.id.buttonGame1);
        letterSelectionButton.setText("Letter Selection");
        letterSelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LetterSelection.class);
                openSelectedGame(myPackageIndex, myLineIndex, intent);
            }
        });

        lilyPadButton = findViewById(R.id.buttonGame2);
        lilyPadButton.setText("Lily Pad");
        lilyPadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LilyPads.class);
                openSelectedGame(myPackageIndex, myLineIndex, intent);
            }
        });

        chooseForMeButton = findViewById(R.id.buttonGame3);
        chooseForMeButton.setText("Random");
        chooseForMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                Random rand = new Random();
                int randNum = rand.nextInt(3);
                switch (randNum) {
                    case 0:
                        intent = new Intent(v.getContext(), WordSelection.class);
                        break;
                    case 1:
                        intent = new Intent(v.getContext(), LetterSelection.class);
                        break;
                    default:
                        intent = new Intent(v.getContext(), LilyPads.class);
                        break;
                }
                openSelectedGame(myPackageIndex, myLineIndex, intent);
            }
        });
    }

    public void openSelectedGame(int packageIndex, int lineIndex, Intent intent){
        Bundle bundle = new Bundle();
        bundle.putSerializable("packageIndex", packageIndex);
        bundle.putSerializable("lineIndex", lineIndex);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
