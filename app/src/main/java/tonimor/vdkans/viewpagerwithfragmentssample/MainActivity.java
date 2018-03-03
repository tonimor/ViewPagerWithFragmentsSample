package tonimor.vdkans.viewpagerwithfragmentssample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import com.viewpagerindicator.TitlePageIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    /**
     * The pager widget, which handles animation and allows swiping horizontally
     * to access previous and next pages.
     */
    ViewPager pager = null;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    MyFragmentPagerAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);


        // Instantiate a ViewPager
        this.pager = (ViewPager) this.findViewById(R.id.pager);

        // Set a custom animation
        // this.pager.setPageTransformer(true, new ZoomOutPageTransformer());

        // Create an adapter with the fragments we show on the ViewPager
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(ScreenSlidePageFragment.newInstance(
                ContextCompat.getColor(this, R.color.android_blue), 1));

        adapter.addFragment(ScreenSlidePageFragment.newInstance(
                ContextCompat.getColor(this, R.color.android_purple), 2));

        adapter.addFragment(ScreenSlidePageFragment.newInstance(
                ContextCompat.getColor(this, R.color.android_green), 3));

        adapter.addFragment(ScreenSlidePageFragment.newInstance(
                ContextCompat.getColor(this, R.color.android_yellow), 4));

        adapter.addFragment(ScreenSlidePageFragment.newInstance(
                ContextCompat.getColor(this, R.color.android_red), 5));

        this.pager.setAdapter(adapter);

        // Bind the title indicator to the adapter
        TitlePageIndicator titleIndicator = (TitlePageIndicator) findViewById(R.id.indicator);
        titleIndicator.setViewPager(pager);
        titleIndicator.setTextColor(ContextCompat.getColor(this, R.color.black));
        titleIndicator.setSelectedColor(ContextCompat.getColor(this, R.color.android_darkred));
    }

    @Override
    public void onBackPressed() {

        // Return to previous page when we press back button
        if (this.pager.getCurrentItem() == 0)
            super.onBackPressed();
        else
            this.pager.setCurrentItem(this.pager.getCurrentItem() - 1);

    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////
// FragmentPagerAdapter


    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        // List of fragments which are going to set in the view pager widget
        List<Fragment> fragments;

        /**
         * Constructor
         *
         * @param fm
         *            interface for interacting with Fragment objects inside of an
         *            Activity
         */
        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
            this.fragments = new ArrayList<Fragment>();
        }

        /**
         * Add a new fragment in the list.
         *
         * @param fragment
         *            a new fragment
         */
        public void addFragment(Fragment fragment) {
            this.fragments.add(fragment);
        }

        @Override
        public Fragment getItem(int arg0) {
            return this.fragments.get(arg0);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Página " + (position + 1);
        }
    }

}
