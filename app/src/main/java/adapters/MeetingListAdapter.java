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

import prefs.Meeting;
import prefs.MeetingInfo;

/**
 * Created by TheSassyGourmet on 5/29/2017.
 */

public class MeetingListAdapter extends ArrayAdapter<Meeting> {
    private Context context;
    List<Meeting> expenses;
    MeetingInfo savedExpenses;

    public MeetingListAdapter(Context context, List<Meeting> expenses) {
        super(context, R.layout.meeting_list_item, expenses);
        this.context = context;
        this.expenses = expenses;
        savedExpenses = new MeetingInfo(context);
    }
    private class ViewHolder {
        TextView expenseDateTxt;
        TextView expenseFromTxt;
        TextView expenseToTxt;
        TextView expenseObjectiveTxt;
    }

    @Override
    public int getCount() {
        return expenses.size();
    }

    @Override
    public Meeting getItem(int position) {
        return expenses.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.meeting_list_item, null);
            holder = new ViewHolder();
            holder.expenseDateTxt = (TextView) convertView
                    .findViewById(R.id.txt_meet_date);
            holder.expenseFromTxt = (TextView) convertView
                    .findViewById(R.id.txt_meet_from);
            holder.expenseToTxt = (TextView) convertView
                    .findViewById(R.id.txt_meet_to);
            holder.expenseObjectiveTxt = (TextView) convertView
                    .findViewById(R.id.txt_meet_objective);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Meeting expense = (Meeting) getItem(position);
        holder.expenseDateTxt.setText(expense.getMeetingDate().toString());
        holder.expenseFromTxt.setText(expense.getFrom());
        holder.expenseToTxt.setText(expense.getTo());
        holder.expenseObjectiveTxt.setText(expense.getObjective());
        return convertView;
    }
}
