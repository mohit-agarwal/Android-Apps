package com.example.wonders;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LinkListActivity extends Activity implements ChangeLinkListener {

	SqlHandler sqlHandler;
	String currentname="";
	static int cnt=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sqlHandler = new SqlHandler(this);
		if(cnt==0)
		{
		getApplicationContext().deleteDatabase("MY_DATABASE");
		Model.LoadModel(sqlHandler);
		}
		cnt+=1;
		Model.showAll(sqlHandler);
		setContentView(R.layout.activity_layout);
		Log.d("In LinkListActivity", "On Create");

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_add:
			create_add_dialog();
			return true;
		case R.id.action_edit:
			create_edit_dialog();
			return true;
		case R.id.action_delete:
			if(!currentname.isEmpty())
			{
				String query = "DELETE FROM PHONE_CONTACTS where wondername = '"+currentname+"'";
				//Toast.makeText(this, (String)currentname, Toast.LENGTH_LONG).show();
				sqlHandler.executeQuery(query);
				Model.showAll(sqlHandler);
				WondersListFragment.la.notifyDataSetChanged();
			}
			create_delete_dialog(currentname);
			return true;
		}
		return super.onOptionsItemSelected(item);

	}

	private void create_delete_dialog(String name) {
		// TODO Auto-generated method stub
		final Dialog dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.delete_dialog);
		TextView tv=(TextView)dialog.findViewById(R.id.tv_delete);
		String text=name+" has been removed. ";
		tv.setText(text);
		Button submitbutton = (Button) dialog.findViewById(R.id.bt_delete);
		submitbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				// TODO Auto-generated method stub
				
			}
		});
		dialog.show();
	}


	private void create_add_dialog() {
		// TODO Auto-generated method stub
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.dialog);
		dialog.setTitle("Add Wonder");

		Button submitbutton = (Button) dialog.findViewById(R.id.dialog_button);
		
		
		submitbutton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText et1 = (EditText) dialog.findViewById(R.id.dialog_edit_name);
				EditText et2 = (EditText) dialog.findViewById(R.id.dialog_edit_imagename);
				EditText et3 = (EditText) dialog.findViewById(R.id.dialog_edit_desc);
				String name = et1.getText().toString();
				String imgname = et2.getText().toString();
				String desc = et3.getText().toString();
				String query = "INSERT INTO PHONE_CONTACTS(wondername,wonderimage,wonderdesc) values ('"
						+ name + "','" + imgname + "','" + desc + "')";
				sqlHandler.executeQuery(query);
				Model.showAll(sqlHandler);
				WondersListFragment.la.notifyDataSetChanged();
				dialog.cancel();
			}
		});
		
		dialog.show();
	}
	
	private void create_edit_dialog() {
		// TODO Auto-generated method stub
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.dialog);
		dialog.setTitle("Add Wonder");

		Button submitbutton = (Button) dialog.findViewById(R.id.dialog_button);
		
		
		submitbutton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText et1 = (EditText) dialog.findViewById(R.id.dialog_edit_name);
				EditText et2 = (EditText) dialog.findViewById(R.id.dialog_edit_imagename);
				EditText et3 = (EditText) dialog.findViewById(R.id.dialog_edit_desc);
				String name = et1.getText().toString();
				String imgname = et2.getText().toString();
				String desc = et3.getText().toString();
				String query = "UPDATE PHONE_CONTACTS set wondername = '"+name+"' ,wonderimage = '"+imgname+"',wonderdesc = '"+desc+"' where wondername = '"+name+"'";
				sqlHandler.executeQuery(query);
				Model.showAll(sqlHandler);
				WondersListFragment.la.notifyDataSetChanged();
				dialog.cancel();
			}
		});
		
		dialog.show();
	}

	@Override
	public void onLinkChange(String name) {
		currentname=name;
		System.out.println("Listener");
		// Here we detect if there's dual fragment
		if ((findViewById(R.id.fragPage1) != null)
				&& (findViewById(R.id.fragPage2) != null)) {
			WonderImageFragment wvf1 = (WonderImageFragment) getFragmentManager()
					.findFragmentById(R.id.fragPage1);
			WonderDescFragment wvf2 = (WonderDescFragment) getFragmentManager()
					.findFragmentById(R.id.fragPage2);

			if (wvf1 == null) {
				System.out.println("Dual fragment - 1");
				wvf1 = new WonderImageFragment();
				wvf1.init(name);
				// We are in dual fragment (Tablet and so on)
				FragmentManager fm = getFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();

				// wvf.updateUrl(link);
				ft.replace(R.id.fragPage1, wvf1);
				ft.commit();

			} else {
				Log.d("ListFragment", "Dual Fragment update");
				wvf1.updateName(name);
			}

			if (wvf2 == null) {
				System.out.println("Dual fragment - 1");
				wvf2 = new WonderDescFragment();
				wvf2.init(name);
				// We are in dual fragment (Tablet and so on)
				FragmentManager fm = getFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();

				// wvf.updateUrl(link);
				ft.replace(R.id.fragPage2, wvf2);
				ft.commit();

			} else {
				Log.d("ListFragment", "Dual Fragment update");
				wvf2.updateName(name);
			}

		}

		else {
			Log.d("helo", "Start Activity");
			Intent i = new Intent(this, WebViewActivity.class);
			i.putExtra("name", name);
			System.out.println("YES!!!!");
			startActivity(i);
		}

	}
}
