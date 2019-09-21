package edu.taylor.cse.pig.quiqstiq;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class LineSelection extends AppCompatActivity {

    private static final String TAG = "LineSelection";

    private RecyclerView recyclerView;

    private int packageIndex;
    private LinePackage selectedPackage;

    private static Context myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myContext = this.getApplicationContext();
        Log.d(TAG,"onCreate: Successful Transition to Line Selection");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_selection);
        PackageCollection packageCollection = (PackageCollection) getApplicationContext(); /* Required to get data from singleton application */

        /* Get the chosen package from the previous segment */
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        selectedPackage = new LinePackage();
        packageIndex = (int)bundle.getSerializable("packageIndex");
        selectedPackage = packageCollection.getPackage(packageIndex);
        Log.d(TAG, "onCreate: Unpacked bundle");

        initRecyclerView();

    }

    private void initRecyclerView(){
        Log.d(TAG,"initRecyclerView: init");
        recyclerView = findViewById(R.id.line_parent_layout);
        LineSelectionAdapter adapter = new LineSelectionAdapter(selectedPackage, packageIndex, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}

