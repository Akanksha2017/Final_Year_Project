package prefs;

/**
 * Created by TheSassyGourmet on 5/27/2017.
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Float.parseFloat;

public class BudgetInfo {
    private static final String TAG = UserSession.class.getSimpleName();
    private static final String PREF_NAME = "budget";
    private static final String KEY_TOTAL = "0.0f";
    private static final String EXPENSE = "expense";
    public static Float LEFT = parseFloat(KEY_TOTAL);
    public static int COUNT = 0;

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;

    public BudgetInfo(Context ctx) {
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences(PREF_NAME, ctx.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setTotal(Float soc){
        editor.putFloat(KEY_TOTAL, soc);
        LEFT = soc;
        //editor.putFloat(LEFT,soc);
        editor.apply();
    }

    public void setExpense(List<Expense> saved){
        Gson gson = new Gson();
        String jsonSaved = gson.toJson(saved);
        editor.putString(EXPENSE, jsonSaved);
        editor.apply();
    }

    public void addExpense(Expense expense) {
        List<Expense> saved = getExpense();
        if (saved == null)
            saved = new ArrayList<Expense>();
        saved.add(expense);
        setExpense(saved);
        COUNT++;
    }

    public ArrayList<Expense> getExpense() {
        List<Expense> saved;
        if (prefs.contains(EXPENSE)) {
            String jsonExpenses = prefs.getString(EXPENSE, "");
            Gson gson = new Gson();
            Expense[] savedItems = gson.fromJson(jsonExpenses,
                    Expense[].class);

            saved = Arrays.asList(savedItems);
            saved = new ArrayList<Expense>(saved);
        } else
            return null;

        return (ArrayList<Expense>) saved;
    }

    public void clearBudgetInfo(){
        editor.clear();
        editor.commit();
    }

    public Float getKeyTotal(){return  prefs.getFloat(KEY_TOTAL, 0f);}

}
