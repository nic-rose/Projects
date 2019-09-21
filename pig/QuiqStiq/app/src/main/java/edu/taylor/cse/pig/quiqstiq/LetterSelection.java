package edu.taylor.cse.pig.quiqstiq;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.util.Collections;
import java.util.Random;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class LetterSelection extends AppCompatActivity {
    private static final String TAG = "LetterSelection";

    /* Variables for getting package data */
    private int myPackageIndex;
    private int myLineIndex;

    protected String textChunk = "";
    protected int Proficiency;
    protected Random rand = new Random();
    protected List<String> allWords;

    protected List<Character> removedWordsChars = new ArrayList<>();
    protected List<Integer> removedWordsIdx = new ArrayList<>();
    protected int currentBlankIdx = 0;

    protected int wrongAnswers = 0;
    protected int rightAnswers = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_selection);
        PackageCollection packageCollection = (PackageCollection) getApplicationContext(); /* Required to get data from singleton application */

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        myPackageIndex = (int)bundle.getSerializable("packageIndex");
        myLineIndex = (int)bundle.getSerializable("lineIndex");
        SingleLine selectedLine = packageCollection.getLine(myPackageIndex, myLineIndex);

        String textName = selectedLine.getName();
        String textChunk = selectedLine.getContent();
        allWords = parseText(textChunk);
        TextView txtTextName = findViewById(R.id.chunkTextName);
        txtTextName.setText(textName);

        // Get the proficiency for this line
        Proficiency = packageCollection.getProficiency(myPackageIndex, myLineIndex);

        int removeCount = determineRemoveWordCount();

        TextView txtTextChunk = findViewById(R.id.textChunk);
        List<String> words = parseText(textChunk);
        removeWords(words, removeCount);
        String finalOutputText = listToString(words);
        txtTextChunk.setText(finalOutputText);

        ProgressBar progBar = findViewById(R.id.progressBar);
        progBar.setMax(removeCount);

        final Button button1 =  findViewById(R.id.btnOne);
        final Button button2 =  findViewById(R.id.btnTwo);
        final Button button3 =  findViewById(R.id.btnThree);
        final Button button4 =  findViewById(R.id.btnFour);

        List<Button> btnList =  new ArrayList<>();
        btnList.add(button1);
        btnList.add(button2);
        btnList.add(button3);
        btnList.add(button4);

        setButtonFunction(button1, progBar, txtTextChunk, btnList, removeCount);
        setButtonFunction(button2, progBar, txtTextChunk, btnList, removeCount);
        setButtonFunction(button3, progBar, txtTextChunk, btnList, removeCount);
        setButtonFunction(button4, progBar, txtTextChunk, btnList, removeCount);

        setAllButtonText(btnList);
    }

    protected void updateTextChunk(TextView txtTextChunk){
        txtTextChunk.setText(addRemovedWord(txtTextChunk.getText().toString()));
    }

    protected SpannableString addRemovedWord(String textChunk){
        List<String> currentTextChunk =  parseText(textChunk);
        int changeIdx;
        if (currentBlankIdx == 0)
            changeIdx = removedWordsIdx.get(currentBlankIdx);
        else
            changeIdx = removedWordsIdx.get(currentBlankIdx - 1);

        currentTextChunk.set(changeIdx, allWords.get(changeIdx));
        String newTextChunk = listToString(currentTextChunk);

        SpannableString textWithUnderlines =  new SpannableString(newTextChunk);
        for (int i = 0; i < currentBlankIdx; i++){
            List<Integer> startStopIdxs = findWordIndex(textChunk, removedWordsIdx.get(i));

            //Removed green coloring for correctly guess words, as it may be misconstrued.
            //textWithUnderlines.setSpan(new ForegroundColorSpan(Color.GREEN), startStopIdxs.get(0), startStopIdxs.get(1), 0);

            textWithUnderlines.setSpan(new UnderlineSpan(), startStopIdxs.get(0), startStopIdxs.get(1), 0);
        }

        return textWithUnderlines;
    }

    protected List<Integer> findWordIndex(String text, int idx){
        List<Integer> startStopIdxs = new ArrayList<>();
        int spaceCount = 0;
        boolean inWord = false;
        for (int i = 0; i < text.length(); i++){
            if (text.charAt(i) == ' '){
                if (idx == 0){
                    startStopIdxs.add(0);
                    startStopIdxs.add(i);
                    break;
                }
                if (inWord){
                    startStopIdxs.add(i);
                    break;
                }
                spaceCount++;
                if (spaceCount == idx){
                    inWord = true;
                    startStopIdxs.add(i + 1);
                }
            }
        }
        return startStopIdxs;
    }

    protected void setButtonFunction(final Button button, final ProgressBar progBar, final TextView txtTextChunk, final List<Button> btnList, final int removeCount){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentBlankIdx >= removeCount || currentBlankIdx >= removedWordsChars.size())
                    return;
                if (button.getText().toString().toUpperCase().equals(removedWordsChars.get(currentBlankIdx).toString().toUpperCase())) {
                    progBar.incrementProgressBy(1);
                    currentBlankIdx++;
                    rightAnswers++;
                    updateTextChunk(txtTextChunk);
                    if (currentBlankIdx >= removedWordsChars.size()){
                        endGame();
                        return;
                    }
                    if (currentBlankIdx < removeCount)
                        setAllButtonText(btnList);
                } else {
                    wrongAnswers++;
                    button.setText("Wrong");
                }
            }
        });
    }

    protected void setAllButtonText(List<Button> btnList){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        List<Character> charList =  new ArrayList<>();

        for (int i = 0; i < btnList.size(); i++){
            if (i == 0){
                charList.add(Character.toUpperCase(removedWordsChars.get(currentBlankIdx)));
            } else {
                int letterIdx = rand.nextInt(26);

                //Ensure that there aren't two buttons with the same letter
                while (charList.contains(alphabet.charAt(letterIdx))) {
                    letterIdx = rand.nextInt(26);
                }
                charList.add(alphabet.charAt(letterIdx));
            }
        }

        //Sort the letters, so that the buttons display the letters in alphabetical order, left -> right
        Collections.sort(charList);
        for (int i = 0; i < btnList.size(); i++){
            btnList.get(i).setText(Character.toString(charList.get(i)));
        }
    }

    protected String listToString(List<String> words){
        String textChunk = "";
        for (int i = 0; i < words.size(); i++){
            textChunk += words.get(i) + " ";
        }
        return textChunk;
    }

    protected void removeWords(List<String> textChunk, int removeCount){
        List<Integer> removedAlreadyIdx = new ArrayList<>();
        for (int i = 0; i < removeCount; i++){
            int removeIdx = rand.nextInt(textChunk.size());
            while (removedAlreadyIdx.contains(removeIdx)){
                removeIdx = rand.nextInt(textChunk.size());
            }
            removedAlreadyIdx.add(removeIdx);
            if (removedAlreadyIdx.size() == textChunk.size()){
                break;
            }
        }

        Collections.sort(removedAlreadyIdx);
        for (int i = 0; i < removedAlreadyIdx.size(); i++){
            int removeIdx = removedAlreadyIdx.get(i);
            removedWordsIdx.add(removeIdx);
            removedWordsChars.add(textChunk.get(removeIdx).charAt(0));
            textChunk.set(removeIdx, addUnderlines(textChunk.get(removeIdx).length()));
        }
    }

    protected String addUnderlines(int length){
        String underLine = "";
        for (int i = 0; i < length; i++){
            underLine += "_";
        }
        return underLine;
    }

    protected List<String> parseText(String textChunk){
        List<String> words = new ArrayList<>();
        String word = "";
        for (int i = 0; i < textChunk.length(); i++){
            char currentChar = textChunk.charAt(i);
            if (currentChar == ' ') {
                words.add(word);
                word = "";
            } else {
                word += textChunk.charAt(i);
            }
        }
        words.add(word);
        return words;
    }

    protected int determineRemoveWordCount(){
        int removeCount = (int)(((double)Proficiency/100) * allWords.size());
        if (removeCount < 3){
            removeCount = 3;
        }
        return removeCount;
    }

    protected int determineProficiencyChange(){
        int totalGuesses = wrongAnswers + rightAnswers;
        double rightPercent = (double)rightAnswers/totalGuesses;
        double wrongPercent = (double)wrongAnswers/totalGuesses;

        if (rightPercent == wrongPercent)
            return 0;
        else if (rightPercent > wrongPercent)
            return (int)(rightPercent * 10);
        else
            return ((int)(wrongPercent * 10)) * -1;
    }

    protected void endGame(){
        PackageCollection packageCollection = (PackageCollection) getApplicationContext();
        int proficiencyChange = determineProficiencyChange();
        if (proficiencyChange < 0)
            packageCollection.decrementProficiency(myPackageIndex,myLineIndex,proficiencyChange*-1);
        else
            packageCollection.incrementProficiency(myPackageIndex,myLineIndex,proficiencyChange);
        packageCollection.setSavedProficiency(myPackageIndex, myLineIndex);
        Intent newIntent =  new Intent(this, Homepage.class);
        startActivity(newIntent);
    }
}

