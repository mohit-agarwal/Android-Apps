package com.example.assignemnt5;

import java.util.ArrayList;
import java.util.List;


import android.R.color;
import android.app.Activity;
import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class AndroidGadgetSpecActivity extends Activity{
  private AndroidGadgetDataService s;
  ArrayList<String> modelslist= new ArrayList<String>();
  ArrayAdapter<String> modeladapter;
  Intent service;
Messenger myService = null;
boolean isBound;
/** Called when the activity is first created. */

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    modelslist.clear();
    modelslist.add("Xperia");
    modelslist.add("Nexus 4");
    modelslist.add("Samsung Galaxy Ace");
    modelslist.add("Samsung Galaxy S III");
    modelslist.add("Samsung Galaxy S4 Active");
    
    setContentView(R.layout.activity_android_gadget_spec);
    ListView Modelview = (ListView) findViewById(R.id.lvModels);
    
    modeladapter = new ArrayAdapter<String>(this,
      R.layout.mylist, android.R.id.text1,
        modelslist);

    Modelview.setAdapter(modeladapter);
    //linksview.setBackgroundColor(color.white);
    //imgsview.setBackgroundColor(color.white);
    
    Modelview.setOnItemClickListener(new OnItemClickListener() {    

        
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        	GadgetData model = null;
            if (s != null) {
                model=s.getSpecs(position);
             }
			Intent i=new Intent(AndroidGadgetSpecActivity.this,MobileSpec.class);
			i.putExtra("name",model.name);
			i.putExtra("size",model.size);
			i.putExtra("wt",model.wt);
			i.putExtra("OS",model.OS);
			i.putExtra("connectivity",model.connectivity);
			i.putExtra("processor",model.processor);
			i.putExtra("camera",model.camera);
			i.putExtra("sd_storage",model.sd_storage);
			i.putExtra("img",model.img);
			startActivity(i);
        	
        }


     });
    
    
  }

  @Override
  protected void onResume() {
    super.onResume();
    service = new Intent(this, AndroidGadgetDataService.class);
    startService(service);
    bindService(new Intent(this, AndroidGadgetDataService.class), mConnection,
        Context.BIND_AUTO_CREATE);
  }

  @Override
  protected void onPause() {
    super.onPause();
    unbindService(mConnection);
  }

  private ServiceConnection mConnection = new ServiceConnection() {

    public void onServiceConnected(ComponentName className, IBinder binder) {
      s = ((AndroidGadgetDataService.MyBinder) binder).getService();
      Toast.makeText(AndroidGadgetSpecActivity.this, "Connected", Toast.LENGTH_SHORT)
          .show();
      isBound=true;
    }

    public void onServiceDisconnected(ComponentName className) {
      s = null;
      isBound=false;
    }
  };
  
  public void sendMessage(View view)
  {
  	  if (!isBound) return;
          
          Message msg = Message.obtain();
          
          Bundle bundle = new Bundle();
          bundle.putString("MyString", "Message Received");
          
          msg.setData(bundle);
          
          try {
              myService.send(msg);
          } catch (RemoteException e) {
              e.printStackTrace();
          }
  }
  
  
}