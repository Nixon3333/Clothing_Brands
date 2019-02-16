package com.sales.lamoda;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sales.lamoda.adapters.NoSwipeViewPager;
import com.sales.lamoda.adapters.ViewPagerAdapter;
import com.sales.lamoda.fragments.AboutFragment;
import com.sales.lamoda.fragments.SalesFragment;
import com.sales.lamoda.fragments.WebFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    public static TabLayout tabLayout;
    private ViewPager viewPager;
    private NoSwipeViewPager noSwipeViewPager;
    private TabLayout.Tab tab;
    public static int counter = 0;
    private TabLayout.Tab tab0;
    private TabLayout.Tab tab1;
    private TabLayout.Tab tab2;
    private boolean bool = false;
    private boolean isFirstLaunch = true;
    private SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        prefs = getSharedPreferences(getPackageName() + "prefs", MODE_PRIVATE);
        if (prefs.contains("isFirstRun"))
            isFirstLaunch = prefs.getBoolean("isFirstRun", false);

        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //actionBar.setCustomView(R.layout.actionbar);



        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setSupportActionBar(toolbar);
            getSupportActionBar().hide();

        }

        setSupportActionBar(toolbar);

        centerTitle();

        //viewPager = (ViewPager) findViewById(R.id.viewpager);
        noSwipeViewPager = findViewById(R.id.noSwipeViewPager);

        noSwipeViewPager.setPagingEnabled(false);

        //setupViewPager(viewPager);
        setupViewPager(noSwipeViewPager);




        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(noSwipeViewPager);

        tab0 = tabLayout.getTabAt(0);
        tab1 = tabLayout.getTabAt(1);

        if (isFirstLaunch) {
            isFirstLaunch = false;
            prefs = getSharedPreferences(getPackageName() + "prefs", MODE_PRIVATE);
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean("isFirstRun" , isFirstLaunch);
            edit.apply();
        } else {
            tab1.select();
        }
        /*tabLayout.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(noSwipeViewPager) {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        int position = tab.getPosition();
                        noSwipeViewPager.setCurrentItem(position);

                        tab0 = tabLayout.getTabAt(0);
                        tab1 = tabLayout.getTabAt(1);
                        tab2 = tabLayout.getTabAt(2);
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
                    }
                });*/
    }

    public void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SalesFragment(), "Скидки");
        adapter.addFragment(new WebFragment(), "Магазин");
        adapter.addFragment(new AboutFragment(), "О приложении");
        viewPager.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        int currentPosition = noSwipeViewPager.getCurrentItem();


        if (currentPosition == 1) {
            if (WebFragment.mWebView.canGoBack())
                WebFragment.mWebView.goBack();
            else {
                noSwipeViewPager.setCurrentItem(currentPosition - 1);
            }
        }


        if (currentPosition == 2)
            noSwipeViewPager.setCurrentItem(currentPosition - 1);


        if (currentPosition == 0) {
            if (bool) {
                super.onBackPressed();
                return;
            }
            this.bool = true;
            Toast.makeText(this, R.string.backString, Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    bool = false;
                }
            }, 2000);
        }
    }


    private void centerTitle() {
        ArrayList<View> textViews = new ArrayList<>();

        getWindow().getDecorView().findViewsWithText(textViews, getTitle(), View.FIND_VIEWS_WITH_TEXT);

        if (textViews.size() > 0) {
            AppCompatTextView appCompatTextView = null;
            if (textViews.size() == 1) {
                appCompatTextView = (AppCompatTextView) textViews.get(0);
            } else {
                for (View v : textViews) {
                    if (v.getParent() instanceof Toolbar) {
                        appCompatTextView = (AppCompatTextView) v;
                        break;
                    }
                }
            }

            if (appCompatTextView != null) {
                ViewGroup.LayoutParams params = appCompatTextView.getLayoutParams();
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                appCompatTextView.setLayoutParams(params);
                appCompatTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
        }
    }
}

