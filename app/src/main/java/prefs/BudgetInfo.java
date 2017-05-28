package prefs;

/**
 * Created by TheSassyGourmet on 5/27/2017.
 */
import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BudgetInfo {
    private static final String TAG = UserSession.class.getSimpleName();
    private static final String PREF_NAME = "budget";
    private static final String KEY_TOTAL = "0.0";
    private static final String EXPENSE = "expense";
    private static final String LEFT = KEY_TOTAL;

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
        editor.putFloat(LEFT,soc);
        editor.apply();
    }

    public void setLeft(Float amnt){
        editor.putFloat(LEFT, amnt);
        editor.apply();
    }
    public void setExpense(List<Expense> saved){
        Gson gson = new Gson();
        String jsonSaved = gson.toJson(saved);
        editor.putString(EXPENSE, jsonSaved);
    }

    public void addExpense(Expense expense) {
        List<Expense> saved = getExpense();
        if (saved == null)
            saved = new ArrayList<Expense>();
        saved.add(expense);
        Float left_amnt = getKeyLeft();
        left_amnt -= expense.getPrice();
        setLeft(left_amnt);
        setExpense(saved);
    }

    public ArrayList<Expense> getExpense() {
        List<Expense> saved;

        if (prefs.contains(EXPENSE)) {
            String jsonExpenses = prefs.getString(EXPENSE, null);
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

    public Float getKeyLeft(){return  prefs.getFloat(LEFT, 0f);}

}
