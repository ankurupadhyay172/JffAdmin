package com.ankursolution.jffmyadmin.data.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.ankursolution.jffmyadmin.R;

import java.util.List;

public class OrderStatusAdapter extends ArrayAdapter<String> {
    LayoutInflater inflater;
    List<String> list;


    public OrderStatusAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        list = objects;

    }


    @Override
    public View getView(int position,  View convertView, @NonNull ViewGroup parent) {

        View view = convertView;

        if (view==null)
        {
            view = inflater.inflate(R.layout.order_status_item,parent,false);
        }

        TextView desc = view.findViewById(R.id.description);
        TextView price = view.findViewById(R.id.price);
        TextView title = view.findViewById(R.id.text1);

        title.setText(list.get(position).toUpperCase());


        return view;
    }
}

