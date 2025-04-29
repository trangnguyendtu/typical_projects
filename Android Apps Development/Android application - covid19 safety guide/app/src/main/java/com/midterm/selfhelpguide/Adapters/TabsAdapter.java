package com.midterm.selfhelpguide.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.midterm.selfhelpguide.Fragments.CategoryFragment;
import com.midterm.selfhelpguide.Fragments.ContentFragment;
import com.google.android.material.tabs.TabLayout;

public class TabsAdapter extends FragmentStatePagerAdapter {
    private int numOfTabs;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    public TabsAdapter(FragmentManager fm, int numOfTabs, final ViewPager viewPager, TabLayout tabLayout) {
        super(fm);
        this.numOfTabs = numOfTabs;
        this.viewPager = viewPager;
        this.tabLayout = tabLayout;

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            CategoryFragment categoryFragment = new CategoryFragment(viewPager);
            return categoryFragment;
        } else {
            ContentFragment contentFragment = new ContentFragment(position);
            return contentFragment;
        }


    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
