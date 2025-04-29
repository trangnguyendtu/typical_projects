package com.midterm.selfhelpguide.Fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.midterm.selfhelpguide.Adapters.TabsAdapter;
import com.midterm.selfhelpguide.R;
import com.google.android.material.tabs.TabLayout;

public class SafetyTipsFragment extends Fragment {
    public SafetyTipsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_safety_tips, container, false);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);

        for (int i = 0; i < 7; i++) {

            String tabTitle = "";

            switch (i) {
                case 0:
                    tabTitle = "Home";
                    break;
                case 1:
                    tabTitle = "Information";
                    break;
                case 2:
                    tabTitle = "Symptoms";
                    break;
                case 3:
                    tabTitle = "Protect Yourself";
                    break;
                case 4:
                    tabTitle = "Indoor";
                    break;
                case 5:
                    tabTitle = "Food Safety";
                    break;
                case 6:
                    tabTitle = "Travel Safety";
                    break;
//                case 7:
//                    tabTitle = "About us";
//                    break;
//                case 8:
//                    tabTitle = "FAQs";
//                    break;
            }
            tabLayout.addTab(tabLayout.newTab().setText(tabTitle));
        }

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        TabsAdapter tabsAdapter = new TabsAdapter(getChildFragmentManager(), tabLayout.getTabCount(), viewPager, tabLayout);
        viewPager.setAdapter(tabsAdapter);

        return view;
    }
}
