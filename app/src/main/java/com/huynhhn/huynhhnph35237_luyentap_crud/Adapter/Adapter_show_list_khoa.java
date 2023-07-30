package com.huynhhn.huynhhnph35237_luyentap_crud.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.huynhhn.huynhhnph35237_luyentap_crud.DTO.DTO_khoa;
import com.huynhhn.huynhhnph35237_luyentap_crud.DTO.DTO_khoa;
import com.huynhhn.huynhhnph35237_luyentap_crud.R;

import java.util.List;

public class Adapter_show_list_khoa extends BaseAdapter {
    Context context;
    List<DTO_khoa> list;

    public Adapter_show_list_khoa(Context context, List<DTO_khoa> list) {
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

        update.setVisibility(View.GONE);
        delete.setVisibility(View.GONE);
        return convertView;
    }
}
