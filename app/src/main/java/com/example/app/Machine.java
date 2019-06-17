package com.example.app;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

public class Machine {
    private static Machine ourInstance = null;
    private final ArrayList<ListModel> mydata = new ArrayList<>();
    private boolean isOn = false;
    private boolean isShowResult = false;
    private final ListAdapter adapter;
    private final int MAX_PARTIES = 15;

    private Machine(Context context) {
        adapter = new ListAdapter(context, mydata);
    }

    public static Machine getInstance(Context context) {
        if(ourInstance != null)
            return ourInstance;
        else {
            ourInstance = new Machine(context);
            return ourInstance;
        }
    }

    public boolean isOn() {
        return isOn;
    }

    public ArrayList<ListModel> getMydata() {
        return mydata;
    }

    public void setOn(Context context, boolean isChecked) {
        isOn = isChecked;
        if(isChecked == true) {
            new Storage(context).LoadVotes(mydata);
            adapter.notifyDataSetChanged();
        } else {
            isShowResult = false;
            new Storage(context).SaveVotes(mydata);
            mydata.clear();
            adapter.notifyDataSetChanged();
        }
    }

    public boolean isShowResult() {
        return isShowResult;
    }

    public ListAdapter getAdapter() {
        return adapter;
    }

    public void setShowResult(boolean showResult) throws Exception {
        if(isOn == false) {
            throw new Exception("Please switch on machine");
        } else {
            isShowResult = showResult;
            adapter.notifyDataSetChanged();
        }
    }

    public void addParty(String party) throws Exception {
        if(isOn == false) {
            throw new Exception("Please switch on machine");
        }
        if(mydata.size() > MAX_PARTIES) {
            throw new Exception("More than " + MAX_PARTIES + " Parties are not allowed");
        } else if(mydata.size() < MAX_PARTIES && isOn == true) {
            mydata.add(new ListModel(party));
            adapter.notifyDataSetChanged();
        }
    }

    public void clearParty() throws Exception {
        if(isOn == false) {
            throw new Exception("Please switch on machine");
        } else {
            mydata.clear();
            adapter.notifyDataSetChanged();
        }
    }

    public void reset() throws Exception {
        if(isOn == false) {
            throw new Exception("Please switch on machine");
        } else {
            Object[] t = mydata.toArray();
            mydata.clear();
            for(Object x: t) {
                ListModel tmp = (ListModel) x;
                mydata.add(new ListModel(tmp.PartyName));
            }
            adapter.notifyDataSetChanged();
        }
    }
}
