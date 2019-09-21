package edu.taylor.cse.pig.quiqstiq;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

public class LinePackage implements Serializable
{
    private String packageName;
    private LinkedList<SingleLine> PackageContents;
    private int totalMaxProficiency;
    private int totalCurrentProficiency;
    private int proficiencyIncrement;

    public LinePackage(String name){
        packageName = name;
        PackageContents = new LinkedList<SingleLine>();
        totalCurrentProficiency = 50;
        totalMaxProficiency = 0;
        SingleLine temp = new SingleLine("temp", "temp");
        proficiencyIncrement = temp.getMaxProficiency();
    }

    public LinePackage(){
        PackageContents = new LinkedList<SingleLine>();
        totalCurrentProficiency = 50;
        totalMaxProficiency = 0;
        SingleLine temp = new SingleLine("temp", "temp");
        proficiencyIncrement = temp.getMaxProficiency();
    }

    public void addLine(SingleLine newLine){
        PackageContents.add(newLine);
        totalMaxProficiency += proficiencyIncrement;
    }

    public void addLine(String name, String content){
        SingleLine tempLine = new SingleLine(name,content);
        PackageContents.add(tempLine);
        totalMaxProficiency += proficiencyIncrement;
    }

    public SharedPreferences setPrefs(Context context){
        return context.getSharedPreferences("Proficiency", Context.MODE_PRIVATE);
    }

    public int getSavedProficiency(Context context, String verseName){
        return setPrefs(context).getInt(verseName, 0);
    }

    public void setSavedProficiency(Context context, String verseName){
        totalCurrentProficiency = 0;
        for (SingleLine currentLine : PackageContents){
            totalCurrentProficiency += currentLine.getProficiency();
        }
        SharedPreferences.Editor editor = setPrefs(context).edit();
        editor.putInt(verseName, totalCurrentProficiency);
        editor.commit();
    }

    public int getTotalMaxProficiency(){
        return totalMaxProficiency;
    }

    public String getPackageName(){
        return packageName;
    }

    public String getLineName(int i){
        SingleLine temp = PackageContents.get(i);
        return temp.getName();
    }
    public String getLineContent(int i){
        SingleLine temp = PackageContents.get(i);
        return temp.getContent();
    }
    public SingleLine getLine(int i){
        return  PackageContents.get(i);
    }

    public void setLine(SingleLine newLine, int i){
        PackageContents.set(i, newLine);
    }

    public int size(){
        return PackageContents.size();
    }

    public Iterator<SingleLine> iterator(){
        return PackageContents.iterator();
    }

}
