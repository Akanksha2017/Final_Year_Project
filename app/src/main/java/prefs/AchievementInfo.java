package prefs;

/**
 * Created by TheSassyGourmet on 5/27/2017.
 */
import android.content.Context;
import android.content.SharedPreferences;

public class AchievementInfo {
    private static final String TAG = UserSession.class.getSimpleName();
    private static final String PREF_NAME = "achievements";
    private static final String KEY_EVENT = "event";
    private static final String KEY_POSITION = "position";
    private static final String KEY_ORG = "organiser";
    private static final String KEY_SOC = "society";

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;

    public AchievementInfo(Context ctx) {
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences(PREF_NAME, ctx.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setEvent(String name){
        editor.putString(KEY_EVENT, name);
        editor.apply();
    }

    public void setPosition(String position){
        editor.putString(KEY_POSITION, position);
        editor.apply();
    }

    public void setOrg(String organiser){
        editor.putString(KEY_ORG, organiser);
        editor.apply();
    }

    public void setSoc(String soc){
        editor.putString(KEY_SOC, soc);
        editor.apply();
    }

    public void clearAchievementInfo(){
        editor.clear();
        editor.commit();
    }

    public String getKeyEvent(){return prefs.getString(KEY_EVENT, "");}

    public String getKeyPosition(){return  prefs.getString(KEY_POSITION, "");}

    public String getKeyOrg(){return  prefs.getString(KEY_ORG, "");}

    public String getKeySoc(){return  prefs.getString(KEY_SOC, "");}

}
