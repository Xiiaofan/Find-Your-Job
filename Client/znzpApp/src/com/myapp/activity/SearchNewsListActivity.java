package com.myapp.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.myapp.adapter.ZpListAdapter;
import com.myapp.adapter.ZpListAdapter.ViewHolder;
import com.myapp.common.HttpUtil;
import com.myweb.domain.News;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SearchNewsListActivity extends ActionBarActivity {

	ListView newsListView;

	ZpListAdapter newsListAdapter;

	List<Map<String, Object>> newsList;
	
	String title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_search_list);

		ListView newsListView = (ListView) findViewById(R.id.newslist_lv);

		newsList = new ArrayList<Map<String, Object>>();

		newsListAdapter = new ZpListAdapter(SearchNewsListActivity.this,
				newsList);

		newsListView.setAdapter(newsListAdapter);

		newsListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				ViewHolder vHollder = (ViewHolder) view.getTag();

				if (vHollder.id != null) {

					Intent intent = new Intent();

					intent.putExtra("id", vHollder.id);

					intent.setClass(SearchNewsListActivity.this,
							SearchDetailActivity.class);

					// Activity
					startActivity(intent);
				}
			}
		});

		Intent intent = getIntent();

		title = intent.getStringExtra("goodsname");

		if (!title.equals("")) {

			Thread newsListThread = new Thread(new NewsListThread());

			newsListThread.start();

		}
		

	}

	class NewsListThread implements Runnable {

		public void run() {
			try {

				JSONObject paraObj = new JSONObject();

				paraObj.put("action", "search");
				
				paraObj.put("title", title);
				
				String result = HttpUtil.getJsonFromServlet(paraObj.toString(),
						"NewsService");

				Message msg = new Message();

				msg.what = 1;

				msg.obj = result;

				handler.sendMessage(msg);

			} catch (Exception ex) {

				System.out.println("ex: " + ex.getMessage());

				Message msg = new Message();

				msg.what = 0;

				handler.sendMessage(msg);

			}
		}
	}

	// Handler
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {

			super.handleMessage(msg);

			switch (msg.what) {

			case 0:
				Toast.makeText(getApplicationContext(), "Query failed, please check the network!",
						Toast.LENGTH_SHORT).show();
				break;

			case 1:

				if (msg.obj != null) {
					String dataStr = (String) msg.obj;

					try {

						List<News> newsList1 = new ArrayList<News>();

						newsList1 = JSONObject.parseArray(dataStr, News.class);

						ArrayList<Map<String, Object>> newslistMap1 = new ArrayList<Map<String, Object>>();

						for (News news : newsList1) {

							Map<String, Object> map = new HashMap<String, Object>();

							map.put("id", news.getId());

							String _title = news.getTitle();

							if (_title.length() > 16) {

								_title = _title.substring(0, 16) + "...";

							}
							map.put("title", _title);

							map.put("createTime", news.getCreatetime());

							if (news.getImgpath().length() > 0) {
								map.put("imgpath",
										HttpUtil.BASE_URL + news.getImgpath());
							} else {
								map.put("imgpath", "");
							}

							map.put("author", news.getCreateuser());

							newslistMap1.add(map);
						}

						if (newslistMap1.size() > 0) {

							newsList.clear();

							newsList.addAll(newslistMap1);

							newsListAdapter.notifyDataSetChanged();

						}
					} catch (Exception ex) {

						System.out.println("ex: " + ex.getMessage());
					}
				}
				break;
			}

		}
	};

}
