package com.midterm.selfhelpguide.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.midterm.selfhelpguide.Adapters.CategoryGridAdapter;
import com.midterm.selfhelpguide.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {
    private GridView gridView;
    private CategoryGridAdapter categoryGridAdapter;
    private ViewPager viewPager;

    public CategoryFragment(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        gridView = (GridView) view.findViewById(R.id.gridView);
        List<String> strings = new ArrayList<String>();
        for (int i = 0; i < 6; i++) {
            String title = "";
            switch (i) {
                case 0:
                    title = "Information";
                    break;
                case 1:
                    title = "Symptoms";
                    break;
                case 2:
                    title = "Protect yourself";
                    break;
                case 3:
                    title = "Indoor";
                    break;
                case 4:
                    title = "Food safety";
                    break;
                case 5:
                    title = "Travel safety";
                    break;
//                case 6:
//                    title = "About us";
//                    break;
//                case 7:
//                    title = "FAQs";
//                    break;
            }
            strings.add(title);
        }
        categoryGridAdapter = new CategoryGridAdapter(this.getContext(), strings, viewPager);
        gridView.setAdapter(categoryGridAdapter);

        return view;
    }
}
