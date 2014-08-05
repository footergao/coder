package com.likou.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.likou.R;
import com.likou.application.Config;
import com.likou.model.BaseGridItem;
import com.nostra13.universalimageloader.core.ImageLoader;

public class BrandAdapter extends BaseAdapter {

	private Context mContex;
	private List<String> videoTitles, videoURL, authorName, authorPhoto;

	public BrandAdapter(Context context, List<String> videoTitles,
			List<String> videoURL, List<String> authorName,
			List<String> authorPhoto) {
		this.mContex = context;
		this.videoTitles = videoTitles;
		this.videoURL = videoURL;
		this.authorName = authorName;
		this.authorPhoto = authorPhoto;
		
		
		Log.v("gl", "videoTitles:"+videoTitles.size());
		Log.v("gl", "videoURL:"+videoURL.size());
		Log.v("gl", "authorName:"+authorName.size());
		Log.v("gl", "authorPhoto:"+authorPhoto.size());
	}

	@Override
	public int getCount() {
		return videoTitles.size();
	}

	@Override
	public Object getItem(int arg0) {
		return videoTitles.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View view, ViewGroup group) {
		Holder holder = null;
		if (view == null) {
			view = LayoutInflater.from(mContex).inflate(
					R.layout.discount_item, null);
			holder = new Holder();
			holder.img_photo = (ImageView) view.findViewById(R.id.img_photo);
			holder.tv_name = (TextView) view.findViewById(R.id.tv_name);
			holder.tv_title = (TextView) view.findViewById(R.id.tv_title);
			view.setTag(holder);
		} else {
			holder = (Holder) view.getTag();
		}


		ImageLoader.getInstance().displayImage(
				authorPhoto.get(position), holder.img_photo);
		holder.tv_name.setText(authorName.get(position));
		holder.tv_title.setText(videoTitles.get(position));

		return view;
	}

	static class Holder {
		ImageView img_photo;
		TextView tv_name;
		TextView tv_title;
	}

}
