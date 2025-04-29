package com.midterm.selfhelpguide.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.midterm.selfhelpguide.CategoryIntro;
import com.midterm.selfhelpguide.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ContentFragment extends Fragment{
    private int tabsNo;
    private TextView nameCat;
    private TextView contentCat;
    private Button bt_moreInfo;
    private ImageView imageView;
    private ImageView iconCat;

    public CategoryIntro categoryIntro;

    public AssetManager getAssets(){
        return getAssets();
    }

    public String loadData(String filename, String text) {
        try {
            InputStream inputStream = getAssets().open(filename);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            text = new String(buffer);
        } catch (IOException e){
            e.printStackTrace();
        }
        return text;
    }

    public ContentFragment(int tabsNo) {
        this.tabsNo = tabsNo;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_content, container, false);

        nameCat = (TextView) view.findViewById(R.id.name_category);
        contentCat = (TextView) view.findViewById(R.id.content_category);
        iconCat = (ImageView) view.findViewById(R.id.image_category);
        bt_moreInfo = (Button) view.findViewById(R.id.link_tv);

        List<CategoryIntro> categoryIntros = new ArrayList<>();
        switch (tabsNo) {
            case 1:
                nameCat.setText(categoryName[0]);
                contentCat.setText(categorytContent[0]);
                iconCat.setImageResource(R.drawable.icon_info_info);
                break;
            case 2:
                nameCat.setText(categoryName[1]);
                contentCat.setText(categorytContent[1]);
                iconCat.setImageResource(R.drawable.ic_info_lung);

                break;
            case 3:
                nameCat.setText(categoryName[2]);
                contentCat.setText(categorytContent[2]);
                iconCat.setImageResource(R.drawable.ic_info_healthcare_medical);
                break;
            case 4:
                nameCat.setText(categoryName[3]);
                contentCat.setText(categorytContent[3]);
                iconCat.setImageResource(R.drawable.ic_info_home);
                break;
            case 5:
                nameCat.setText(categoryName[4]);
                contentCat.setText(categorytContent[4]);
                iconCat.setImageResource(R.drawable.ic_info_basket);
                break;
            case 6:
                nameCat.setText(categoryName[5]);
                contentCat.setText(categorytContent[5]);
                iconCat.setImageResource(R.drawable.ic_info_travel);
                break;
//            case 7:
//                //
//                break;
//            case 8:
//                //
//                break;
        }

        for(int i=1; i<7;i++) {
            String link = "";
            switch (i){
                case 1:
                    link = "cdc.gov/coronavirus/2019-ncov/index.html";
                case 2:
                    link = "https://www.cdc.gov/coronavirus/2019-ncov/symptoms-testing/symptoms.html";
                case 3:
                    link = "https://www.cdc.gov/coronavirus/2019-ncov/prevent-getting-sick/prevention.html";
                case 4:
                    link = "https://www.euro.who.int/en/health-topics/health-emergencies/coronavirus-covid-19/technical-guidance/stay-physically-active-during-self-quarantine";
                case 5:
                    link = "https://www.euro.who.int/en/health-topics/health-emergencies/coronavirus-covid-19/technical-guidance/food-and-nutrition-tips-during-self-quarantine?fbclid=IwAR0IxmHZqgX-uwgq0cNTsDM3BdHUogV8EcFbqiY3olALGzBP_hbzW6AwYnA";
                case 6:
                    link = "https://www.cdc.gov/coronavirus/2019-ncov/travelers/travel-during-covid19.html";
            }
            final String finalLink = link;
            bt_moreInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse(finalLink);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });
        }
        return view;
    }


    public String[] categoryName = {"Information about COVID-19",
            "COVID-19 Infective Symptoms",
            "COVID-19 Transmission and \nProtective Measures",
            "Stay Physically Active During \nSelf-quarantine",
            "Food and Nutrition Tips \nDuring Self-quarantine",
            "Travel During the COVID-19 \nPandemic"};

    public String[] categorytContent = {"• Coronavirus (COVID-19) is an illness caused by a virus that can spread from person to person.\n\n• The virus that causes COVID-19 is a new coronavirus that has spread throughout the world.\n\n• COVID-19 symptoms can range from mild (or no symptoms) to severe illness.\n\nAll credit for the guides in this app goes to WHO and CDC.",
            "People with COVID-19 have had a wide range of symptoms reported – ranging from mild symptoms to severe illness. Symptoms may appear 2-14 days after exposure to the virus. People with these symptoms may have COVID-19:\n\n• Fever or chills\n• Cough\n• Shortness of breath or difficulty breathing\n• Fatigue\n• Muscle or body aches\n• Headache\n• New loss of taste or smell\n• Sore throat\n• Congestion or runny nose\n• Nausea or vomiting\n• Diarrhea",
            "COVID-19 spreads primarily from person to person. Fighting this disease is our joint responsibility.\n\n• Stay home as much as possible and avoid close contact with others.\n\n• Wear a mask that covers your nose and mouth in public settings.\n\n• Clean and disinfect frequently touched surfaces.\n\n• Wash your hands often with soap and water for at least 20 seconds, or use an alcoholbased hand sanitizer that contains at least 60% alcohol.",
            "Self-quarantine can also cause additional stress and challenge the mental health of citizens.\n\n• Physical activity and relaxation techniques can be valuable tools to help you remain calm and continue to protect your health during this time.\n\n• Buy groceries and medicine, go to the doctor, and complete banking activities online when possible.\n\n• Get deliveries and takeout, and limit in-person contact as much as possible",
            "• Good nutrition is crucial for health, particularly in times when the immune system might need to fight back.\n\n• Limited access to fresh foods may compromise opportunities to continue eating a healthy and varied diet.\n\n• It can also potentially lead to an increased consumption of highly processed foods, which tend to be high in fats, sugars and salt.\n\n• Nonetheless, even with few and limited ingredients, one can continue eating a diet that supports good health.",
            "• Travel increases your chance of getting and spreading COVID-19.\n\n• You can get COVID-19 during your travels. You may feel well and not have any symptoms, but you can still spread COVID-19 to others.\n\n• Don’t travel if you are sick or if you have been around someone with COVID-19 in the past 14 days.\n\n• Don’t travel with someone who is sick.\n\n• Staying home is the best way to protect yourself and others from COVID-19."};


}
