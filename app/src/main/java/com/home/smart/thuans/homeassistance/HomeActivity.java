package com.home.smart.thuans.homeassistance;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import layout.ChatFragment;
import layout.ControlBoardFragment;
import layout.HouseModeFragment;

public class HomeActivity extends AppCompatActivity {
    private static int Flags = -1;
    private ChatFragment chatFragment;
    private static final String TAG = "HomeActivity";
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        if (getIntent().getIntExtra("noti",0) == 111111) {
//            int noti = savedInstanceState.getInt("noti");
//            Log.d("HomeActivity", "onCreateView: " + noti);
//            if (noti == 111111) {
//                Log.d("HomeActivity", "onCreateView: Noti");
//
//            }
//        }



//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        onNewIntent(getIntent());

    }

    @Override
    public void onNewIntent(Intent intent){
        Bundle extras = intent.getExtras();
        if(extras != null){
            Flags = intent.getExtras().getInt("noti");
            Log.d("aaaa", "FLAGSL " + Flags);
            chatFragment = ChatFragment.getInstance();
            chatFragment.setFlag(111111);
        }


    }
//
//    @Override
//    protected void onNewIntent(Intent intent) {
////        super.onNewIntent(intent);
////        this.setIntent(intent);
//        int noti = intent.getIntExtra("noti",0);
//        Log.d("HomeActivity", "onCreateView: " + noti);
//        if (noti == 111111) {
//            Log.d("HomeActivity", "onCreateView: Noti");
//
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    chatFragment = ChatFragment.getInstance();
                    return chatFragment;
                case 1:
                    ControlBoardFragment controlBoardFragment = new ControlBoardFragment();
                    return controlBoardFragment;
                case 2:
                    HouseModeFragment houseModeFragment = new HouseModeFragment();
                    return houseModeFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Giúp việc";
                case 1:
                    return "Công tắc";
                case 2:
                    return "Chế độ";
            }
            return null;
        }
    }
}
