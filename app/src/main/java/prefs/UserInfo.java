package prefs;

/**
 * Created by TheSassyGourmet on 5/26/2017.
 */
        import android.content.Context;
        import android.content.SharedPreferences;

public class UserInfo {
    private static final String TAG = UserSession.class.getSimpleName();
    private static final String PREF_NAME = "userinfo";
    private static final String KEY_EMAIL = "mail";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_YEAR = "year";
    private static final String KEY_DEPT = "department";
    private static final String KEY_NAME = "name";
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;

    public UserInfo(Context ctx) {
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences(PREF_NAME, ctx.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setName(String name){
        editor.putString(KEY_NAME, name);
        editor.apply();
    }

    public void setYear(String password){
        editor.putString(KEY_YEAR, password);
        editor.apply();
    }

    public void setDept(String password){
        editor.putString(KEY_DEPT, password);
        editor.apply();
    }

    public void setEmail(String email){
        editor.putString(KEY_EMAIL, email);
        editor.apply();
    }

    public void setCategory(String category){
        editor.putString(KEY_CATEGORY, category);
        editor.apply();
    }

    public void clearUserInfo(){
        editor.clear();
        editor.commit();
    }


    public String getKeyEmail(){return prefs.getString(KEY_EMAIL, "");}

    public String getKeyCategory(){return  prefs.getString(KEY_CATEGORY, "");}

    public String getKeyName(){return  prefs.getString(KEY_NAME, "");}

    public String getKeyYear(){return  prefs.getString(KEY_YEAR, "");}

    public String getKeyDept(){return  prefs.getString(KEY_DEPT, "");}
}