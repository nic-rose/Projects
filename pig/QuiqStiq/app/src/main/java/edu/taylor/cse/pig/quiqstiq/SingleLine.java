package edu.taylor.cse.pig.quiqstiq;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;

public class SingleLine implements Serializable
{
    private String name;
    private String content;
    private int maxProficiency = 100;
    private int proficiency;

    public SingleLine(String name, String content) {
        this.name = name;
        this.content = content;
        this.proficiency = 0;
        maxProficiency = 100;
    }

    public String getContent(){
        return content;
    }

    public String getName(){
        return name;
    }

    public int getProficiency() {
        return  proficiency;
    }

    public int getMaxProficiency(){
        return maxProficiency;
    }

    public void incrementProficiency(){
        proficiency += 1;
        if (proficiency > maxProficiency){
            proficiency = maxProficiency;
        }
    }

    public void incrementProficiency(int amount){
        proficiency += amount;
        if (proficiency > maxProficiency){
            proficiency = maxProficiency;
        }
    }

    public void decrementProficiency(){
        proficiency -= 1;
        if (proficiency < 0){
            proficiency = 0;
        }
    }

    public void decrementProficiency(int amount){
        proficiency -= amount;
        if (proficiency < 0){
            proficiency = 0;
        }
    }

    public SharedPreferences setPrefs(Context context){
        return context.getSharedPreferences("Proficiency", Context.MODE_PRIVATE);
    }

    public int getSavedProficiency(Context context, String verseName){
        return setPrefs(context).getInt(verseName, 0);
    }

    public void setSavedProficiency(Context context, String verseName, int proficiency){
        SharedPreferences.Editor editor = setPrefs(context).edit();
        editor.putInt(verseName, proficiency);
        editor.commit();
    }

    public String getNameAndContent(){
        return name + ": " + content;
    }
}
