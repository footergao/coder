package com.likou.adapter;

import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.likou.R;
import com.likou.application.Config;
import com.likou.model.BaseGridItem;
import com.likou.util.MyGridView;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ListViewAdapter extends BaseAdapter {
	private List<Map<String, Object>> items;// 父亲
	private Activity context;
	private GridViewAdapter adapter;
	private List<BaseGridItem> clist;

	public ListViewAdapter(Activity context, List<Map<String, Object>> items) {
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
		return position;
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getView(final int position, View view, ViewGroup parent) {
		Holder holder = null;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(R.layout.row_style,
					null);
			holder = new Holder();
			holder.name = (TextView) view.findViewById(R.id.tv_style);
			holder.grid = (MyGridView) view.findViewById(R.id.grv_pic);
			view.setTag(holder);
		} else {
			holder = (Holder) view.getTag();
		}

		holder.name.setText((String) items.get(position).get("name"));

		clist = (List<BaseGridItem>) items.get(position).get("list");

		adapter = new GridViewAdapter(clist);
		holder.grid.setAdapter(adapter);
		holder.grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {}
		});

		return view;
	}

	static class Holder {
		TextView name;
		GridView grid;
	}

	public class GridViewAdapter extends BaseAdapter {

		private List<BaseGridItem> list;

		public GridViewAdapter(List<BaseGridItem> list) {
			this.list = list;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return ((BaseGridItem) getItem(position)).id;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Holder1 holder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(
						R.layout.row_grid_1item, null);
				holder = new Holder1();
				holder.photo = (ImageView) convertView
						.findViewById(R.id.img_photo);
				holder.name = (TextView) convertView.findViewById(R.id.tv_name);
				convertView.setTag(holder);
			} else {
				holder = (Holder1) convertView.getTag();
			}

			BaseGridItem item = (BaseGridItem) getItem(position);
			holder.name.setText(item.name);
			ImageLoader.getInstance().displayImage(
					Config.THUMBNAILS_PATH + item.photo, holder.photo);
			return convertView;
		}
	}

	static class Holder1 {
		ImageView photo;
		TextView name;
	}
}
