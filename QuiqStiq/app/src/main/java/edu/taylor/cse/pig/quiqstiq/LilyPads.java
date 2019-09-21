package edu.taylor.cse.pig.quiqstiq;


import android.graphics.Color;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import java.util.Random;
import java.util.LinkedList;


public class LilyPads extends AppCompatActivity {

    private static final String TAG = "Lillipads";

    protected String textChunk = "";
    protected String textName = "";
    protected int Proficiency = 2;



    protected Random rand = new Random();

    protected int currentBlankIdx = 0;
    protected int presses = 0;
    protected int numOfPresses = 3;
    protected int numberOfWordsDisplayed = 3;
    protected boolean endOfVerse = false;
    protected boolean done = false;
    protected int wrongGuesses = 0;
    /* Variables for getting package data */
    private int myPackageIndex;
    private int myLineIndex;



    int currentPosition;
    int stringLength;
    ProgressBar currentProgBar;
    String[] arrayOfWords;
    //String[] parsedString;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;

    TextView recentWords;
    String lastWords;

    // String lastWords;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lily_pads);

        initialize();
    }

    public void initialize(){
        PackageCollection packageCollection = (PackageCollection) getApplicationContext(); /* Required to get data from singleton application */


        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        myPackageIndex = (int)bundle.getSerializable("packageIndex");
        myLineIndex = (int)bundle.getSerializable("lineIndex");

        SingleLine selectedLine = packageCollection.getLine(myPackageIndex, myLineIndex);
        textName = selectedLine.getName();
        textChunk = selectedLine.getContent();
       // textChunk = "jesus wept yo";
        LinkedList<String> llWords = parseString(textChunk);
        arrayOfWords = new String[llWords.size()];
        arrayOfWords = llWords.toArray(arrayOfWords);
        stringLength = arrayOfWords.length;
        currentPosition = 1;
        TextView chunkTextName = findViewById(R.id.chunkTextName);
        chunkTextName.setText(textName);



        this.currentProgBar = findViewById(R.id.progressBar);
        currentProgBar.setMax(stringLength);



        recentWords = findViewById(R.id.LastThreeWords);



        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);


        List<Button> btnList = new ArrayList<>();
        btnList.add(button1);
        btnList.add(button2);
        btnList.add(button3);
        btnList.add(button4);
        btnList.add(button5);
        btnList.add(button6);
        setButtonFunction(button1, currentProgBar, recentWords, btnList);
        setButtonFunction(button2, currentProgBar, recentWords, btnList);
        setButtonFunction(button3, currentProgBar, recentWords, btnList);
        setButtonFunction(button4, currentProgBar, recentWords, btnList);
        setButtonFunction(button5, currentProgBar, recentWords, btnList);
        setButtonFunction(button6, currentProgBar, recentWords, btnList);


        setAllButtonText(btnList);
    }

    private static String delimiters = "[ .,?!;:\"]+";

    private static LinkedList<String> parseString(String input){
        LinkedList<String> parsedString = new LinkedList<String>();
        for (String currentWord: input.split(delimiters)){
            parsedString.add(currentWord);
        }
        return parsedString;
    }




    protected String getRecentWords()
        {
            String recentWords = "";
            for(int i = 0; i < numberOfWordsDisplayed; i++){
                recentWords += arrayOfWords[currentPosition - 1 - 3 + i] + " ";
            }
            return recentWords;
        }

    protected boolean correctRow(Button button){
        boolean correctRow = false;
        switch(button.getId()) {
            case R.id.button1:
            case R.id.button2:
                if (presses == 0) {
                    correctRow = true;
                }
                break;
            case R.id.button3:
            case R.id.button4:
                if (presses == 1) {
                    correctRow = true;
                }
                break;
            case R.id.button5:
            case R.id.button6:
                if (presses == 2) {
                    correctRow = true;
                }
                break;
        }
        return correctRow;

    }

    protected void setButtonFunction(final Button button, final ProgressBar progBar, final TextView txtTextChunk, final List<Button> btnList){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!done) {
                    if (correctRow(button)) {
                        if (button.getText().equals(arrayOfWords[currentBlankIdx])) {
                            progBar.incrementProgressBy(1);
                            currentBlankIdx++;
                            presses++;
                            button.setBackgroundColor(Color.GREEN);
                            // button.setText("Correct!!!");
                            if (currentBlankIdx <= stringLength - 1 && presses >= numOfPresses) {
                                presses = 0;
                                recentWords.setText(getRecentWords());
                                setAllButtonText(btnList);
                            }
                            if (presses >= numOfPresses && endOfVerse) {
                                endGame();
                                done = true;
                            }
                        } else {
                            button.setBackgroundColor(Color.RED);
                            wrongGuesses++;
                        }
                    }
                }
            }
        });
    }

    protected int calculateProficiency(){
        double ratio;
        int normalizedX;
        if(wrongGuesses >= stringLength){
            ratio = 0;
        }
        else if(wrongGuesses == 0){
            ratio = 1;
        }
        else{
            ratio = ((double)(stringLength - wrongGuesses) / (double)stringLength);
        }
        normalizedX = (int)(ratio * 10);

        return normalizedX;
    }

    protected void endGame(){
        PackageCollection packageCollection = (PackageCollection) getApplicationContext();
        int proficiencyChange = calculateProficiency();
        packageCollection.incrementProficiency(myPackageIndex,myLineIndex,proficiencyChange);
        packageCollection.setSavedProficiency(myPackageIndex, myLineIndex);
        Intent newIntent =  new Intent(this, Homepage.class);
        startActivity(newIntent);
    }


    protected void setAllButtonText(List<Button> btnList) {
        int setsOfButtons = btnList.size() / 2;
        List<String> stringList = new ArrayList<>();
        int correctOption;

        if (stringLength <= setsOfButtons) {

            for(int i = 0; i < setsOfButtons; i++){
                correctOption = rand.nextInt(2);

                if (currentPosition <= stringLength) {
                    String correctWord = arrayOfWords[currentPosition - 1];
                    String wrongWord = arrayOfWords[rand.nextInt(stringLength - 1)];

                    while (correctWord.equals(wrongWord) || stringList.contains(wrongWord)) {
                        wrongWord = arrayOfWords[rand.nextInt(stringLength )];
                    }

                    if (correctOption == 0) {
                        if (i == 0) {
                            btnList.get(i).setText(correctWord);
                            btnList.get(i + 1).setText(wrongWord);
                        } else {
                            btnList.get((2 * i)).setText(correctWord);
                            btnList.get((2 * i) + 1).setText(wrongWord);
                        }
                    } else {
                        if (i == 0) {
                            btnList.get(i).setText(wrongWord);
                            btnList.get(i + 1).setText(correctWord);
                        } else {
                            btnList.get((2 * i)).setText(wrongWord);
                            btnList.get((2 * i) + 1).setText(correctWord);
                        }
                    }
                    if (currentPosition >= stringLength) {
                        numOfPresses = i + 1;
                        endOfVerse = true;
                    }
                    //currentPosition++;
                } else {
                    if (i == 0) {
                        btnList.get(i).setText("");
                        btnList.get(i + 1).setText("");
                        btnList.get(i).setVisibility(View.INVISIBLE);
                        btnList.get(i + 1).setVisibility(View.INVISIBLE);
                    } else {
                        btnList.get(2 * i).setText("");
                        btnList.get((2 * i) + 1).setText("");
                        btnList.get(2 * i).setVisibility(View.INVISIBLE);
                        btnList.get((2 * i) + 1).setVisibility(View.INVISIBLE);
                    }
                    if (!endOfVerse) {
                        numOfPresses = i + 1;
                        endOfVerse = true;
                    }
                }
                currentPosition++;
            }
        }
        else
        {
            for (int i = 0; i < setsOfButtons; i++) {
               if (currentPosition + i <= stringLength) {
                   stringList.add(arrayOfWords[currentPosition - 1 + i]);
              }
            }


            for (int i = 0; i < setsOfButtons; i++) {
                correctOption = rand.nextInt(2);

                if (i == 0) {
                    btnList.get(i).setBackgroundResource(android.R.drawable.btn_default);
                    btnList.get(i + 1).setBackgroundResource(android.R.drawable.btn_default);
                    btnList.get(i).setVisibility(View.VISIBLE);
                    btnList.get(i + 1).setVisibility(View.VISIBLE);
                } else {
                    btnList.get((2 * i)).setBackgroundResource(android.R.drawable.btn_default);
                    btnList.get((2 * i) + 1).setBackgroundResource(android.R.drawable.btn_default);
                    btnList.get(2 * i).setVisibility(View.VISIBLE);
                    btnList.get((2 * i) + 1).setVisibility(View.VISIBLE);
                }

                if (currentPosition <= stringLength) {
                    String correctWord = arrayOfWords[currentPosition - 1];
                    String wrongWord = arrayOfWords[rand.nextInt(stringLength - 1)];

                    while (correctWord.equals(wrongWord) || stringList.contains(wrongWord)) {
                        wrongWord = arrayOfWords[rand.nextInt(stringLength - 1)];
                    }

                    stringList.add(wrongWord);

                    if (correctOption == 0) {
                        if (i == 0) {
                            btnList.get(i).setText(correctWord);
                            btnList.get(i + 1).setText(wrongWord);
                        } else {
                            btnList.get((2 * i)).setText(correctWord);
                            btnList.get((2 * i) + 1).setText(wrongWord);
                        }
                    } else {
                        if (i == 0) {
                            btnList.get(i).setText(wrongWord);
                            btnList.get(i + 1).setText(correctWord);
                        } else {
                            btnList.get((2 * i)).setText(wrongWord);
                            btnList.get((2 * i) + 1).setText(correctWord);
                        }
                    }
                    if (currentPosition >= stringLength) {
                        numOfPresses = i + 1;
                        endOfVerse = true;
                    }
                    //currentPosition++;
                } else {
                    if (i == 0) {
                        btnList.get(i).setText("");
                        btnList.get(i + 1).setText("");
                        btnList.get(i).setVisibility(View.INVISIBLE);
                        btnList.get(i + 1).setVisibility(View.INVISIBLE);
                    } else {
                        btnList.get(2 * i).setText("");
                        btnList.get((2 * i) + 1).setText("");
                        btnList.get(2 * i).setVisibility(View.INVISIBLE);
                        btnList.get((2 * i) + 1).setVisibility(View.INVISIBLE);
                    }
                    if (!endOfVerse) {
                        numOfPresses = i + 1;
                        endOfVerse = true;
                    }
                }
                currentPosition++;
            }
        }
    }


}
