package com.example.colors;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class ColorSelectionActivity extends Activity {

	Intent i;
	int value = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		i = getIntent();
		setContentView(R.layout.activity_selectcolor);
		RadioGroup rg = (RadioGroup) findViewById(R.id.rgSelect);
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.rRed:
					value=10;
					break;
				case R.id.rBlue:
					value=20;
					break;
				case R.id.rGreen:
					value=30;
					break;
				case R.id.rBrown:
					value=40;
					break;
				case R.id.rYellow:
					value=50;
					break;
				}
			}
		});

		(findViewById(R.id.bdone)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				i.putExtra("value", value);
				int resultcode = 1;
				setResult(resultcode, i);
				finish();
			}
		});
	}

}
