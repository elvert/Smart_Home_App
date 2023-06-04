package com.example.ayaya;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<listitem> {
    private List<listitem> ProductItemList;

    private Context context;

    public ListViewAdapter(List<listitem> ProductItemList, Context context) {
        super(context, R.layout.listviewdetails, ProductItemList);
        this.ProductItemList = ProductItemList;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View listViewItem = inflater.inflate(R.layout.listviewdetails, null, true);

        TextView textViewcard = listViewItem.findViewById(R.id.Cardnum);
        TextView textViewStatus = listViewItem.findViewById(R.id.Status);

        listitem listItem = ProductItemList.get(position);
        textViewcard.setText(listItem.getCard_id());
        textViewStatus.setText(listItem.getStatus());

        return listViewItem;
    }
}