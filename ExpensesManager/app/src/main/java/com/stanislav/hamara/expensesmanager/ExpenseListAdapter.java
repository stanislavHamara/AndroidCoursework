package com.stanislav.hamara.expensesmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by stan on 07/04/15.
 */
public class ExpenseListAdapter extends BaseAdapter {

    Context context;
    List<Expense> list;
    private static LayoutInflater inflater = null;

    public ExpenseListAdapter(Context context, List<Expense> list){
        this.context = context;
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if(vi == null)
            vi = inflater.inflate(R.layout.row, null);

        TextView category = (TextView) vi.findViewById(R.id.category);
        TextView desc = (TextView) vi.findViewById(R.id.desciption);
        TextView price = (TextView) vi.findViewById(R.id.price);
        TextView date = (TextView) vi.findViewById(R.id.time);

        category.setText(list.get(position).getCategory() + ", " + list.get(position).getSubcategory());
        desc.setText(list.get(position).getDescription());
        price.setText(list.get(position).getCurrency() + list.get(position).getWhole_currency() + "." + list.get(position).getSmall_currency());
        date.setText(list.get(position).getDate());
        return  vi;
    }
}
