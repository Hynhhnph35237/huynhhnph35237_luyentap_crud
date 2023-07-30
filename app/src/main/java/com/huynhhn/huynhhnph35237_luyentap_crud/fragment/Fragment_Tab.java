package com.huynhhn.huynhhnph35237_luyentap_crud.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.huynhhn.huynhhnph35237_luyentap_crud.Adapter.Adapter_fragmentTab;
import com.huynhhn.huynhhnph35237_luyentap_crud.R;

public class Fragment_Tab extends Fragment {
    Adapter_fragmentTab tab;
    ViewPager2 pager2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tab = new Adapter_fragmentTab(this);
        pager2 = view.findViewById(R.id.page);
        pager2.setAdapter(tab);

        TabLayout tabLayout = view.findViewById(R.id.tab);
        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, pager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Khoa");
                        tab.setIcon(R.drawable.baseline_school_24);
                        break;
                    case 1:
                        tab.setText("Lớp");
                        tab.setIcon(R.drawable.baseline_class_24);
                        break;
                }
            }
        });
        mediator.attach();
    }
}
