package com.huynhhn.huynhhnph35237_luyentap_crud.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.huynhhn.huynhhnph35237_luyentap_crud.DTO.DTO_khoa;
import com.huynhhn.huynhhnph35237_luyentap_crud.DTO.DTO_lop;
import com.huynhhn.huynhhnph35237_luyentap_crud.DAO.DAO_khoa;
import com.huynhhn.huynhhnph35237_luyentap_crud.DAO.DAO_lop;
import com.huynhhn.huynhhnph35237_luyentap_crud.DTO.DTO_khoa;
import com.huynhhn.huynhhnph35237_luyentap_crud.DTO.DTO_lop;
import com.huynhhn.huynhhnph35237_luyentap_crud.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Adapter_lop extends RecyclerView.Adapter<Adapter_lop.ViewHolder> implements Filterable {
    Context context;
    List<DTO_lop> list;
    DTO_khoa dto_khoa;
    List<DTO_lop> listOld;

    public Adapter_lop(Context context, List<DTO_lop> list) {
        this.context = context;
        this.list = list;
        this.listOld = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_lop, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DTO_lop dto_lop = list.get(position);
        holder.tenLop.setText(dto_lop.getTenLop());
        holder.tenKhoa.setText(dto_lop.getTenKhoa());
        holder.siSo.setText(dto_lop.getSiSo() + "");
        DAO_lop dao_lop = new DAO_lop(context);
        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Cảnh báo ");
                builder.setMessage("Bạn có muốn xóa lớp này ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dao_lop.Delete(dto_lop) > 0) {
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            list.clear();
                            list.addAll(dao_lop.getAll());
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
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

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_lop, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();

                TextView title = view.findViewById(R.id.tv_title_lop);
                title.setText("Sửa lớp");
                EditText tenLop = view.findViewById(R.id.edt_tenlop);
                EditText tenKhoa = view.findViewById(R.id.edt_ten_khoa_lop);
                EditText siso = view.findViewById(R.id.edt_siso);
                ImageButton sua = view.findViewById(R.id.ibtn_add_item_lop);
                tenLop.setText(dto_lop.getTenLop());
                tenKhoa.setText(dto_lop.getTenKhoa());
                siso.setText(dto_lop.getSiSo() + "");


                tenKhoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        androidx.appcompat.app.AlertDialog.Builder builder1 = new androidx.appcompat.app.AlertDialog.Builder(context);
                        LayoutInflater inflater1 = ((Activity) context).getLayoutInflater();
                        View view2 = inflater1.inflate(R.layout.list_khoa, null);
                        builder1.setView(view2);
                        Dialog dialog1 = builder1.create();
                        dialog1.show();
                        DAO_khoa dao_khoa = new DAO_khoa(context);
                        ListView listView = view2.findViewById(R.id.lv_khoa_show);
                        List<DTO_khoa> list_khoa = dao_khoa.getAll();
                        Adapter_show_list_khoa adapterShowListKhoa = new Adapter_show_list_khoa(context, list_khoa);
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

                sua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        dto_lop.setTenLop(tenLop.getText().toString());
                        dto_lop.setIdKhoa(dto_khoa.getId());
                        dto_lop.setSiSo(Integer.parseInt(siso.getText().toString()));

                        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                        builder1.setTitle("Cảnh báo ");
                        builder1.setMessage("Bạn có muốn lưu lại lớp chỉnh sửa này ?");


                        builder1.setPositiveButton("có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (dao_lop.UơdateRow(dto_lop) > 0) {
                                    Toast.makeText(builder1.getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                                    list.clear();
                                    list.addAll(dao_lop.getAll());
                                    notifyDataSetChanged();
                                } else {
                                    Toast.makeText(builder1.getContext(), "Sửa thất bại", Toast.LENGTH_SHORT).show();
                                }
                                dialog.dismiss();
                            }
                        });

                        builder1.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        Dialog dialog2 = builder1.create();
                        dialog2.show();
                        dialog.dismiss();
                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tenLop, tenKhoa, siSo;
        ImageButton xoa, update;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tenLop = itemView.findViewById(R.id.tv_ten_lop);
            tenKhoa = itemView.findViewById(R.id.tv_ten_khoa_lop);
            siSo = itemView.findViewById(R.id.tv_si_so);
            xoa = itemView.findViewById(R.id.ibtn_delete_lop);
            update = itemView.findViewById(R.id.ibtn_update_lop);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if (constraint.toString().isEmpty()) {
                    list = listOld;
                } else {
                    List<DTO_lop> lops = new ArrayList<>();
                    for (DTO_lop lop : listOld) {
                        if (lop.getTenLop().toLowerCase().contains(constraint.toString())) {
                            lops.add(lop);
                        }
                    }
                    list = lops;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = list;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (List<DTO_lop>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public void sapXepTangDan(){
        List<DTO_lop> lops = new ArrayList<>();
        Collections.sort(list,(a,b)->a.getTenLop().compareTo(b.getTenLop()));
        notifyDataSetChanged();
    }
    public void sapXepgiam(){
        List<DTO_lop> lops = new ArrayList<>();
        Collections.sort(list,(a,b)->b.getTenLop().compareTo(a.getTenLop()));
        notifyDataSetChanged();
    }
}
