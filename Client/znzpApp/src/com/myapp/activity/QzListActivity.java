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
import com.myweb.domain.NewsType;

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

public class QzListActivity extends ActionBarActivity {

	private ActionBar bar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_qz_list);

		bar = getSupportActionBar();

		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		Thread newsTypeThread = new Thread(new NewsTypeThread());

		newsTypeThread.start();

	}

	class NewsTypeThread implements Runnable {

		public void run() {
			try {

				JSONObject paraObj = new JSONObject();

				paraObj.put("action", "list");

				String result = HttpUtil.getJsonFromServlet(paraObj.toString(),
						"NewsTypeService");

				if (!result.equals("")) {

					List<NewsType> goodsTypeList = JSONObject.parseArray(
							result, NewsType.class);

					ArrayList<Map<String, Object>> newstypelistMap = new ArrayList<Map<String, Object>>();

					for (NewsType newstype : goodsTypeList) {

						Map<String, Object> map = new HashMap<String, Object>();

						map.put("id", newstype.getId());

						map.put("typename", newstype.getTypename());

						newstypelistMap.add(map);
					}

					Message msg = new Message();

					msg.what = 1;// success

					msg.obj = newstypelistMap;

					handler.sendMessage(msg);

				}

			} catch (Exception ex) {

				System.out.println("ex: " + ex.getMessage());

				Message msg = new Message();

				msg.what = 0;// error

				handler.sendMessage(msg);

			}
		}
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {

			super.handleMessage(msg);

			switch (msg.what) {

			case 0:

				Toast.makeText(getApplicationContext(), "fail£¬check network!",
						Toast.LENGTH_SHORT).show();
				break;

			case 1:
				try {
					ArrayList<Map<String, Object>> result = (ArrayList<Map<String, Object>>) msg.obj;

					if (result != null) {

						for (Map<String, Object> map : result) {

							map.get("id");

							map.get("typename");

							Fragment goodslistfragment = QzListFragment
									.newInstance(String.valueOf(map.get("id")));

							Tab tab1 = bar
									.newTab()
									.setText(
											String.valueOf(map.get("typename")))
									.setTabListener(
											new TestListener(goodslistfragment));

							bar.addTab(tab1);

						}

						System.out.println(result.toString());
					}
				} catch (Exception ex) {

					System.out.println("ex: " + ex.getMessage());
				}
				break;
			}

		}
	};

	class TestListener implements TabListener {
		// Fragment
		private Fragment fragment;

		// Reference the corresponding fragment by construction
		public TestListener(Fragment fragment) {
			this.fragment = fragment;
		}

		// realization ActionBar.TabListener Methods to be implemented by the interface
		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			ft.add(R.id.container, fragment, null);
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			ft.remove(fragment);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.newssearch) {
			SearchDialog dialog = new SearchDialog(this,
					new SearchDialog.OnCustomDialogListener() {

						@Override
						public void back(String goodsname) {
							if (!goodsname.equals("")) {

								Intent intent = new Intent();

								intent.putExtra("goodsname", goodsname);

								intent.setClass(QzListActivity.this, SearchNewsListActivity.class);

								// Activity
								startActivity(intent);
								
							} else {
								Toast.makeText(QzListActivity.this,
										"Content cannot be empty!", Toast.LENGTH_SHORT).show();
							}
						}
					});
			dialog.show();
		}
		else if (id == R.id.xxgk) {
			Intent intent = new Intent(QzListActivity.this,
					XygkListActivity.class);

			startActivity(intent);
		}
		
		else if (id == R.id.gerenxinxi) {
			Intent intent = new Intent(QzListActivity.this,
					UserInfoActivity.class);

			startActivity(intent);
		}

		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_news_list,
					container, false);
			return rootView;
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);

		}

	}

}
