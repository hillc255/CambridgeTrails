package com.example.android.cambridgetrails;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass  - based on Miwok Application
 */
public class MapFragment extends Fragment {

    //Context mContext;
    // View listItemView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.site_list, container, false);


        // Create a list of words
        final ArrayList<Site> sites = new ArrayList<Site>();
        sites.add(new Site(getString(R.string.address1), getString(R.string.name1)));
        sites.add(new Site(getString(R.string.address2), getString(R.string.name2)));
        sites.add(new Site(getString(R.string.address3), getString(R.string.name3)));
        sites.add(new Site(getString(R.string.address4), getString(R.string.name4)));
        sites.add(new Site(getString(R.string.address5), getString(R.string.name5)));
        sites.add(new Site(getString(R.string.address6), getString(R.string.name6)));
        sites.add(new Site(getString(R.string.address7), getString(R.string.name7)));
        sites.add(new Site(getString(R.string.address8), getString(R.string.name8)));
        sites.add(new Site(getString(R.string.address9), getString(R.string.name9)));
        sites.add(new Site(getString(R.string.address10), getString(R.string.name10)));
        sites.add(new Site(getString(R.string.address11), getString(R.string.name11)));
        sites.add(new Site(getString(R.string.address12), getString(R.string.name12)));
        sites.add(new Site(getString(R.string.address13), getString(R.string.name13)));
        sites.add(new Site(getString(R.string.address14), getString(R.string.name14)));
        sites.add(new Site(getString(R.string.address15), getString(R.string.name15)));
        sites.add(new Site(getString(R.string.address16), getString(R.string.name16)));
        sites.add(new Site(getString(R.string.address17), getString(R.string.name17)));
        sites.add(new Site(getString(R.string.address18), getString(R.string.name18)));
        sites.add(new Site(getString(R.string.address19), getString(R.string.name19)));
        sites.add(new Site(getString(R.string.address20), getString(R.string.name20)));


        // Create an {@link SiteAdapter}, whose data source is a list of {@link Site}s. The
        // adapter knows how to create list items for each item in the list.
        // SiteAdapter adapter = new SiteAdapter(this, words);
        //fix for fragments  - SiteAdapter adapter = new SiteAdapter(this, words, R.color.category_numbers);
        SiteAdapter adapter = new SiteAdapter(getActivity(), sites, R.color.category_map);


        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // site_list.xml file.
        //fixed for Fragment  ListView listView = (ListView) findViewById(R.id.list);
        ListView listView = (ListView) rootView.findViewById(R.id.list);


        // Make the {@link ListView} use the {@link SiteAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Site} in the list.
        listView.setAdapter(adapter);


        return rootView;
    }

}