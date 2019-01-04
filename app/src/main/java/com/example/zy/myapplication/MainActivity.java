package com.example.zy.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private List<Fragment> tabFragments;

    private List<ShadeView> tabIndicators;

    private ViewPager viewPager;

    private FragmentPagerAdapter adapter;

    //创建通讯录联系人


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //显示联系人

        initData();
        viewPager = (ViewPager) findViewById(R.id.id_viewpager);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
    }

    private void initData() {
        tabFragments = new ArrayList<>();
        tabIndicators = new ArrayList<>();


            TabFragment tabFragment = new TabFragment();
            TabFragment1 tabFragment1 = new TabFragment1();

            TabFragment3 tabFragment3 = new TabFragment3();



            tabFragments.add(tabFragment);
            tabFragments.add(tabFragment1);

            tabFragments.add(tabFragment3);

        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return tabFragments.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return tabFragments.get(arg0);
            }
        };
        initTabIndicator();
    }

    private void initTabIndicator() {
        ShadeView one = (ShadeView) findViewById(R.id.id_indicator_one);
        ShadeView two = (ShadeView) findViewById(R.id.id_indicator_two);

        ShadeView four = (ShadeView) findViewById(R.id.id_indicator_four);
        tabIndicators.add(one);
        tabIndicators.add(two);

        tabIndicators.add(four);
        one.setOnClickListener(this);
        two.setOnClickListener(this);

        four.setOnClickListener(this);
        one.setIconAlpha(1.0f);
    }

    @Override
    public void onClick(View v) {
        resetTabsStatus();
        switch (v.getId()) {
            case R.id.id_indicator_one:
//                getRequest();
                tabIndicators.get(0).setIconAlpha(1.0f);
                viewPager.setCurrentItem(0, false);
                break;
            case R.id.id_indicator_two:

                tabIndicators.get(1).setIconAlpha(1.0f);
                viewPager.setCurrentItem(1, false);
                break;
            case R.id.id_indicator_four:
                tabIndicators.get(2).setIconAlpha(1.0f);
                viewPager.setCurrentItem(2, false);
                break;

        }
    }

    /**
     * 重置Tab状态
     */
    private void resetTabsStatus() {
        for (int i = 0; i < tabIndicators.size(); i++) {
            tabIndicators.get(i).setIconAlpha(0);
        }
    }

    /**
     * 如果是直接点击图标来跳转页面的话，position值为0到3，positionOffset一直为0.0
     * 如果是通过滑动来跳转页面的话
     * 假如是从第一页滑动到第二页
     * 在这个过程中，positionOffset从接近0逐渐增大到接近1.0，滑动完成后又恢复到0.0，而position只有在滑动完成后才从0变为1
     * 假如是从第二页滑动到第一页
     * 在这个过程中，positionOffset从接近1.0逐渐减小到0.0，而position一直是0
     *
     * @param position             当前页面索引
     * @param positionOffset       偏移量
     * @param positionOffsetPixels 偏移量
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.e("TAG", "position==" + position);
        Log.e("TAG", "positionOffset==" + positionOffset);
        Log.e("TAG", "positionOffsetPixels==" + positionOffsetPixels);

        if (positionOffset > 0) {

            ShadeView leftTab = tabIndicators.get(position);

            ShadeView rightTab = tabIndicators.get(position + 1);
            leftTab.setIconAlpha(1 - positionOffset);
            rightTab.setIconAlpha(positionOffset);

        }
    }

    @Override
    public void onPageSelected(int position) {
        if (position == 2) {

            tabIndicators.get(position).setIconBitmap(this, R.drawable.me);
        } else {
            tabIndicators.get(2).setIconBitmap(this, R.drawable.me);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
