package com.example.app;

import org.json.JSONException;
import org.json.JSONObject;

public class ListModel {

    private final String JSON_PARTY_NAME = "PartyName";
    private final String JSON_VOTES = "Votes";

    public final String PartyName;
    public final int Votes;

    public ListModel(JSONObject object) throws JSONException {
        PartyName = object.getString(JSON_PARTY_NAME);
        Votes = object.getInt(JSON_VOTES);
    }

    public ListModel(String partyName) {
        PartyName = partyName;
        Votes = 0;
    }

    public ListModel(String partyName, int votes) {
        PartyName = partyName;
        if (votes < 0)
            Votes = 0;
        else
            Votes = votes;
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject object = new JSONObject();
        object.put(JSON_PARTY_NAME, PartyName);
        object.put(JSON_VOTES, Votes);
        return object;
    }
}
