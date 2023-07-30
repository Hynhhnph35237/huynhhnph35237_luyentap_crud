package com.huynhhn.huynhhnph35237_luyentap_crud.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.huynhhn.huynhhnph35237_luyentap_crud.Adapter.Adapter_khoa;
import com.huynhhn.huynhhnph35237_luyentap_crud.DAO.DAO_khoa;
import com.huynhhn.huynhhnph35237_luyentap_crud.DTO.DTO_khoa;
import com.huynhhn.huynhhnph35237_luyentap_crud.R;

import java.util.List;

public class Frag_Khoa extends Fragment {
    ListView lv_list;
    ImageButton add;

    EditText edt_add;

    DTO_khoa dto_khoa;
    DAO_khoa dao_khoa;

    List<DTO_khoa> list;

    Adapter_khoa adapter_khoa;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_khoa,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv_list= view.findViewById(R.id.lv_khoa);
        add = view.findViewById(R.id.ibtn_add);
        edt_add = view.findViewById(R.id.edt_add);
        edt_add.setVisibility(View.GONE);
        dao_khoa = new DAO_khoa(getContext());
        list = dao_khoa.getAll();
        adapter_khoa = new Adapter_khoa(getContext(),list);
        lv_list.setAdapter(adapter_khoa);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_add.setVisibility(View.VISIBLE);
                add.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       dto_khoa = new DTO_khoa();
                       dto_khoa.setTenKhoa(edt_add.getText().toString());
                       if(dao_khoa.AddRow(dto_khoa)>0){
                           Toast.makeText(getContext(), "Thêm khoa thành công", Toast.LENGTH_SHORT).show();
                           list.clear();
                           list.addAll(dao_khoa.getAll());
                           adapter_khoa.notifyDataSetChanged();
                           return;
                       }else {
                           Toast.makeText(getContext(), "Thêm khoa thất bại", Toast.LENGTH_SHORT).show();
                           return;
                       }
                   }
               });
            }
        });


    }
}
