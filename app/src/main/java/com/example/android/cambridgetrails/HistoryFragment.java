package com.example.android.cambridgetrails;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.site_list, container, false);

        // Create a arraylist of Site documents for History fragment
        final ArrayList<Site> sites = new ArrayList<Site>();
        sites.add(new Site(getString(R.string.title1), getString(R.string.name1), R.drawable.baldwin2));
        sites.add(new Site(getString(R.string.title2), getString(R.string.name2), R.drawable.brownc2));
        sites.add(new Site(getString(R.string.title3), getString(R.string.name3), R.drawable.brownw2));
        sites.add(new Site(getString(R.string.title4), getString(R.string.name4), R.drawable.clark2));
        sites.add(new Site(getString(R.string.title5), getString(R.string.name5), R.drawable.dubois2));
        sites.add(new Site(getString(R.string.title6), getString(R.string.name6), R.drawable.duckrey2));
        sites.add(new Site(getString(R.string.title7), getString(R.string.name7), R.drawable.fatal2));
        sites.add(new Site(getString(R.string.title8), getString(R.string.name8), R.drawable.greener2));
        sites.add(new Site(getString(R.string.title9), getString(R.string.name9), R.drawable.hopkins2));
        sites.add(new Site(getString(R.string.title10), getString(R.string.name10), R.drawable.jacobs2));
        sites.add(new Site(getString(R.string.title11), getString(R.string.name11), R.drawable.lane2));
        sites.add(new Site(getString(R.string.title12), getString(R.string.name12), R.drawable.lewis2));
        sites.add(new Site(getString(R.string.title13), getString(R.string.name13), R.drawable.mcguire2));
        sites.add(new Site(getString(R.string.title14), getString(R.string.name14), R.drawable.morgan2));
        sites.add(new Site(getString(R.string.title15), getString(R.string.name15), R.drawable.morris2));
        sites.add(new Site(getString(R.string.title16), getString(R.string.name16), R.drawable.raymond2));
        sites.add(new Site(getString(R.string.title17), getString(R.string.name17), R.drawable.scott2));
        sites.add(new Site(getString(R.string.title18), getString(R.string.name18), R.drawable.smith2));
        sites.add(new Site(getString(R.string.title19), getString(R.string.name19), R.drawable.stanford2));
        sites.add(new Site(getString(R.string.title20), getString(R.string.name20), R.drawable.wright2));


        // Create an {@link SiteAdapter}, whose data source is a list of {@link Site}s. The
        // adapter knows how to create list items for each item in the list.
        SiteAdapter adapter = new SiteAdapter(getActivity(), sites, R.color.category_history);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        ListView listView = rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link SiteAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Site} in the list.
        listView.setAdapter(adapter);


        //  Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // get position of resource in site then id of resource
                Site site = sites.get(position);
                int resourceId = site.getAudioResourceId();

                // create dialog fragment
                HistoryDialogFragment overlay = new HistoryDialogFragment();

                // pass arguments to the dialog fragment as args.putString("key","value");
                Bundle args = new Bundle();
                args.putString("key", Integer.toString(resourceId));
                overlay.setArguments(args);

                //set up link to dialog
                overlay.show(getFragmentManager().beginTransaction(), "dialog");

            }
        });

        return rootView;
    }

}