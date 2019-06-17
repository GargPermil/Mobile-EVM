package com.example.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    ArrayList<ListModel> mydata;
    Context context;

    public ListAdapter(Context context, ArrayList<ListModel> mydata) {
        this.mydata = mydata;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_view_1,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        final ListModel tmp = mydata.get(i);
        viewHolder.PartyLabel.setText(tmp.PartyName);
        viewHolder.VoteCount.setText(tmp.Votes+"");
        viewHolder.Vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Machine.getInstance(context).isOn() == true) {
                    mydata.set(i, new ListModel(tmp.PartyName, mydata.get(i).Votes+1));
                    Toast.makeText(context, "Voted : " + tmp.PartyName, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Switch On Machine", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if(Machine.getInstance(context).isShowResult() == true) {
            viewHolder.Vote.setVisibility(View.INVISIBLE);
            viewHolder.VoteCount.setVisibility(View.VISIBLE);
        } else {
            viewHolder.Vote.setVisibility(View.VISIBLE);
            viewHolder.VoteCount.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mydata.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        Button Vote;
        TextView PartyLabel;
        TextView VoteCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Vote = itemView.findViewById(R.id.Vote_Button);
            PartyLabel = itemView.findViewById(R.id.party_label);
            VoteCount = itemView.findViewById(R.id.VotesCount);
        }
    }

}
