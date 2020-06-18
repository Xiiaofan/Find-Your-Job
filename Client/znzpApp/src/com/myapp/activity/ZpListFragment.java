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

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class ZpListFragment extends Fragment {

	ListView newsListView;

	ZpListAdapter newsListAdapter;

	private String _newstypeid;

	private ProgressDialog pd;

	List<Map<String, Object>> newsList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		_newstypeid = getArguments().getString("newstypeid");

	}

	public static final ZpListFragment newInstance(String newstypeid) {
		ZpListFragment f = new ZpListFragment();

		Bundle bdl = new Bundle(2);

		bdl.putString("newstypeid", newstypeid);

		f.setArguments(bdl);

		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_news_list, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		/* ProgressDialog */
		pd = ProgressDialog.show(getActivity(), "Title", "Loading, please wait");

		ListView newsListView = (ListView) getActivity().findViewById(
				R.id.newslist_lv);

		newsList = new ArrayList<Map<String, Object>>();

		newsListAdapter = new ZpListAdapter(getActivity(), newsList);

		newsListView.setAdapter(newsListAdapter);

		newsListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				ViewHolder vHollder = (ViewHolder) view.getTag();

				if (vHollder.id != null) {

					Intent intent = new Intent();

					intent.putExtra("id", vHollder.id);

					intent.setClass(getActivity(), ZpDetailActivity.class);

					// Activity
					startActivity(intent);
				}
			}
		});

		Thread newsListThread = new Thread(new NewsListThread());

		newsListThread.start();

	}

	class NewsListThread implements Runnable {

		public void run() {
			try {

				JSONObject paraObj = new JSONObject();

				paraObj.put("action", "list");

				paraObj.put("newstypeid", _newstypeid);

				String result = HttpUtil.getJsonFromServlet(paraObj.toString(),
						"NewsService");

				Message msg = new Message();

				msg.what = 1;// Success

				msg.obj = result;

				handler.sendMessage(msg);

			} catch (Exception ex) {

				System.out.println("ex: " + ex.getMessage());

				Message msg = new Message();

				msg.what = 0;// Error

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
				pd.dismiss();// ProgressDialog

				Toast.makeText(getActivity().getApplicationContext(),
						"Query failed, please check the network!", Toast.LENGTH_SHORT).show();
				break;

			case 1:
				pd.dismiss();// ¹Ø±ÕProgressDialog

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
