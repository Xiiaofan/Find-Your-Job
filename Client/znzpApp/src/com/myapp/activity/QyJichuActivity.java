package com.myapp.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;

public class QyJichuActivity extends TabActivity {

	private TabHost tabHost;
	MyApplication myApp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_jichu_qy);

		tabHost = this.getTabHost();
		TabHost.TabSpec spec;
		Intent intent;

		intent = new Intent().setClass(this, QyInfoActivity.class);
		spec = tabHost.newTabSpec("Company Information").setIndicator("Company Information")
				.setContent(intent);
		tabHost.addTab(spec);

		myApp = (MyApplication) getApplication();

		intent = new Intent().setClass(this, WdzpListActivity.class);
		spec = tabHost.newTabSpec("Recruitment").setIndicator("Recruitment")
				.setContent(intent);
		tabHost.addTab(spec);

		RadioGroup radioGroup = (RadioGroup) this.findViewById(R.id.main_radio);

		RadioButton radio_button1 = (RadioButton) this
				.findViewById(R.id.radio_button1);

		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.radio_button0:
					tabHost.setCurrentTabByTag("Company Information");
					break;

				case R.id.radio_button3:
					tabHost.setCurrentTabByTag("Recruitment");
					break;

				}
			}
		});

		radio_button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case R.id.radio_button1:

					Intent intent = new Intent();

					intent.setClass(QyJichuActivity.this,
							ZpListActivity.class);

					// start Activity
					startActivity(intent);

					break;
				}
			}

		});

		RadioButton radio_button2 = (RadioButton) this
				.findViewById(R.id.radio_button2);

		radio_button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case R.id.radio_button2:

					Intent intent = new Intent();

					intent.setClass(QyJichuActivity.this,
							QzListActivity.class);

					startActivity(intent);

					break;
				}
			}

		});
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			if (event.getAction() == KeyEvent.ACTION_DOWN
					&& event.getRepeatCount() == 0) {
				finish();
			}
			return true;
		}
		return super.dispatchKeyEvent(event);
	}
}
