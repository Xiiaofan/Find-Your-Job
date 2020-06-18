package com.myapp.activity;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

import com.alibaba.fastjson.JSONObject;
import com.myapp.activity.R;
import com.myapp.common.HttpUtil;
import com.myweb.domain.User;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.os.Build;

public class LoginActivity extends FinalActivity {

	User user;// save info

	@ViewInject(id = R.id.radioGroup1)
	RadioGroup rg;

	@ViewInject(id = R.id.radio0)
	RadioButton radio0;

	@ViewInject(id = R.id.radio1)
	RadioButton radio1;

	String usertype = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);

		final EditText loginTxt = (EditText) findViewById(R.id.editText1);

		final EditText passwordTxt = (EditText) findViewById(R.id.editText2);

		// login button
		Button button1 = (Button) findViewById(R.id.button1);

		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				usertype = "Company";

				if (radio1.isChecked()) {
					usertype = "Individual";
				}

				Thread loginThread = new Thread(new LoginThread());

				loginThread.start();
			}
		});

		// register button
		Button regBtn = (Button) findViewById(R.id.login_regbtn);

		regBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(LoginActivity.this,
						RegActivity.class);
				// start Activity
				startActivity(intent);
			}
		});

	}

	// Login multithreading
	class LoginThread implements Runnable {

		public void run() {
			try {

				String loginname = ((EditText) findViewById(R.id.editText1))
						.getText().toString();

				String loginpsw = ((EditText) findViewById(R.id.editText2))
						.getText().toString();

				User user = new User();

				user.setLoginname(loginname);

				user.setLoginpsw(loginpsw);
			  
				user.setUsertype(usertype);
				
				JSONObject paraObj = new JSONObject();

				paraObj.put("action", "login");

				paraObj.put("user", JSONObject.toJSONString(user));

				String result = HttpUtil.getJsonFromServlet(paraObj.toString(),
						"UserService");

				if (result.equals("")) {

					Message msg = new Message();

					msg.what = 2;// error

					handler.sendMessage(msg);
				} else {
					Message msg = new Message();

					msg.what = 3;// success

					msg.obj = result.toString();

					handler.sendMessage(msg);

				}

			} catch (Exception ex) {

				System.out.println("ex: " + ex.getMessage());

				Message msg = new Message();

				msg.what = 1;// error

			}
		}
	}

	// Handler
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {

			super.handleMessage(msg);

			switch (msg.what) {

			case 2:
				Toast.makeText(getApplicationContext(), "Login failed, please check account and password!",
						Toast.LENGTH_SHORT).show();
				break;

			case 3:

				String result = (String) msg.obj;
				try {

					User user = JSONObject.parseObject(result, User.class);

					Toast.makeText(getApplicationContext(), "success!",
							Toast.LENGTH_SHORT).show();

					MyApplication myApp = (MyApplication) getApplication(); // get MyApp

					myApp.setUser(user);

					if (usertype.equals("Company")) {

						Intent intent = new Intent(LoginActivity.this,
								QyJichuActivity.class);
						// start Activity
						startActivity(intent);
					} else {

						Intent intent = new Intent(LoginActivity.this,
								GrJichuActivity.class);
						
						startActivity(intent);
					}

				} catch (Exception ex) {

					System.out.println("ex: " + ex.getMessage());
				}
				break;
			}

		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will

		return super.onOptionsItemSelected(item);
	}
}
