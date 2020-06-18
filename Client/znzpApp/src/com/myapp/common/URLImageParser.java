package com.myapp.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.myapp.activity.ZpDetailActivity;
import com.myapp.activity.R;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.Html.ImageGetter;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

public class URLImageParser implements ImageGetter {

	Context c;

	TextView tv_image;

	private Drawable mDefaultDrawable;

	public URLImageParser(TextView t, Context c) {
		this.tv_image = t;
		this.c = c;

		try {
			mDefaultDrawable = c.getResources().getDrawable(R.drawable.logo);

		} catch (NotFoundException e) {

			mDefaultDrawable = null;

			e.printStackTrace();
		}
	}

	@Override
	public Drawable getDrawable(String source) {

		URLDrawable urlDrawable = new URLDrawable(c);

		try {

			urlDrawable.drawable = mDefaultDrawable;

			URLImageParser.this.tv_image.invalidate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		ImageGetterAsyncTask asyncTask = new ImageGetterAsyncTask(urlDrawable);

		asyncTask.execute(source);

		return urlDrawable;
	}

	public class ImageGetterAsyncTask extends AsyncTask<String, Void, Drawable> {

		URLDrawable urlDrawable;

		public ImageGetterAsyncTask(URLDrawable d) {
			this.urlDrawable = d;
		}

		@Override
		protected void onPostExecute(Drawable result) {
			if (result != null) {
				urlDrawable.drawable = result;

				urlDrawable.setBounds(0, 0, result.getBounds().width(), result
						.getBounds().height());

				URLImageParser.this.tv_image.invalidate();

				tv_image.setText(tv_image.getText());// 解决图文重叠

			}
		}

		@Override
		protected void onPreExecute() {
			urlDrawable.setBounds(0, 0,
					0 + mDefaultDrawable.getIntrinsicWidth(),
					0 + mDefaultDrawable.getIntrinsicHeight());
			urlDrawable.drawable = mDefaultDrawable;
			URLImageParser.this.tv_image.invalidate();
			super.onPreExecute();
		}

		@Override
		protected Drawable doInBackground(String... params) {
			// TODO Auto-generated method stub
			String source = params[0];//
			return fetchDrawable(source);
		}

		public Drawable fetchDrawable(String url) {
			if (url.indexOf("http") < 0) {
				url = HttpUtil.BASE_URL + url;
			}
			Drawable drawable = null;
			URL Url;
			try {
				Url = new URL(url);
				drawable = Drawable.createFromStream(Url.openStream(), "");
			} catch (Exception e) {
				return null;
			}

			// 获取图片等比大小
			Rect bounds = getDefaultImageBounds(c);
			int newwidth = bounds.width();
			int newheight = bounds.height();
			double factor = 1;
			double fx = (double) drawable.getIntrinsicWidth()
					/ (double) newwidth;
			double fy = (double) drawable.getIntrinsicHeight()
					/ (double) newheight;
			factor = fx > fy ? fx : fy;
			if (factor < 1)
				factor = 1;
			newwidth = (int) (drawable.getIntrinsicWidth() / factor);
			newheight = (int) (drawable.getIntrinsicHeight() / factor);

			drawable.setBounds(0, 0, newwidth, newheight);

			/*
			 * 下面是设置固定大小图片 int height = drawable.getIntrinsicHeight(); int width
			 * = drawable.getIntrinsicWidth(); if (width < 100) {
			 * drawable.setBounds(0, 0, 40, 40); } else { WindowManager wm =
			 * (WindowManager) c .getSystemService(Context.WINDOW_SERVICE);
			 * 
			 * int width1 = wm.getDefaultDisplay().getWidth(); double scale =
			 * (width1 - 40) / width; width = width1 - 40; height = (int) scale
			 * * height; drawable.setBounds(0, 0, width, height); }
			 */
			return drawable;
		}
	}

	@SuppressWarnings("deprecation")
	public Rect getDefaultImageBounds(Context context) {
		Display display = ((Activity) context).getWindowManager()
				.getDefaultDisplay();
		int width = display.getWidth();
		int height = (int) (width * 3 / 4);

		Rect bounds = new Rect(0, 0, width, height);
		return bounds;
	}
}
