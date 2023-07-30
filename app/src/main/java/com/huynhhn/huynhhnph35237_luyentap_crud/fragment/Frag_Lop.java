package com.huynhhn.huynhhnph35237_luyentap_crud.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huynhhn.huynhhnph35237_luyentap_crud.Adapter.Adapter_lop;
import com.huynhhn.huynhhnph35237_luyentap_crud.Adapter.Adapter_show_list_khoa;
import com.huynhhn.huynhhnph35237_luyentap_crud.DAO.DAO_khoa;
import com.huynhhn.huynhhnph35237_luyentap_crud.DAO.DAO_lop;
import com.huynhhn.huynhhnph35237_luyentap_crud.DTO.DTO_khoa;
import com.huynhhn.huynhhnph35237_luyentap_crud.DTO.DTO_lop;
import com.huynhhn.huynhhnph35237_luyentap_crud.R;

import java.util.List;

public class Frag_Lop extends Fragment {
    RecyclerView recyclerView;
    List<DTO_khoa> list_khoa;
    List<DTO_lop> list_lop;
    ImageButton add, search, sapxep;
    EditText edt_search;
    Adapter_lop adapter_lop;
    DAO_lop dao_lop;
    DTO_khoa dto_khoa;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lop, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.lv_lop);
        add = view.findViewById(R.id.ibtn_add_lop);
        search = view.findViewById(R.id.ibtn_search);
        sapxep = view.findViewById(R.id.tbtn_sapxep);
        edt_search = view.findViewById(R.id.edt_search);
        edt_search.setVisibility(View.GONE);


        dao_lop = new DAO_lop(getContext());
        list_lop = dao_lop.getAll();
        adapter_lop = new Adapter_lop(getContext(), list_lop);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter_lop);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_search.setVisibility(View.VISIBLE);
                edt_search.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapter_lop.getFilter().filter(s.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        adapter_lop.getFilter().filter(s.toString());
                    }
                });
            }
        });

        sapxep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Chọn sắp xếp theo");
                String[] loai = new String[]{
                        "Tăng dần", "Giảm dần"
                };
                builder.setItems(loai, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                adapter_lop.sapXepTangDan();
                                dialog.dismiss();
                                break;
                            case 1:
                                adapter_lop.sapXepgiam();
                                dialog.dismiss();
                                break;
                        }
                    }
                });
                Dialog dialog = builder.create();
                dialog.show();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View view1 = inflater.inflate(R.layout.dialog_lop, null);
                builder.setView(view1);
                Dialog dialog = builder.create();
                dialog.show();

                EditText tenLop = view1.findViewById(R.id.edt_tenlop);
                EditText tenKhoa = view1.findViewById(R.id.edt_ten_khoa_lop);
                EditText siso = view1.findViewById(R.id.edt_siso);
                ImageButton them = view1.findViewById(R.id.ibtn_add_item_lop);

                tenKhoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                        LayoutInflater inflater1 = ((Activity) getContext()).getLayoutInflater();
                        View view2 = inflater1.inflate(R.layout.list_khoa, null);
                        builder1.setView(view2);
                        Dialog dialog1 = builder1.create();
                        dialog1.show();
                        DAO_khoa dao_khoa = new DAO_khoa(getContext());
                        ListView listView = view2.findViewById(R.id.lv_khoa_show);
                        list_khoa = dao_khoa.getAll();
                        Adapter_show_list_khoa adapterShowListKhoa = new Adapter_show_list_khoa(getContext(), list_khoa);
                        listView.setAdapter(adapterShowListKhoa);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                dto_khoa = list_khoa.get(position);
                                tenKhoa.setText(dto_khoa.getTenKhoa());
                                dialog1.dismiss();
                            }
                        });

                    }
                });

                DTO_lop dto_lop = new DTO_lop();
                DAO_lop dao_lop = new DAO_lop(getContext());
                them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dto_lop.setTenLop(tenLop.getText().toString());
                        dto_lop.setIdKhoa(dto_khoa.getId());
                        dto_lop.setSiSo(Integer.parseInt(siso.getText().toString()));
                        if (dao_lop.AddRow(dto_lop) > 0) {
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            list_lop.clear();
                            list_lop.addAll(dao_lop.getAll());
                            adapter_lop.notifyDataSetChanged();
                            dialog.dismiss();

                        } else {
                            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });

            }
        });
    }


}

