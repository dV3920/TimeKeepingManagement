package com.example.timekeepingmanagement;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
        this.context = context;
        this.resource = resource;
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
        ImageView ivEditProduct = convertView.findViewById(R.id.ivEditProduct);
        ImageView ivRemoveProduct = convertView.findViewById(R.id.ivRemoveProduct);
        ivEditProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddProductActivity.class);
                intent.putExtra("isAdd",false);
                intent.putExtra("Object",product);
                context.startActivity(intent);
            }
        });
        ivRemoveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder adb = new AlertDialog.Builder(context);
                adb.setMessage("Bạn có muốn xóa sản phẩm có mã là "+ product.getId());
                adb.setTitle("Thông báo");
                adb.setPositiveButton("Đồng Ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Boolean isSuccess =  new DataBase(context).removeProduct(product.getId());
                        if(isSuccess){
                            Toast.makeText(context,"Xóa thành công",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(context,"Lỗi",Toast.LENGTH_LONG).show();
                        }
                        data.remove(product);
                        notifyDataSetChanged();
                    }
                });
                adb.setNegativeButton("Hủy",null);
                adb.show();

            }
        });

        return convertView;
    }
}
