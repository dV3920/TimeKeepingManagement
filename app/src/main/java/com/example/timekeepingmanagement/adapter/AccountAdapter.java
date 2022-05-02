package com.example.timekeepingmanagement.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;





import com.example.timekeepingmanagement.AddAccountActivity;
import com.example.timekeepingmanagement.R;

import com.example.timekeepingmanagement.entity.Users;

import java.io.Serializable;

import java.util.List;

public class AccountAdapter extends BaseAdapter implements Serializable{
    private List<Users> data;
    private Context context;
    private int layout;

    public AccountAdapter() {
    }

    public AccountAdapter(List<Users> data, Context context, int layout) {
        this.data = data;
        this.context = context;
        this.layout = layout;
    }

    public List<Users> getData() {
        return data;
    }

    public void setData(List<Users> data) {
        this.data = data;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    private class ViewHolder{
        TextView txtUsername,txtPasswd,idEmployee, txtID;
        ImageView imgDelete, imgEdit;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.txtID = (TextView) view.findViewById(R.id.tvID);
            holder.txtUsername = (TextView) view.findViewById(R.id.tvIdUsername);
            holder.txtPasswd = (TextView) view.findViewById(R.id.tvPasswd);
            holder.idEmployee = (TextView) view.findViewById(R.id.tvIDEmployee);
            holder.imgDelete = (ImageView) view.findViewById(R.id.ivRemoveAccount);
            holder.imgEdit = (ImageView) view.findViewById(R.id.ivEditAccount);

            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        Users users = data.get(i);
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddAccountActivity.class);
                intent.putExtra("flag","edit");
                intent.putExtra("id", Integer.toString(users.getId()));
                intent.putExtra("username", users.getUsername());
                intent.putExtra("passwd", users.getPasswd());
                intent.putExtra("idEmployee", Integer.toString(users.getIdEmployee()));
                context.startActivity(intent);
            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddAccountActivity.class);
                intent.putExtra("flag","delete");
                intent.putExtra("id_remove", Integer.toString(users.getId()));
                context.startActivity(intent);
            }
        });
        holder.idEmployee.setText(Integer.toString(users.getIdEmployee()));
        holder.txtID.setText(Integer.toString(users.getId()));
        holder.txtUsername.setText(users.getUsername());
        holder.txtPasswd.setText(users.getPasswd());
        return view;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }



}
