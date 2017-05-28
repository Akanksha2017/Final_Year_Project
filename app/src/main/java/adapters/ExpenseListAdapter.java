package adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.final_year_project.R;

import java.util.List;

import prefs.BudgetInfo;
import prefs.Expense;

/**
 * Created by TheSassyGourmet on 5/28/2017.
 */

public class ExpenseListAdapter extends ArrayAdapter<Expense>{

    private Context context;
    List<Expense> expenses;
    BudgetInfo savedExpenses;

    public ExpenseListAdapter(Context context, List<Expense> expenses) {
        super(context, R.layout.expense_list_item, expenses);
        this.context = context;
        this.expenses = expenses;
        savedExpenses = new BudgetInfo(context);
    }

    private class ViewHolder {
        TextView expenseDateTxt;
        TextView expenseDescTxt;
        TextView expensePriceTxt;
    }

    @Override
    public int getCount() {
        return expenses.size();
    }

    @Override
    public Expense getItem(int position) {
        return expenses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expense_list_item, null);
            holder = new ViewHolder();
            holder.expenseDateTxt = (TextView) convertView
                    .findViewById(R.id.txt_exp_date);
            holder.expenseDescTxt = (TextView) convertView
                    .findViewById(R.id.txt_exp_detail);
            holder.expenseDateTxt = (TextView) convertView
                    .findViewById(R.id.txt_exp_amount);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Expense expense = (Expense) getItem(position);
        holder.expenseDateTxt.setText(expense.getDate());
        holder.expenseDescTxt.setText(expense.getDescription());
        holder.expensePriceTxt.setText(expense.getPrice()+"");
        return convertView;
    }

    @Override
    public void add(Expense expense) {
        super.add(expense);
        expenses.add(expense);
        notifyDataSetChanged();
    }
}
