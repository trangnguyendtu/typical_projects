package com.midterm.selfhelpguide.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.midterm.selfhelpguide.R;

import java.util.List;

public class CategoryGridAdapter extends BaseAdapter {
    private Context context;
    private List<String> categories;
    private ViewPager viewPager;

    public CategoryGridAdapter(Context context, List<String> categories, ViewPager viewPager) {
        this.context = context;
        this.categories = categories;
        this.viewPager = viewPager;
    }
    Integer[] icons = {R.drawable.ic_information, R.drawable.ic_lungs,
            R.drawable.ic_first_aid_kit, R.drawable.ic_stay_home, R.drawable.ic_food,R.drawable.ic_travel};
//            R.drawable.ic_travel, R.drawable.ic_user, R.drawable.ic_question};

    AutoCompleteTextView txtAutoComplete;

    String[] brief_intro = {"General information about COVID-19.",
            "Symptoms of COVID-19 ranging from mild to severe.",
            "Know how the virus spreads and take steps to protect yourself.",
            "Things to do while social distancing and self quarantining.",
            "Foods can boost the immune system to against virus.",
            "Travelling during the pandemic - is it a should?"};
//            "More information about our work - motivation and goal.",
//            "Common questions about the COVID-19 outbreak."};

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.category_grid_layout, null);

        TextView textView = (TextView) view.findViewById(R.id.gridview_text);
        TextView textView1 = (TextView) view.findViewById(R.id.brief_intro);
        ImageView imageView = (ImageView) view.findViewById(R.id.gridImageView);

        textView.setText(categories.get(position));
        textView1.setText(brief_intro[position]);
        imageView.setImageResource(icons[position]);

        switch (position) {
            case 0:
                //
                break;
            case 1:
                //
                break;
            case 2:
                //
                break;
            case 3:
                //
                break;
            case 4:
                //
                break;
            case 5:
                //
                break;
//            case 6:
//                //
//                break;
//            case 7:
//                //
//                break;
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(position + 1);
            }
        });
        return view;
    }

}
