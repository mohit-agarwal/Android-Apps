package com.example.wonders;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


public class WondersListFragment extends Fragment {
	//private static List<LinkData> linkDataList = new ArrayList<LinkData>();
	public static LinkAdapter la;
	
	/*static {
		linkDataList.add(new LinkData("Apple", "http://www.apple.com"));
		linkDataList.add(new LinkData("Facebook", "http://www.facebook.com"));
		linkDataList.add(new LinkData("Android", "http://www.android.com"));
		linkDataList.add(new LinkData("Google Mail", "http://mail.google.com"));
	}*/
	
	public WondersListFragment() {	
		this.la=la;
	}



	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// Here we set our custom adapter. Now we have the reference to the activity

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
	    Log.d("SwA", "LV onCreateView");
	   // SqlHandler sqlHandler = new SqlHandler(getActivity().getBaseContext());
		//Model.LoadModel(sqlHandler);
	    View v = inflater.inflate(R.layout.activity_link_list, container, false);
	    ListView lv = (ListView) v.findViewById(R.id.urls);
	    la = new LinkAdapter(Model.linkDatalist, getActivity());
	    lv.setAdapter(la);
	    la.notifyDataSetChanged();
	    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	        @Override
	         public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
	                 long id) {
	            LinkData data = ( (LinkAdapter) la ).getItem(position);
	            ( (ChangeLinkListener)  getActivity()).onLinkChange(data.getName());
	        }

	    });
	    return v;
	}

	@Override
	public void onAttach(Activity activity) {
		// We verify that our activity implements the listener
		if (! (activity instanceof ChangeLinkListener) )
			throw new ClassCastException();


		super.onAttach(activity);
	}
}
