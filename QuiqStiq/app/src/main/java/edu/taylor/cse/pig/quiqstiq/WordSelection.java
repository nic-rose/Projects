package edu.taylor.cse.pig.quiqstiq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import android.view.View;
import java.util.ArrayList;

public class WordSelection extends AppCompatActivity {
    //Global Variables
    private static final String TAG = "WordSelection";
    private static int removedIdxCount = 0;
    private static String blankText = "";
    private static int wrongCount = 0;
    /* Variables for getting package data */
    private int myPackageIndex;
    private int myLineIndex;

    protected static void setTitle(TextView title, String textTitle){
        title.setText(textTitle);
    }

    protected static void setTextView(TextView textBox, String blankText){
        textBox.setText(blankText);
    }

    protected static int getNumWordsRemoved(int difficulty, String[] deconstruct){
        //int numWordsRemoved = 1;
        if (difficulty == 0){
            difficulty = 10;
        }
        double temp = (difficulty * .01);
        int numWordsRemoved = (int)Math.ceil((temp) * deconstruct.length);

        if (numWordsRemoved >= deconstruct.length){
            numWordsRemoved = deconstruct.length;
        }
        return numWordsRemoved;
    }
    protected static void setRemovedIndexes(int numWordsRemoved, String[] deconstruct, int[] indexesRemoved){
        int[] shuffleArray = new int [deconstruct.length];
        for(int i = 0; i < deconstruct.length; i++){
            shuffleArray[i] = i;
        }
        int tempInt;

        for (int i = 0; i < shuffleArray.length; i++) {
            Random rand = new Random();
            int randNum = rand.nextInt(shuffleArray.length);

            tempInt = shuffleArray[i];
            shuffleArray[i] = shuffleArray[randNum];
            shuffleArray[randNum] = tempInt;
        }

        for(int i = 0; i < numWordsRemoved; i++){
            indexesRemoved[i] = shuffleArray[i];
        }

        Arrays.sort(indexesRemoved);
    }
    protected static void setBlanks(int[] indexesRemoved, String[] deconstruct){
        for (int i = 0; i < indexesRemoved.length; i++) {
            int length = deconstruct[indexesRemoved[i]].length();
            String underscore = "";
            // builds a string of _ so it can replace the word in the text
            for (int j = 0; j < length; j++) {
                underscore += "_";
            }
            deconstruct[indexesRemoved[i]] = underscore;
        }
    }
    protected static String joinArray(String[] deconstruct){
        String joinedArray = "";
        for(int i = 0; i < deconstruct.length; i++){
            joinedArray = joinedArray + deconstruct[i] + " ";
        }
        return joinedArray;
    }
    protected void checkButton (final Button option, final Button option2, final ProgressBar progressBar, final TextView textBox, final int[] indexesRemoved, final String[] goalArray){
        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String wrong = "X";
                if(option.getText().equals(goalArray[indexesRemoved[removedIdxCount]])){
                    progressBar.incrementProgressBy((100)/(indexesRemoved.length));
                    updateTextBox(indexesRemoved, goalArray, textBox);
                    removedIdxCount++;

                    if (removedIdxCount == indexesRemoved.length) {
                        double grade = (double)wrongCount / (double)indexesRemoved.length ;

                        if(grade > .5){
                            grade = ((grade * 2) * 10);
                            grade = -grade;
                            if (grade < -10){
                                grade = -10;
                            }
                        }
                        else{
                            grade = grade +.5;
                            grade = ((grade * 2) * 10);
                            if (grade > 10){
                                grade = 10;
                            }
                        }
                        returnToHomePage((int)grade);
                    }
                    else {
                        setButtonText(option, option2, goalArray, indexesRemoved);
                    }
                }
                else{
                    option.setText(wrong);
                    wrongCount++;
                    //option.setBackgroundColor(0x0000ff);
                }


            }
        });
    }
    protected void setButtonText (final Button option1, final Button option2, final String[] goalArray, final int[] indexesRemoved){
        Random rand = new Random();
        int correctOption = rand.nextInt(2);

        String correctWord = goalArray[indexesRemoved[removedIdxCount]];
        String wrongWord = correctWord;
        while (correctWord.equals(wrongWord)){
            wrongWord = goalArray[rand.nextInt(goalArray.length)];
        }
        if (correctOption == 0) {
            option1.setText(correctWord);
            option2.setText(wrongWord);

        } else {
            option1.setText(wrongWord);
            option2.setText(correctWord);
        }

    }
    //function is void for now but may need to return something later.
    protected static void updateTextBox (int[] indexesRemoved, String[] goalArray, TextView textBox){
        String[] workingArray = blankText.split(" ");
        workingArray[indexesRemoved[removedIdxCount]] = goalArray[indexesRemoved[removedIdxCount]];
        blankText = joinArray(workingArray);
        setTextView(textBox, blankText);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_selection);

        PackageCollection packageCollection = (PackageCollection) getApplicationContext(); /* Required to get data from singleton application */
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        myPackageIndex = (int) bundle.getSerializable("packageIndex");
        myLineIndex = (int) bundle.getSerializable("lineIndex");
        SingleLine selectedLine = packageCollection.getLine(myPackageIndex, myLineIndex);

        ProgressBar progressBar = findViewById(R.id.progressBar);
        final Button option1 = findViewById(R.id.option1);
        final Button option2 = findViewById(R.id.option2);
        final TextView textBox = findViewById(R.id.textBox);
        TextView title = findViewById(R.id.title);

        String textTitle = selectedLine.getName();
        String words = selectedLine.getContent();
        int difficulty = selectedLine.getProficiency();

        String[] goalArray = words.split("[ .,?!;:\"]+");
        String[] deconstruct = words.split("[ .,?!;:\"]+");
        int numWordsRemoved = getNumWordsRemoved(difficulty, deconstruct);
        int[] indexesRemoved = new int[numWordsRemoved];
        removedIdxCount = 0;
        wrongCount = 0;

        setTitle(title, textTitle);
        setRemovedIndexes(numWordsRemoved, deconstruct, indexesRemoved);
        setBlanks(indexesRemoved, deconstruct);
        String joinedArray = joinArray(deconstruct);
        blankText = joinedArray;
        setTextView(textBox, joinedArray);
        setButtonText(option1, option2, goalArray, indexesRemoved);

        checkButton(option1, option2, progressBar, textBox, indexesRemoved, goalArray);
        checkButton(option2, option1, progressBar, textBox, indexesRemoved, goalArray);

    }
    protected void returnToHomePage(int change){
        PackageCollection packageCollection = (PackageCollection) getApplicationContext();
        packageCollection.incrementProficiency(myPackageIndex,myLineIndex, change);
        packageCollection.setSavedProficiency(myPackageIndex, myLineIndex);
        Intent home = new Intent(this, Homepage.class);
        startActivity(home);
    }
}



