package com.example.assignemnt5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

public class AndroidGadgetDataService extends Service {
  private final IBinder mBinder = new MyBinder();
  private ArrayList<GadgetData> list = new ArrayList<GadgetData>();

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
	  
	  	  GadgetData newmodel1=new GadgetData("Xperia M","62.0mm x 124.0mm x 9.3mm","115 g","Android 4.1","Bluetooth 4.0","1GHz  dual-core","5.0 megapixels","microSD card","1");
	      list.add(newmodel1);
	      
	  	  GadgetData newmodel2=new GadgetData("Nexus 4","68.7mm x 133.9mm x 9.1mm","Android 4.1","139 g","Bluetooth 4.0","1500 MHz Quad core","8.0 megapixels","None","2");
	      list.add(newmodel2);
	      
	  	  GadgetData newmodel3=new GadgetData("Samsung Galaxy Ace","59.9mm x 112.4mm x 11.5mm","113 g","Android 2.2","Bluetooth 2.1","800 MHz Single core","5.0 megapixels","microSD card (2GB included)","3");
	      list.add(newmodel3);
	      
	  	  GadgetData newmodel4=new GadgetData("Samsung Galaxy S III","5.38mm x 2.78mm x 0.34mm","133g","Android 4.0","Bluetooth 4.0","1400 MHz Quad core","8.0 megapixels","None (16GB included)","4");
	      list.add(newmodel4);
	      
	  	  GadgetData newmodel5=new GadgetData("Samsung Galaxy S4 Active","139.7mm x 71.3mm x 9.1mm","151 g","Android 4.2.2","Bluetooth 4.0","1900 MHz Quad core","8.0 megapixels","microSDXC up to 64 GB","5");
	      list.add(newmodel5);
		    
    return Service.START_NOT_STICKY;
  }
  
	class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            
        	Bundle data = msg.getData();        	
        	String dataString = data.getString("MyString");
        	Toast.makeText(getApplicationContext(), 
                     dataString, Toast.LENGTH_SHORT).show();
        }
     }
	
	final Messenger myMessenger = new Messenger(new IncomingHandler());

  @Override
  public IBinder onBind(Intent arg0) {
     //return myMessenger.getBinder();
	  return mBinder;
  }

  public class MyBinder extends Binder {
    AndroidGadgetDataService getService() {
      return AndroidGadgetDataService.this;
    }
  }

  public GadgetData getSpecs(int id) {
    return list.get(id);
  }
  

}