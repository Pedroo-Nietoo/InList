package com.example.meuapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    ViewPager2 pager;
    TabLayout tab;
    AdaptadorPager adapterPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(Color.rgb(76, 175, 80));

        pager = findViewById(R.id.vp);
        tab = findViewById(R.id.tl);

        adapterPager = new AdaptadorPager(getSupportFragmentManager(), getLifecycle());
        pager.setAdapter(adapterPager);

        tab.addTab(tab.newTab().setText("Cadastro"));
        tab.addTab(tab.newTab().setText("Lista"));
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tab.selectTab(tab.getTabAt(position));
            }
        });
    }
}