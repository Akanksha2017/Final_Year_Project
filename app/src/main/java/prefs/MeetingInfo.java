package prefs;

/**
 * Created by TheSassyGourmet on 5/29/2017.
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MeetingInfo {
    private static final String PREF_NAME = "meetingInfo";
    private static final String MEETING = "meeting";

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;

    public MeetingInfo(Context ctx) {
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences(PREF_NAME, ctx.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setMeeting(List<Meeting> saved){
        Gson gson = new Gson();
        String jsonSaved = gson.toJson(saved);
        editor.putString(MEETING, jsonSaved);
        editor.apply();
    }

    public void addMeeting(Meeting meeting) {
        List<Meeting> saved = getMeeting();
        if (saved == null)
            saved = new ArrayList<Meeting>();
        saved.add(meeting);
        setMeeting(saved);
    }

    public ArrayList<Meeting> getMeeting() {
        List<Meeting> saved;
        if (prefs.contains(MEETING)) {
            String jsonExpenses = prefs.getString(MEETING, "");
            Gson gson = new Gson();
            Meeting[] savedItems = gson.fromJson(jsonExpenses,
                    Meeting[].class);

            saved = Arrays.asList(savedItems);
            saved = new ArrayList<Meeting>(saved);
        } else
            return null;

        return (ArrayList<Meeting>) saved;
    }

    public void clearMeetingInfo(){
        editor.clear();
        editor.commit();
    }
}
