package com.example.android.cambridgetrails;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * {@link CategoryAdapter} is a {@link FragmentPagerAdapter} that can provide the layout for
 * each list item based on a data source which is a list of {@link Site} objects - based on Miwok Application
 */
public class CategoryAdapter extends FragmentPagerAdapter {


    /** Context of the app */
    private Context mContext;

    /**
     * Create a new {@link CategoryAdapter} object.
     *
     * @param fm is the fragment manager that will keep each fragment's state in the adapter
     *           across swipes.
     */
    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    /**
     * Return the {@link Fragment} that should be displayed for the given page number.
     */
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new PeopleFragment();
        } else if (position == 1) {
            return new TimelineFragment();
        } else if (position == 2) {
            return new MapFragment();
        } else {
            return new HistoryFragment();
        }
    }

    /**
     * Return the total number of pages.
     */
    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.category_people);
        } else if (position == 1) {
            return mContext.getString(R.string.category_timeline);
        } else if (position == 2) {
            return mContext.getString(R.string.category_map);
        } else {
            return mContext.getString(R.string.category_history);
        }
    }
}
