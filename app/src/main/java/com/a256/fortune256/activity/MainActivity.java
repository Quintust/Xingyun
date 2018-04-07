package com.a256.fortune256.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;

import com.a256.fortune256.viewer.HomePage;
import com.a256.fortune256.viewer.MinePage;
import com.a256.fortune256.adapter.MyViewPagerAdapter;
import com.a256.fortune256.R;
import com.a256.fortune256.viewer.NearPage;
import com.a256.fortune256.viewer.TendPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by de165 on 2018/1/27.
 */

public class MainActivity extends Activity implements View.OnClickListener{
    private RadioButton home;
    private RadioButton tend;
    private RadioButton near;
    private RadioButton mine;
    private ViewPager center;
    private List<View> viewList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void resetBottom(){
        home.setBackgroundColor(getResources().getColor(R.color.white));
        tend.setBackgroundColor(getResources().getColor(R.color.white));
        near.setBackgroundColor(getResources().getColor(R.color.white));
        mine.setBackgroundColor(getResources().getColor(R.color.white));

        home.setTextColor(getResources().getColor(R.color.black));
        tend.setTextColor(getResources().getColor(R.color.black));
        near.setTextColor(getResources().getColor(R.color.black));
        mine.setTextColor(getResources().getColor(R.color.black));

    }

    private void initView(){
        home = findViewById(R.id.home);
        tend = findViewById(R.id.tend);
        near = findViewById(R.id.near);
        mine = findViewById(R.id.mine);

        home.setOnClickListener(this);
        tend.setOnClickListener(this);
        near.setOnClickListener(this);
        mine.setOnClickListener(this);

        // ViewPage 初始化
        center = findViewById(R.id.main_viewpage);

        View v1 = new HomePage().init(this);
        View v2 = new TendPage().init(this);
        View v3 = new NearPage().init(this);
        View v4 = new MinePage().init(this);
        viewList = new ArrayList<View>();
        viewList.add(v1);
        viewList.add(v2);
        viewList.add(v3);
        viewList.add(v4);
        center.setAdapter(new MyViewPagerAdapter(viewList));
        center.setCurrentItem(0);
        center.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        onHomeClick();
                        break;
                    case 1:
                        onTendClick();
                        break;
                    case 2:
                        onNearClick();
                        break;
                    case 3:
                        onMineClick();
                        break;
                    default:break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.home:
                onHomeClick();
                break;
            case R.id.tend:
                onTendClick();
                break;
            case R.id.near:
                onNearClick();
                break;
            case R.id.mine:
                onMineClick();
                break;
            default:break;
        }
    }

    private void onHomeClick(){
        center.setCurrentItem(0);
        resetBottom();
        home.setBackgroundColor(getResources().getColor(R.color.bgmain));
        home.setTextColor(getResources().getColor(R.color.white));
    }

    private void onTendClick(){
        center.setCurrentItem(1);
        resetBottom();
        tend.setBackgroundColor(getResources().getColor(R.color.bgmain));
        tend.setTextColor(getResources().getColor(R.color.white));
    }

    private void onNearClick(){
        center.setCurrentItem(2);
        resetBottom();
        near.setBackgroundColor(getResources().getColor(R.color.bgmain));
        near.setTextColor(getResources().getColor(R.color.white));
    }

    private void onMineClick(){
        center.setCurrentItem(3);
        resetBottom();
        mine.setBackgroundColor(getResources().getColor(R.color.bgmain));
        mine.setTextColor(getResources().getColor(R.color.white));
    }
}
