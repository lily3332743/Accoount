package com.example.account;


import android.graphics.Typeface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements BottomNavigationBar.OnTabSelectedListener,addAccountFragment.OnFragmentInteractionListener,
        detailFragment.OnFragmentInteractionListener,AnalysisFragment.OnFragmentInteractionListener,societyFragment.OnFragmentInteractionListener {

    private BottomNavigationBar mBottomNavigationBar;
    private DrawerLayout mDrawerLayout;

    private String[] mTitles = new String[]{"记账", "明细", "分析", "社区"};




    private int currentIndex=0;//记载现在导航栏位于哪一个页面



    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);//左侧弹出框
        NavigationView navView=(NavigationView)findViewById(R.id.nav_view);


        navView.setCheckedItem(R.id.nav_foucs);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            //左侧弹出框item的点击事件
            public boolean onNavigationItemSelected( MenuItem item) {

                return true;
            }
        });



        mBottomNavigationBar = (BottomNavigationBar)findViewById(R.id.bottomNavigationBar);//底部导航栏


        intview();//初始化工作的进行


        addAccountFragment defaultFragment=new addAccountFragment();
        replaceFragment(defaultFragment);//默认载入记账fragment

    }


    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_content,fragment);
        transaction.commit();
    }


    private void intview() {

        mBottomNavigationBar
                .setActiveColor(R.color.GoldEnrod)
                .setInActiveColor(R.color.black)
                .setBarBackgroundColor(R.color.white);



        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);


        mBottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );


        TextBadgeItem numberBadgeItem = new TextBadgeItem();


        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_tabbar_home_pressed, mTitles[0])

                        .setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.ic_tabbar_home_normal)))



                .addItem(new BottomNavigationItem(R.mipmap.ic_tabbar_discover_pressed, mTitles[1])
                        .setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.ic_tabbar_discover_normal)))

                .addItem(new BottomNavigationItem(R.mipmap.ic_tabbar_feed_pressed, mTitles[2])
                        .setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.ic_tabbar_feed_normal)));

               mBottomNavigationBar .addItem(new BottomNavigationItem(R.mipmap.ic_tabbar_me_pressed, mTitles[3])

                        .setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.ic_tabbar_me_normal))
                       .setActiveColorResource(R.color.orange).setBadgeItem(numberBadgeItem));

        numberBadgeItem.setText("10");//更新数据,就是社区标记右上角那个红色的提示框

                mBottomNavigationBar.setFirstSelectedPosition(0)

                .initialise();


        //点击底部导航栏切换不同的Fragment
        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                if(currentIndex!=position) {
                    switch (position) {
                        case 0:
                            addAccountFragment fragemnt_add=new addAccountFragment();
                            replaceFragment(fragemnt_add);
                            currentIndex=position;
                            break;
                        case 1:
                            detailFragment fragemnt_detail=new detailFragment();
                            replaceFragment(fragemnt_detail);
                            currentIndex=position;
                            break;
                        case 2:
                            AnalysisFragment fragemnt_analysis=new AnalysisFragment();
                            replaceFragment(fragemnt_analysis);
                            currentIndex=position;
                            break;
                        case 3:
                            societyFragment fragemnt_society=new societyFragment();
                            replaceFragment(fragemnt_society);
                            currentIndex=position;
                            break;
                    }
                }

            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

    }



    //对于位于toolbar位置的item的点击事件的处理
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.cancel:
                //进行返回到上一个浏览页面的操作
                Toast.makeText(this,"你点击了取消",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }




    public void onTabSelected(int position) {

    }


    @Override
    public void onTabUnselected(int position) {

    }


    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}

