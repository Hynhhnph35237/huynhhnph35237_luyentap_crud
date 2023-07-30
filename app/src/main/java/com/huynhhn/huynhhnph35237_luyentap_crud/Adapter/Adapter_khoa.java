package com.huynhhn.huynhhnph35237_luyentap_crud.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.huynhhn.huynhhnph35237_luyentap_crud.DTO.DTO_khoa;
import com.huynhhn.huynhhnph35237_luyentap_crud.DAO.DAO_khoa;

import com.huynhhn.huynhhnph35237_luyentap_crud.R;

import java.util.List;

public class Adapter_khoa extends BaseAdapter {
    Context context;
    List<DTO_khoa> list;

    public Adapter_khoa(Context context, List<DTO_khoa> list) {
        this.context = context;
        this.list = list;
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
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.item_khoa,parent,false);

        TextView id_khoa = convertView.findViewById(R.id.tv_id_khoa);
        TextView ten = convertView.findViewById(R.id.tv_ten_khoa);
        ImageButton update = convertView.findViewById(R.id.ibtn_update);
        ImageButton delete = convertView.findViewById(R.id.ibtn_delete);

        id_khoa.setText(list.get(position).getId()+"");
        ten.setText(list.get(position).getTenKhoa());
        DAO_khoa dao_khoa = new DAO_khoa(context);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Cảnh báo");
                builder.setMessage("Bạn có chắc chắn muốn xóa");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(dao_khoa.DeleteRow(list.get(position))>0){
                            Toast.makeText(context, "Đã xóa thành công", Toast.LENGTH_SHORT).show();
                            list.clear();
                            list.addAll(dao_khoa.getAll());
                            notifyDataSetChanged();
                        }else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                Dialog dialog = builder.create();
                dialog.show();


            }
        });

        return convertView;
    }
}
