package edu.taylor.cse.pig.quiqstiq;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.LinkedList;


public class Homepage extends AppCompatActivity {

    private static final String TAG = "Homepage";
    private RecyclerView recyclerView;
    private LinkedList<LinePackage> myPackageCollection;
    private static Context myContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: called");
        setContentView(R.layout.activity_homepage);

        myContext = this.getApplicationContext();

        setContentView(R.layout.activity_homepage);
        PackageCollection packageCollection = (PackageCollection) getApplicationContext(); /* Required to get data from singleton application */
        myPackageCollection = packageCollection.getContent();
        Log.d(TAG,"onCreate: Got package");
        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init");
        recyclerView = findViewById(R.id.package_parent_layout);
        HomepageAdapter adapter = new HomepageAdapter(myPackageCollection, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
