package com.example.nic_r.wordselection3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.Arrays;
import java.util.Random;


public class WordSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_selection);

        //TextView progressbar = findViewById(R.id.progressBar);
        TextView option1 = findViewById(R.id.option1);
        TextView option2 = findViewById(R.id.option2);
        TextView textBox = findViewById(R.id.textBox);
        String words = "For God so loved the world that he gave his one and only Son, that whoever believes in him shall not perish but have eternal life.";
        String [] goalArray = words.split(" ");
        String[] deconstruct = words.split(" ");

        int difficulty = 1;
        int numWordsRemoved = deconstruct.length * (int)(difficulty*.1);
        int[] wordsRemovedIndexes = {};

        if (numWordsRemoved > deconstruct.length){
            numWordsRemoved = deconstruct.length;
        }

        for (int i = wordsRemovedIndexes.length ; i < numWordsRemoved;){
            Random rand = new Random();
            int num = rand.nextInt(deconstruct.length);
            int count = 0;
            for (int j = 0; j < wordsRemovedIndexes.length; j++){
                if (wordsRemovedIndexes[j] == num){
                    count++;
                }
            }
            if (count == 0){
                wordsRemovedIndexes[i] = num;
            }
        }
        Arrays.sort(wordsRemovedIndexes);

        // export this in to its own function (black out words)
        for (int i = 0; i < wordsRemovedIndexes.length; i++){
            int length = deconstruct[wordsRemovedIndexes[i]].length();
            String underscore = "";
            // builds a string of _ so it can replace the word in the text
            for (int j = 0; j < length; j++){
                underscore += "_";
            }
            deconstruct[wordsRemovedIndexes[i]] = underscore;
        }
        //connect the deconstruct array together
        String blankText = "";
        for(int i = 0; i < deconstruct.length; i++){
            blankText += deconstruct[i];
        }
        textBox.setText(blankText);

        for(int i = 0; i < numWordsRemoved; i++){
            Random rand = new Random();
            int correctOption = rand.nextInt(2);
            String correctWord = goalArray[wordsRemovedIndexes[i]];
            String wrongWord = goalArray[rand.nextInt(deconstruct.length)];

            if (correctOption == 0){
                option1.setText(correctWord);
                option2.setText(wrongWord);

            }else{
                option1.setText(wrongWord);
                option2.setText(correctWord);
            }

            //onclick response from user



        }



    }
}
