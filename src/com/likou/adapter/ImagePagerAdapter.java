package com.likou.adapter;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.likou.application.Config;
import com.likou.model.BaseGridItem;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ImagePagerAdapter extends PagerAdapter {


	private Context activity;
	private List<BaseGridItem> mList;

	public ImagePagerAdapter(Context activity, List<BaseGridItem> list) {
		this.activity = activity;
		this.mList = list;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return (view == object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		final BaseGridItem item = mList.get(position);
		ImageView imageView = new ImageView(activity);
		ImageLoader.getInstance().displayImage(
				Config.THUMBNAILS_PATH + item.photo, imageView);
		imageView.setAdjustViewBounds(true);
		imageView.setScaleType(ScaleType.FIT_XY);
		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				toActivity(item);
			}
		});
		((ViewPager) container).addView(imageView, 0);
		return imageView;
	}

	protected void toActivity(BaseGridItem item) {
		switch (item.adType) {}
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager) container).removeView((ImageView) object);
	}

}
