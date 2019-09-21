package com.example.ben.kwiqstiq;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.util.Collections;
import java.util.Random;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    //public String textChunk = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
            //"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";

    //Temp Globals for testing
    protected String textChunk = "The yellow frog jumped! Hello he said.";
    protected String textName = "Yellow Frog Poem";
    public int DIFFICULTY = 2;

    //Keep
    Random rand = new Random();
    public List<String> allWords =  parseText(textChunk);

    //Try and get rid of
    public List<Character> removedWordsChars = new ArrayList<>();
    public List<Integer> removedWordsIdx = new ArrayList<>();
    public int currentBlankIdx = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView txtTextName = findViewById(R.id.chunkTextName);
        txtTextName.setText(textName);

        TextView txtTextChunk = findViewById(R.id.textChunk);
        List<String> words = parseText(textChunk);
        removeWords(words);
        String finalOutputText = listToString(words);
        txtTextChunk.setText(finalOutputText);

        ProgressBar progBar = findViewById(R.id.progressBar);
        progBar.setMax(DIFFICULTY * 3);

        final Button button1 =  findViewById(R.id.btnOne);
        final Button button2 =  findViewById(R.id.btnTwo);
        final Button button3 =  findViewById(R.id.btnThree);
        final Button button4 =  findViewById(R.id.btnFour);

        //TODO: Change the hard coding of the number of buttons
        List<Button> btnList =  new ArrayList<>();
        btnList.add(button1);
        btnList.add(button2);
        btnList.add(button3);
        btnList.add(button4);

        setButtonFunction(button1, progBar, txtTextChunk, btnList);
        setButtonFunction(button2, progBar, txtTextChunk, btnList);
        setButtonFunction(button3, progBar, txtTextChunk, btnList);
        setButtonFunction(button4, progBar, txtTextChunk, btnList);

        setAllButtonText(btnList);
    }

    protected void receiveTextInfo(String textName, String textChunk){
        this.textName = textName;
        this.textChunk = textChunk;
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

        //TODO: Underline replaced words?
        currentTextChunk.set(changeIdx, allWords.get(changeIdx));
        String newTextChunk = listToString(currentTextChunk);

        SpannableString textWithUnderlines =  new SpannableString(newTextChunk);
        for (int i = 0; i < currentBlankIdx; i++){
            List<Integer> startStopIdxs = findWordIndex(textChunk, removedWordsIdx.get(i));
            textWithUnderlines.setSpan(new ForegroundColorSpan(Color.GREEN), startStopIdxs.get(0), startStopIdxs.get(1), 0);
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

    protected void setButtonFunction(final Button button, final ProgressBar progBar, final TextView txtTextChunk, final List<Button> btnList){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentBlankIdx >= DIFFICULTY * 3)
                    return;
                if (button.getText().toString().toUpperCase().equals(removedWordsChars.get(currentBlankIdx).toString().toUpperCase())) {
                    progBar.incrementProgressBy(1);
                    currentBlankIdx++;
                    updateTextChunk(txtTextChunk);
                    if (currentBlankIdx < DIFFICULTY * 3 )
                        setAllButtonText(btnList);
                } else {
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

    protected void removeWords(List<String> textChunk){
        List<Integer> removedAlreadyIdx = new ArrayList<>();
        //List<Integer> removedWords =  new ArrayList<>();
        for (int i = 0; i < DIFFICULTY * 3; i++){
            int removeIdx = rand.nextInt(textChunk.size());
            while (removedAlreadyIdx.contains(removeIdx)){
                removeIdx = rand.nextInt(textChunk.size());
            }
            //textChunk.set(removeIdx, addUnderlines(textChunk.get(removeIdx).length()));
            removedAlreadyIdx.add(removeIdx);
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

}
