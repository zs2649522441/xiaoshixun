package com.example.lx;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import fragment.Afragment;
import fragment.Bfragment;
import fragment.Cfragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private FrameLayout mFram;
    private TabLayout mTab;
    private LinearLayout mLin;
    private DrawerLayout mDraw;
    private NavigationView mNv;
    private FragmentManager manager;
    private Afragment afragment;
    private Bfragment bfragment;
    private Cfragment cfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mFram = (FrameLayout) findViewById(R.id.fram);
        mTab = (TabLayout) findViewById(R.id.tab);
        mLin = (LinearLayout) findViewById(R.id.lin);
        mNv = (NavigationView) findViewById(R.id.nv);
        mDraw = (DrawerLayout) findViewById(R.id.draw);
        mToolbar.setTitle("主页");

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDraw, mToolbar, R.string.app_name, R.string.app_name);
        actionBarDrawerToggle.syncState();

        mDraw.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                int right = mNv.getRight();
                mLin.setX(right);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        manager = getSupportFragmentManager();
        afragment = new Afragment();
        bfragment = new Bfragment();
        cfragment = new Cfragment();

        manager.beginTransaction().add(R.id.fram,afragment).add(R.id.fram,bfragment).add(R.id.fram,cfragment).hide(bfragment).hide(cfragment
        ).commit();

        mTab.addTab(mTab.newTab().setText("主页"));
        mTab.addTab(mTab.newTab().setText("公众号"));
        mTab.addTab(mTab.newTab().setText("我的"));

        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        manager.beginTransaction().show(afragment).hide(bfragment).hide(cfragment).commit();
                        break;case 1:
                        manager.beginTransaction().show(bfragment).hide(afragment).hide(cfragment).commit();
                        break;case 2:
                        manager.beginTransaction().show(cfragment).hide(bfragment).hide(afragment).commit();
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
