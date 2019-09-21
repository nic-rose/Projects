package edu.taylor.cse.pig.quiqstiq;

import android.app.Application;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class PackageCollection extends Application implements Serializable
{
    private LinkedList<LinePackage> CollectionContents;
    private static final String TAG = "PackageCollection";

    /* On application startup */
    @Override
    public void onCreate(){
        super.onCreate();
        Log.d(TAG, "onCreate: called");
        PackageCollection packageCollection = (PackageCollection) getApplicationContext();
        addPackage("Test");
        packageCollection.addLine("Esther 8:9","Then were the king's scribes called at that time in the third month, that is, the month Sivan, on the three and twentieth day thereof; and it was written according to all that Mordecai commanded unto the Jews, and to the lieutenants, and the deputies and rulers of the provinces which are from India unto Ethiopia, an hundred twenty and seven provinces, unto every province according to the writing thereof, and unto every people after their language, and to the Jews according to their writing, and according to their language.",0);
        packageCollection.addLine("Luke 12:53", "The father shall be divided against the son, and the son against the father; the mother against the daughter, and the daughter against the mother; the mother in law against her daughter in law, and the daughter in law against her mother in law.",0);
        packageCollection.addLine("John 11:35","Jesus wept.",0);
        SingleLine tempLine = new SingleLine("John 1:1", "In the beginning was the Word, and the Word was with God, and the Word was God.");
        tempLine.incrementProficiency(45);
        LinePackage thisPackage = getPackage(0);
        thisPackage.getSavedProficiency(this, thisPackage.getPackageName());
        packageCollection.addLine(tempLine,0);
        packageCollection.incrementProficiency(0,0,85);

        Log.d(TAG, "onCreate: created Test");
        addPackage("Psalm 23");
        packageCollection.addLine("Psalm 23:1", "The LORD is my shepherd; I shall not want.", 1);
        packageCollection.addLine("Psalm 23:2", "He maketh me to lie down in green pastures: he leadeth me beside the still waters.", 1);
        packageCollection.addLine("Psalm 23:3", "He restoreth my soul: he leadeth me in the paths of righteousness for his name's sake.", 1);
        packageCollection.addLine("Psalm 23:4", "Yea, though I walk through the valley of the shadow of death, I will fear no evil: for thou art with me; thy rod and thy staff they comfort me.", 1);
        packageCollection.addLine("Psalm 23:5", "Thou preparest a table before me in the presence of mine enemies: thou anointest my head with oil; my cup runneth over.", 1);
        packageCollection.addLine("Psalm 23:6", "Surely goodness and mercy shall follow me all the days of my life: and I will dwell in the house of the LORD for ever.", 1);
        thisPackage = getPackage(1);
        packageCollection.incrementProficiency(1,0,thisPackage.getSavedProficiency(this, thisPackage.getPackageName()));
        Log.d(TAG, "onCreate: created Psalm 23");
        addPackage("Encouragement");
        packageCollection.addLine("Joshua 1:9","Have not I commanded thee? Be strong and of a good courage; be not afraid, neither be thou dismayed: for the Lord thy God is with thee whithersoever thou goest.",2);
        packageCollection.addLine("Psalm 46:1","God is our refuge and strength, a very present help in trouble.",2);
        packageCollection.addLine("Isaiah 40:31","But they that wait upon the Lord shall renew their strength; they shall mount up with wings as eagles; they shall run, and not be weary; and they shall walk, and not faint.",2);
        packageCollection.addLine("Isaiah 41:10","Fear thou not; for I am with thee: be not dismayed; for I am thy God: I will strengthen thee; yea, I will help thee; yea, I will uphold thee with the right hand of my righteousness.",2);
        packageCollection.addLine("Romans 8:38-39","For I am persuaded, that neither death, nor life, nor angels, nor principalities, nor powers, nor things present, nor things to come, Nor height, nor depth, nor any other creature, shall be able to separate us from the love of God, which is in Christ Jesus our Lord.",2);
        packageCollection.addLine("Philippians 4:6","Be careful for nothing; but in every thing by prayer and supplication with thanksgiving let your requests be made known unto God.",2);
        packageCollection.addLine("Philippians 4:12-13","I know both how to be abased, and I know how to abound: every where and in all things I am instructed both to be full and to be hungry, both to abound and to suffer need. I can do all things through Christ which strengtheneth me.",2);
        packageCollection.addLine("2 Timothy 1:7","For God hath not given us the spirit of fear; but of power, and of love, and of a sound mind.",2);
        thisPackage = getPackage(2);
        thisPackage.getSavedProficiency(this, thisPackage.getPackageName());
        Log.d(TAG, "onCreate: created Encouragement");
        addPackage("Passages from Psalms");
        packageCollection.addLine("Psalm 21:1-6", "The LORD is my shepherd; I shall not want. He maketh me to lie down in green pastures: he leadeth me beside the still waters. He restoreth my soul: he leadeth me in the paths of righteousness for his name's sake. Yea, though I walk through the valley of the shadow of death, I will fear no evil: for thou art with me; thy rod and thy staff they comfort me. Thou preparest a table before me in the presence of mine enemies: thou anointest my head with oil; my cup runneth over. Surely goodness and mercy shall follow me all the days of my life: and I will dwell in the house of the LORD for ever.",3);
        packageCollection.addLine("Psalm 46:1","God is our refuge and strength, a very present help in trouble.",3);
        packageCollection.addLine("Psalm 117:1-2","O praise the LORD, all ye nations: praise him, all ye people. For his merciful kindness is great toward us: and the truth of the LORD endureth for ever. Praise ye the LORD.",3);
        packageCollection.addLine("Psalm 139:1-6","O lord, thou hast searched me, and known me. Thou knowest my downsitting and mine uprising, thou understandest my thought afar off. Thou compassest my path and my lying down, and art acquainted with all my ways. For there is not a word in my tongue, but, lo, O Lord, thou knowest it altogether. Thou hast beset me behind and before, and laid thine hand upon me. Such knowledge is too wonderful for me; it is high, I cannot attain unto it.",3);
        thisPackage = getPackage(3);
        thisPackage.getSavedProficiency(this, thisPackage.getPackageName());
        Log.d(TAG, "onCreate: Passages from Psalms");
        addPackage("Wisdom From Proverbs");
        packageCollection.addLine("Proverbs 1:7", "The fear of the Lord is the beginning of knowledge: but fools despise wisdom and instruction.",4);
        packageCollection.addLine("Proverbs 3:5-6", "Trust in the Lord with all thine heart; and lean not unto thine own understanding. In all thy ways acknowledge him, and he shall direct thy paths.",4);
        packageCollection.addLine("Proverbs 4:23","Keep thy heart with all diligence; for out of it are the issues of life.",4);
        packageCollection.addLine("Proverbs 22:6","Train up a child in the way he should go: and when he is old, he will not depart from it.",4);
        packageCollection.addLine("Proverbs 31:10", "Who can find a virtuous woman? for her price is far above rubies.",4);
        thisPackage = getPackage(4);
        thisPackage.getSavedProficiency(this, thisPackage.getPackageName());
        Log.d(TAG, "onCreate: Wisdom from Proverbs");
        addPackage("The Romans Road");
        packageCollection.addLine("Romans 1:20-21","For the invisible things of him from the creation of the world are clearly seen, being understood by the things that are made, even his eternal power and Godhead; so that they are without excuse: Because that, when they knew God, they glorified him not as God, neither were thankful; but became vain in their imaginations, and their foolish heart was darkened.",5);
        packageCollection.addLine("Romans 3:23", "For all have sinned, and come short of the glory of God;", 5);
        packageCollection.addLine("Romans 5:8", "But God commendeth his love toward us, in that, while we were yet sinners, Christ died for us.", 5);
        packageCollection.addLine("Romans 6:23", "For the wages of sin is death; but the gift of God is eternal life through Jesus Christ our Lord.",5);
        packageCollection.addLine("Romans 10:9-10","That if thou shalt confess with thy mouth the Lord Jesus, and shalt believe in thine heart that God hath raised him from the dead, thou shalt be saved. For with the heart man believeth unto righteousness; and with the mouth confession is made unto salvation.",5);
        packageCollection.addLine("Romans 10:13", "For whosoever shall call upon the name of the Lord shall be saved.", 5);
        packageCollection.addLine("Romans 11:36","For of him, and through him, and to him, are all things: to whom be glory for ever. Amen.", 5);
        thisPackage = getPackage(5);
        thisPackage.getSavedProficiency(this, thisPackage.getPackageName());
        Log.d(TAG, "onCreate: The Romans Road");
        addPackage("Psalm 117");
        packageCollection.addLine("Psalm 117:1","O praise the Lord, all ye nations: praise him, all ye people.",6);
        packageCollection.addLine("Psalm 117:2", "For his merciful kindness is great toward us: and the truth of the Lord endureth for ever. Praise ye the LORD.",6);
        thisPackage = getPackage(6);
        thisPackage.getSavedProficiency(this, thisPackage.getPackageName());
        Log.d(TAG, "onCreate: Psalm 117");
    }

    public PackageCollection(){
        CollectionContents = new LinkedList<LinePackage>();
    }

    public void addPackage(LinePackage newPackage){
        CollectionContents.add(newPackage);
    }

    public void addPackage(String packageName){
        LinePackage newPackage = new LinePackage(packageName);
        CollectionContents.add(newPackage);
    }

    public void addLine(SingleLine newLine, int packageNum){
        LinePackage selectedPackage = CollectionContents.get(packageNum);
        selectedPackage.addLine(newLine);
        CollectionContents.set(packageNum, selectedPackage);
        selectedPackage.getLine(selectedPackage.size()-1).incrementProficiency( selectedPackage.getSavedProficiency(this, newLine.getName()));
    }

    public void addLine(String name, String content, int packageNum){
        LinePackage selectedPackage = CollectionContents.get(packageNum);
        selectedPackage.addLine(name, content);
        CollectionContents.set(packageNum, selectedPackage);
        selectedPackage.getLine(selectedPackage.size()-1).incrementProficiency( selectedPackage.getSavedProficiency(this, name));
    }

    public void incrementProficiency(int packageNum, int lineNum, int amount){
        LinePackage selectedPackage = CollectionContents.get(packageNum);
        SingleLine selectedLine = selectedPackage.getLine(lineNum);
        selectedLine.incrementProficiency(amount);
        selectedPackage.setLine(selectedLine, lineNum);
        CollectionContents.set(packageNum, selectedPackage);
    }

    public void decrementProficiency(int packageNum, int lineNum, int amount){
        LinePackage selectedPackage = CollectionContents.get(packageNum);
        SingleLine selectedLine = selectedPackage.getLine(lineNum);
        selectedLine.decrementProficiency(amount);
        selectedPackage.setLine(selectedLine, lineNum);
        CollectionContents.set(packageNum, selectedPackage);
    }

    public int getProficiency(int packageNum, int lineNum){
        LinePackage selectedPackage = CollectionContents.get(packageNum);
        SingleLine selectedLine = selectedPackage.getLine(lineNum);
        return  selectedLine.getProficiency();
    }

    public void setSavedProficiency(int packageNum, int lineNum){
        LinePackage selectedPackage = CollectionContents.get(packageNum);
        SingleLine selectedLine = selectedPackage.getLine(lineNum);
        Context context = this.getApplicationContext();
        SharedPreferences.Editor editor = selectedLine.setPrefs(context).edit();
        editor.putInt(selectedLine.getName(), selectedLine.getProficiency());
        editor.commit();
    }

    public LinkedList<LinePackage> getContent(){
        return CollectionContents;
    }

    public LinePackage getPackage(int packageNum){
        return CollectionContents.get(packageNum);
    }

    public  String getPackageName(int packageNum){
        LinePackage selectedPackage = CollectionContents.get(packageNum);
        return  selectedPackage.getPackageName();
    }

    public SingleLine getLine(int packageNum, int lineNum){
        LinePackage selectedPackage = CollectionContents.get(packageNum);
        return selectedPackage.getLine(lineNum);
    }

    public String getLineName(int packageNum, int lineNum) {
        LinePackage selectedPackage = CollectionContents.get(packageNum);
        return selectedPackage.getLineName(lineNum);
    }

    public String getLineContent(int packageNum, int lineNum) {
        LinePackage selectedPackage = CollectionContents.get(packageNum);
        return selectedPackage.getLineContent(lineNum);
    }

    public int size(){
        return CollectionContents.size();
    }

    public Iterator<LinePackage> iterator(){
        return CollectionContents.iterator();
    }
}
