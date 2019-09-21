package com.example.ben.kwiqstiq;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
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
    //public String Lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";

    //Temp Globals for testing
    public String GlobaltextChunk = "The yellow frog jumped! Hello he said.";
    public String textName = "Yellow Frog Poem";
    public int DIFFICULTY = 2;

    //Keep
    Random rand = new Random();
    public List<String> allWords =  parseText(GlobaltextChunk);

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
        List<String> words = parseText(GlobaltextChunk);
        removeWords(words);
        String finalOutputText = listToString(words);
        txtTextChunk.setText(finalOutputText);

        ProgressBar progBar = findViewById(R.id.progressBar);
        progBar.setMax(DIFFICULTY * 3);

        final Button button1 =  findViewById(R.id.button1);
        final Button button2 =  findViewById(R.id.button2);
        final Button button3 =  findViewById(R.id.button3);
        final Button button4 =  findViewById(R.id.button4);

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

    protected void updateTextChunk(TextView txtTextChunk){
        txtTextChunk.setText(AddRemovedWord(txtTextChunk.getText().toString()));
    }

    protected String AddRemovedWord(String textChunk){
        List<String> currentTextChunk =  parseText(textChunk);
        int changeIdx;
        if (currentBlankIdx == 0)
            changeIdx = removedWordsIdx.get(currentBlankIdx);
        else
            changeIdx = removedWordsIdx.get(currentBlankIdx - 1);

        //TODO: Underline replaced words?
        //SpannableString word =  new SpannableString(allWords.get(changeIdx));
        //word.setSpan(new UnderlineSpan(), 0, word.length(), 0);
        currentTextChunk.set(changeIdx, allWords.get(changeIdx));
        return listToString(currentTextChunk);
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
        int correctButtonIdx = rand.nextInt(4);

        for (int i = 0; i < btnList.size(); i++){
            if (i == correctButtonIdx){
                btnList.get(i).setText(Character.toString(removedWordsChars.get(currentBlankIdx)));
            } else {
                int letterIdx = rand.nextInt(26);
                btnList.get(i).setText(Character.toString(alphabet.charAt(letterIdx)));
            }

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
