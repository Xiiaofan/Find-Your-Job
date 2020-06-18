package com.myapp.activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.tsz.afinal.FinalActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.myapp.common.HttpUtil;
import com.myweb.domain.News;
import com.myweb.domain.User;

public class AddZpActivity extends FinalActivity {

	MyApplication myApp;

	EditText biaotitxt;

	Spinner hysp;

	EditText shuomingtxt;

	Button fabubtn;

	Map<String, Object> userMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_add_zp);

		biaotitxt = (EditText) findViewById(R.id.biaoti);

		hysp = (Spinner) findViewById(R.id.hy);

		shuomingtxt = (EditText) findViewById(R.id.shuoming);

		fabubtn = (Button) findViewById(R.id.fabubtn);

		fabubtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Thread saveThread = new Thread(new SaveThread());

				saveThread.start();
			}
		});

		myApp = (MyApplication) getApplication(); // get app

	}

	class SaveThread implements Runnable {

		public void run() {
			try {

				String biaoti = biaotitxt.getText().toString();

				String shuoming = shuomingtxt.getText().toString();

				 
				String hangye = hysp.getSelectedItem().toString();

				String hangyeid = "";

				if (hangye.equals("IT")) {
					hangyeid = "1";
				} else if (hangye.equals("Service")) {
					hangyeid = "2";
				} else if (hangye.equals("Finance")) {
					hangyeid = "3";
				} else if (hangye.equals("Transportation")) {
					hangyeid = "4";
				} else if (hangye.equals("Agriculture")) {
					hangyeid = "5";
				}
				
				News news=new News();
				
				news.setTitle(biaoti);
				
				news.setTypeid(hangyeid);
				
				news.setContent(shuoming);
				
				SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				Date date = new Date();
				
				news.setCreatetime(f.format(date));
				
				news.setCreateuser(String.valueOf(myApp.getUser().getId()));

				  
				JSONObject paraObj = new JSONObject();

				paraObj.put("action", "add");

				paraObj.put("news", JSONObject.toJSONString(news));

				String result = HttpUtil.getJsonFromServlet(paraObj.toString(),
						"NewsService");

				if (result.equals("ok")) {

					Message msg = new Message();

					msg.what = 1;// success

					handler.sendMessage(msg);
				}

			} catch (Exception ex) {

				System.out.println("ex: " + ex.getMessage());

				Message msg = new Message();

				msg.what = 2;// error

				handler.sendMessage(msg);

			}
		}
	}

	// Handler
	Handler handler = new Handler() {

		public void handleMessage(Message msg) {

			super.handleMessage(msg);

			switch (msg.what) {

		 
			case 1:

				Toast.makeText(getApplicationContext(), "success!",
						Toast.LENGTH_SHORT).show();
				
				finish();
				
				break;

			case 2:

				Toast.makeText(getApplicationContext(), "Save failed, please check the network!",
						Toast.LENGTH_SHORT).show();
				break;
			 
			}

		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.add_question, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will

		return super.onOptionsItemSelected(item);
	}
}
