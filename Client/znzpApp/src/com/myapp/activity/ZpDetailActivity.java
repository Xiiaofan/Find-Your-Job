package com.myapp.activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.myapp.activity.LoginActivity.LoginThread;
import com.myapp.adapter.ZpListAdapter;
import com.myapp.adapter.ZpListAdapter.ViewHolder;
import com.myapp.common.HttpUtil;
import com.myapp.common.URLImageParser;
import com.myweb.domain.News;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.ScrollingMovementMethod;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.os.Build;

public class ZpDetailActivity extends ActionBarActivity {

	MyApplication myApp;

	private TextView tv1;

	private TextView tv2;

	private TextView tv3;

	private TextView tv4;

	private TextView tv5;

	Map<String, Object> newsMap;

	private String id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_zp_detail);

		Intent intent = getIntent();

		id = intent.getStringExtra("id");

		myApp = (MyApplication) getApplication(); // MyApp

		tv1 = (TextView) findViewById(R.id.textView1);

		tv2 = (TextView) findViewById(R.id.textView2);

		tv3 = (TextView) findViewById(R.id.textView3);

		tv4 = (TextView) findViewById(R.id.textView4);

		tv5 = (TextView) findViewById(R.id.textView5);

		tv5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Thread shoucangThread = new Thread(new ShoucangThread());

				shoucangThread.start();

			}
		});

		tv5.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); 

		if (myApp.getUser().getUsertype().equals("Company")) {
			tv5.setVisibility(View.GONE);
		} else {
			tv5.setVisibility(View.VISIBLE);
		}

		tv4.setMovementMethod(ScrollingMovementMethod.getInstance());

		newsMap = new HashMap<String, Object>();

		Thread newsDetailThread = new Thread(new NewsDetailThread());

		newsDetailThread.start();

	}

	class ShoucangThread implements Runnable {

		public void run() {
			try {

				JSONObject paraObj = new JSONObject();

				paraObj.put("newsid", id);

				paraObj.put("userid", String.valueOf(myApp.getUser().getId()));

				paraObj.put("action", "shoucang");

				String result = HttpUtil.getJsonFromServlet(paraObj.toString(),
						"NewsService");

				if (!result.equals("")) {

					Message msg = new Message();

					msg.what = 0;

					handler.sendMessage(msg);

				}

			} catch (Exception ex) {

				System.out.println("ex: " + ex.getMessage());

				Message msg = new Message();

				msg.what = 4;

				handler.sendMessage(msg);

			}
		}
	}

	
	class NewsDetailThread implements Runnable {

		public void run() {
			try {

				JSONObject paraObj = new JSONObject();

				paraObj.put("id", id);

				paraObj.put("action", "view");

				String result = HttpUtil.getJsonFromServlet(paraObj.toString(),
						"NewsService");

				if (!result.equals("")) {

					News news = JSONObject.parseObject(result, News.class);

					Map<String, Object> newsMap1 = new HashMap<String, Object>();

					newsMap1.put("id", String.valueOf(news.getId()));

					newsMap1.put("title", news.getTitle());

					newsMap1.put("createTime", news.getCreatetime());

					newsMap1.put("createUser", news.getCreateusername());

					newsMap1.put("content", news.getContent());

					Message msg = new Message();

					msg.what = 1;

					msg.obj = newsMap1;

					handler.sendMessage(msg);

				}

			} catch (Exception ex) {

				System.out.println("ex: " + ex.getMessage());

				Message msg = new Message();

				msg.what = 0;

				handler.sendMessage(msg);

			}
		}
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {

			super.handleMessage(msg);

			switch (msg.what) {

			case 0:

				Toast.makeText(getApplicationContext(), "Collected!",
						Toast.LENGTH_SHORT).show();
				break;
			case 4:

				Toast.makeText(getApplicationContext(), "Error!",
						Toast.LENGTH_SHORT).show();
				break;

			case 1:

				try {
					newsMap = (Map<String, Object>) msg.obj;

					String cs = newsMap.get("content").toString();

					id = newsMap.get("id").toString();

					tv1.setText(newsMap.get("title").toString());

					tv2.setText("Company:" + newsMap.get("createUser").toString());

					String createTime = newsMap.get("createTime").toString();

					if (!createTime.equals("")) {
						createTime = createTime.substring(0,
								createTime.indexOf(" "));
					}

					tv3.setText("Post Time:" + createTime);

					tv4.setMovementMethod(ScrollingMovementMethod.getInstance());

					URLImageParser imgOBJ = new URLImageParser(tv4,
							ZpDetailActivity.this);// URLImageGetter

					Spanned content = Html.fromHtml(cs, imgOBJ, null);

					tv4.setText(content);

				} catch (Exception ex) {
					System.out.print(ex.getMessage());
				}
			}

		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.news_detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		return super.onOptionsItemSelected(item);
	}

}
