package com.likou.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.likou.R;
import com.likou.model.SlidingMenuItem;

public class SlidingMenuAdapter extends BaseAdapter {

	private Context mContext;
	private List<SlidingMenuItem> mList;

	public SlidingMenuAdapter(Context context, List<SlidingMenuItem> list) {
		this.mContext = context;
		this.mList = list;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.lk_item_sliding, null);
			holder = new Holder();
			holder.image = (ImageView) convertView.findViewById(R.id.menuicon);
			holder.text = (TextView) convertView.findViewById(R.id.catalog);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		SlidingMenuItem item = (SlidingMenuItem) getItem(position);
		holder.image.setBackgroundResource(item.icon);
		holder.text.setText(item.name);
		return convertView;
	}

	static class Holder {
		ImageView image;
		TextView text;
	}

}
