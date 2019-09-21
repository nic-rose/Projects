package edu.taylor.cse.pig.quiqstiq;

import android.content.Context;
import android.content.Intent;
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

public class LineSelectionAdapter extends RecyclerView.Adapter<LineSelectionAdapter.ViewHolder>{
    private static final String TAG = "LineSelectionAdapter";

    private LinePackage myLines = new LinePackage();
    private Context myContext;
    private int myPackageIndex;

    public LineSelectionAdapter(LinePackage lines, int packageIndex, Context context){
        myLines = lines;
        myContext = context;
        myPackageIndex = packageIndex;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_single_line, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called.");
        SingleLine currentLine = myLines.getLine(i);
        viewHolder.lineName.setText(currentLine.getName());
        viewHolder.progressBar.setMax(currentLine.getMaxProficiency());
        viewHolder.progressBar.setProgress(currentLine.getProficiency());
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + myLines.getLineName(i));
                Intent nextIntent = new Intent(myContext, GameSelection.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("packageIndex", myPackageIndex );
                bundle.putSerializable("lineIndex", i);
                nextIntent.putExtras(bundle);
                myContext.startActivity(nextIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return myLines.size();
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


