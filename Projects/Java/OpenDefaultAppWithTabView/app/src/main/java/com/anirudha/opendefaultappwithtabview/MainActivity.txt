package com.anirudha.opendefaultappwithtabview;

// MainActivity.java
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    private static final int[] TAB_ICONS = {
            R.drawable.camera,
            R.drawable.message,
            R.drawable.contacts
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new MyPagerAdapter(this));

        TabLayout tabLayout = findViewById(R.id.tabLayout);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            tab.setIcon(TAB_ICONS[position]);
            switch (position) {
                case 0:
                    tab.setText("Camera");
                    break;
                case 1:
                    tab.setText("Messages");
                    break;
                case 2:
                    tab.setText("Contacts");
                    break;
            }
        }).attach();
    }
}