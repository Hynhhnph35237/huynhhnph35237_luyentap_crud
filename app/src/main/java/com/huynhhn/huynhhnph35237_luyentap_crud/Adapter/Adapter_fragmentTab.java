package com.huynhhn.huynhhnph35237_luyentap_crud.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.huynhhn.huynhhnph35237_luyentap_crud.fragment.Frag_Khoa;
import com.huynhhn.huynhhnph35237_luyentap_crud.fragment.Frag_Lop;


public class Adapter_fragmentTab extends FragmentStateAdapter {
    int page = 2;

    public Adapter_fragmentTab(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Frag_Khoa();
            case 1:
                return new Frag_Lop();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return page;
    }
}
