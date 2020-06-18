package com.myapp.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.myapp.activity.R;
import com.myapp.adapter.WdzpListAdapter.ViewHolder;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ZpListAdapter extends BaseAdapter {


	private List<Map<String, Object>> mData;

	private LayoutInflater mInflater;

	public static Map<Integer, Boolean> isSelected;

	private Context context;// 用于接收传递过来的Context对象

	public ZpListAdapter(Context context, List<Map<String, Object>> mData) {

		this.context = context;

		mInflater = LayoutInflater.from(context);

		this.mData = mData;
	}

	@Override
	public int getCount() {
		System.out.print("mData.size()=" + mData.size());
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;

		// convertView为null的时候初始化convertView。
		if (convertView == null) {

			holder = new ViewHolder();

			convertView = mInflater.inflate(R.layout.activity_zp_list_item,
					null);

			holder.title = (TextView) convertView
					.findViewById(R.id.newslisttitle);

			holder.author = (TextView) convertView
					.findViewById(R.id.newslistauthor);

			holder.createtime = (TextView) convertView
					.findViewById(R.id.newslistcreatetime);

			convertView.setTag(holder);

		} else {

			holder = (ViewHolder) convertView.getTag();

		}
		
		holder.id=mData.get(position).get("id").toString();

		holder.title.setText(mData.get(position).get("title").toString());

		holder.author.setText(mData.get(position).get("author").toString());

		String createTime = mData.get(position).get("createTime").toString();

		if (createTime.length() > 0) {

			createTime = createTime.substring(0, createTime.indexOf(" "))
					.replace("-", "");
		}

		holder.createtime.setText(createTime);

		return convertView;
	}

	public final class ViewHolder {

		public String id;

		public TextView title;

		public TextView author;

		public TextView createtime;

	}

}
