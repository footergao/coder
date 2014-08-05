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
import com.likou.application.Config;
import com.likou.model.BaseGridItem;
import com.nostra13.universalimageloader.core.ImageLoader;

public class GridViewAdapter extends BaseAdapter {
	private List<BaseGridItem> items;
	private Context context;

	public GridViewAdapter(Context context, List<BaseGridItem> items) {
		this.items = items;
		this.context = context;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return ((BaseGridItem) getItem(position)).id;
	}

	@Override
	public View getView(final int position, View view, ViewGroup parent) {
		Holder holder = null;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(R.layout.row_grid_2item, null);
			holder = new Holder();
			holder.itemImage = (ImageView) view.findViewById(R.id.img_photo);
			holder.itemText = (TextView) view.findViewById(R.id.tv_name);
			view.setTag(holder);
		} else {
			holder = (Holder) view.getTag();
		}

		BaseGridItem item = (BaseGridItem) getItem(position);
		holder.itemText.setText(item.name);
		ImageLoader.getInstance().displayImage(
				Config.THUMBNAILS_PATH + item.photo, holder.itemImage);

		return view;
	}

	static class Holder {
		ImageView itemImage;
		TextView itemText;
	}
}
