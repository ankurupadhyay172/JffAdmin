package com.ankursolution.jffmyadmin.data.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ankursolution.jffmyadmin.R;
import com.ankursolution.jffmyadmin.data.model.Varients;
import com.ankursolution.jffmyadmin.utils.ext.Common;


import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<Varients> {
    LayoutInflater inflater;
    List<Varients> list;

    public SpinnerAdapter(@NonNull Context context, int resource, @NonNull List<Varients> objects) {
        super(context, resource, objects);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        list = objects;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;

        if (view==null)
        {
            view = inflater.inflate(R.layout.spinner_item,parent,false);
        }

                TextView desc = view.findViewById(R.id.description);
                TextView price = view.findViewById(R.id.price);
                TextView title = view.findViewById(R.id.text1);

                title.setText(list.get(position).getVarientName());
                price.setText(Common.setPrice(list.get(position).getVarientPrice()));

        return view;
    }
}
