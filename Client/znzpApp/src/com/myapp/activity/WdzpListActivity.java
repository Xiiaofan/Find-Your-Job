package com.myapp.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.alibaba.fastjson.JSONObject;
import com.myapp.activity.ZpListFragment.NewsListThread;
import com.myapp.adapter.BoardListAdapter;
import com.myapp.adapter.WdzpListAdapter.ViewHolder;
import com.myapp.adapter.ZpListAdapter;
import com.myapp.adapter.WdzpListAdapter;
 
import com.myapp.common.HttpUtil;
import com.myweb.domain.News;
import com.myweb.domain.User;

public class WdzpListActivity extends FinalActivity {

	MyApplication myApp;

	ListView newsListView;

	WdzpListAdapter wdzpListAdapter;

	List<Map<String, Object>> newsList;
	

	private String[] opr = new String[] { "Delete" };

	@ViewInject(id = R.id.button1, click = "addBtnClick")
	private Button loginBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_wdzp_list);

		myApp = (MyApplication) getApplication(); // MyApp

		newsListView = (ListView) findViewById(R.id.note_lv);

		newsList = new ArrayList<Map<String, Object>>();

		wdzpListAdapter = new WdzpListAdapter(this, newsList);

		newsListView.setAdapter(wdzpListAdapter);

		Thread newsListThread = new Thread(new NewsListThread());

		newsListThread.start();
		

		newsListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				ViewHolder vHollder = (ViewHolder) view.getTag();

				if (vHollder.id != null) {

					Intent intent = new Intent();

					intent.putExtra("id", vHollder.id);

					intent.setClass(WdzpListActivity.this,WdzpDetailActivity.class);

					// Activity
					startActivity(intent);
				}
			}
		});
		
		newsListView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					final int position, long id) {

				ViewHolder vHollder = (ViewHolder) view.getTag();

				showOprDialog(vHollder.id);
				
				return true;

			}
		});

	}
	
	private void showOprDialog(String id) {

		final String _id = id;

		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle("Actions:");

		builder.setItems(opr, new DialogInterface.OnClickListener()

		{
			@Override
			public void onClick(DialogInterface dialog, int which) {

				if (which == 0) {

					FinalHttp finalHttp = new FinalHttp();

					AjaxParams ajaxParams = new AjaxParams();

					ajaxParams.put("action", "delete");

					String sql = "";

					sql = "delete from news  where id='" + _id + "'";

					ajaxParams.put("sql", sql);

					try {
						finalHttp.get(HttpUtil.SQLSERVICE_URL, ajaxParams,
								new AjaxCallBack<Object>() {

									@Override
									public void onSuccess(Object t) {

										String result = (String) t;

										Toast.makeText(WdzpListActivity.this,
												"Success!", Toast.LENGTH_SHORT)
												.show();

										Thread newsListThread = new Thread(new NewsListThread());

										newsListThread.start();


									}
								});

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		});

		builder.create().show();
	}
	
	public void addBtnClick(View v) {
		Intent intent = new Intent(WdzpListActivity.this,
				AddZpActivity.class);
		// Activity
		startActivity(intent);
		
	}

	class NewsListThread implements Runnable {

		public void run() {
			try {

				JSONObject paraObj = new JSONObject();

				paraObj.put("action", "mylist");

				paraObj.put("userid", String.valueOf(myApp.getUser().getId()));

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

				Toast.makeText(WdzpListActivity.this, "Query failed, please check the network!",
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

							map.put("author", news.getCreateusername());

							newslistMap1.add(map);
						}

						if (newslistMap1.size() > 0) {

							newsList.clear();

							newsList.addAll(newslistMap1);

							wdzpListAdapter.notifyDataSetChanged();

						}
					} catch (Exception ex) {

						System.out.println("ex: " + ex.getMessage());
					}
				}
				break;
			}

		}
	};
	
	

	
	@Override
	protected void onResume() {

		super.onResume();

		handler1.post(runnable);
	}

	private Runnable runnable = new Runnable() {

		public void run() {
			
			handler1.sendEmptyMessage(1);
		}
	};

	private Handler handler1 = new Handler() {

		public void handleMessage(android.os.Message msg) {

			switch (msg.what) {

			case 1:
				// renew UI
				Thread newsListThread = new Thread(new NewsListThread());

				newsListThread.start();
				

				break;
			}
		};
	};

 
}
