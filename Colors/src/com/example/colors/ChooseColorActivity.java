package com.example.colors;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

public class ChooseColorActivity extends Activity {

	Button selectcol;
	int requestcode = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_color);
		selectcol = (Button) findViewById(R.id.bselect);
		selectcol.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(),ColorSelectionActivity.class);
				startActivityForResult(i, requestcode);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.choose_color, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1 && resultCode == 1) {
			int value = data.getExtras().getInt("value");
			FrameLayout fl = (FrameLayout) findViewById(R.id.ColorBox);
			switch (value) {
			case 10:
					fl.setBackgroundColor(Color.RED);
				break;
			case 20:
				fl.setBackgroundColor(Color.BLUE);
				break;
			case 30:
				fl.setBackgroundColor(Color.GREEN);
				break;
			case 40:
				fl.setBackgroundColor(Color.argb(255, 139, 69, 19));
				break;
			case 50:
				fl.setBackgroundColor(Color.YELLOW);
				break;

			}
		}
	}
}
