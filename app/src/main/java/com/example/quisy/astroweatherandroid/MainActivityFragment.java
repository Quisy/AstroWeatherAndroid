package com.example.quisy.astroweatherandroid;

import android.app.Activity;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MainActivityFragment extends Fragment {

    private static final int NUM_PAGES = 4;
    private static final int NUM_PAGES_LANDSCAPE = 2;

    private FragmentActivity myContext;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        int orientation = this.getActivity().getResources().getConfiguration().orientation;

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) view.findViewById(R.id.pager);

        if (orientation == Configuration.ORIENTATION_PORTRAIT)
            mPagerAdapter = new ScreenSlidePagerAdapter(myContext.getSupportFragmentManager());
        else
            mPagerAdapter = new ScreenSlidePagerAdapterLandscape(myContext.getSupportFragmentManager());

        mPager.setAdapter(mPagerAdapter);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new WeatherPrimaryFragment();
            } else if (position == 1) {
                return new WeatherAdditionalFragment();
            } else if (position == 2) {
                return new SunInfoFragment();
            } else {
                return new MoonInfoFragment();
            }

        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    private class ScreenSlidePagerAdapterLandscape extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapterLandscape(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new WeatherInfoFragment();
            } else {
                return new AstroInfoFragment();
            }

        }

        @Override
        public int getCount() {
            return NUM_PAGES_LANDSCAPE;
        }
    }
}
