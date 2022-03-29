package com.example.timekeepingmanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ProductAdapter extends ArrayAdapter {
    List<Product> data;
    Context context;
    int resource;

    public ProductAdapter(@NonNull Context context, int resource, @NonNull List<Product> data) {
        super(context, resource, data);
        this.data = data;
        this.context=context;
        this.resource=resource;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);
        TextView tvId = convertView.findViewById(R.id.tvId);
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvPrice = convertView.findViewById(R.id.tvPrice);
        Product product = data.get(position);
        tvId.setText(product.getId()+" ");
        tvName.setText(product.getName());
        tvPrice.setText(product.getPrice()+"");

        return convertView;
    }
}
