package edu.taylor.cse.pig.quiqstiq;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.LinkedList;

public class HomepageAdapter extends RecyclerView.Adapter<HomepageAdapter.ViewHolder>{
    private static final String TAG = "HomepageAdapter";

    private LinkedList<LinePackage> myPackageCollection;
    private Context myContext;

    public HomepageAdapter(LinkedList<LinePackage> packageCollection, Context context){
        myPackageCollection = packageCollection;
        myContext = context;
    }

    @NonNull
    @Override
    public HomepageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_single_line, viewGroup, false);
        HomepageAdapter.ViewHolder holder = new HomepageAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomepageAdapter.ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called.");
        LinePackage currentPackage = myPackageCollection.get(i);
        viewHolder.lineName.setText(currentPackage.getPackageName());
        viewHolder.progressBar.setMax(currentPackage.getTotalMaxProficiency());

        Context context = myContext.getApplicationContext();
        currentPackage.setSavedProficiency(context, currentPackage.getPackageName());
        viewHolder.progressBar.setProgress(currentPackage.getSavedProficiency(context, currentPackage.getPackageName()));

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + myPackageCollection.get(i).getPackageName());
                Intent nextIntent = new Intent(myContext, LineSelection.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("packageIndex", i);
                nextIntent.putExtras(bundle);
                myContext.startActivity(nextIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myPackageCollection.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView lineName;
        RelativeLayout parentLayout;
        ProgressBar progressBar;

        public ViewHolder(View itemView){
            super(itemView);
            lineName = itemView.findViewById(R.id.line_name);
            parentLayout = itemView.findViewById(R.id.line_parent_layout);
            progressBar = itemView.findViewById(R.id.progress_bar);
        }
    }
}
