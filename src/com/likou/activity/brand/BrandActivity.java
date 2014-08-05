package com.likou.activity.brand;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.reflect.TypeToken;
import com.likou.R;
import com.likou.activity.common.BaseFragmentActivity;
import com.likou.adapter.BrandAdapter;
import com.likou.application.Config;
import com.likou.model.BaseGridItem;
import com.likou.model.TopBrand;

/**
 * 
 * @author
 * 
 */
public class BrandActivity extends BaseFragmentActivity {

	// private static final String TAG = makeLogTag(BrandActivity.class);

	// private static final String ACTION_LISTTOPBRANDS =
	// "api/s/shop_listtopbrands.action?";
	private static final String ACTION_LISTTOPBRANDS = "/shop/listTopBrands/%d/%d/%d";
	private static final int API_LISTTOPBRANDS = 0x01;

	private int styleId;

	private BrandAdapter mAdapter;
	private ListView mListView;
	private Button btn_top_left;
	private TextView title;
	private Button btn_top_right;
	private TextView tv_top_right;
	// 是否是样式返回请求
	private boolean isResult;
	private List<String> videoTitles,  videoURL,authorName,authorPhoto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
		getData();
	}

	private void getData() {

		httpRequest("", API_LISTTOPBRANDS);
	}

	private void initView() {
		btn_top_left = (Button) findViewById(R.id.top_btn_left);
		btn_top_left.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		title = (TextView) findViewById(R.id.top_btn_center);
		title.setText(R.string.app_name);
		// 设定右边按钮样式
		btn_top_right = (Button) findViewById(R.id.top_btn_right);
		btn_top_right.setVisibility(View.INVISIBLE);

	}

	@Override
	protected void httpResponse(String xml, int action) {
		super.httpResponse(xml, action);
		switch (action) {
		case API_LISTTOPBRANDS:
			// brandsHandler(xml);
			parseHtmlVideo(xml);
			mAdapter = new BrandAdapter(this, videoTitles, videoURL, authorName, authorPhoto);
			mListView.setAdapter(mAdapter);
			break;
		default:

			break;
		}
	}

	private List<BaseGridItem> brandToItem(List<TopBrand> list) {
		List<BaseGridItem> items = new ArrayList<BaseGridItem>();
		for (TopBrand p : list) {
			BaseGridItem item = new BaseGridItem();
			item.id = p.shopId;
			item.name = p.shopName;
			item.photo = p.brandLogo;
			items.add(item);
		}
		return items;
	}


	private String getBrandUrl() {
		String url = String.format(ACTION_LISTTOPBRANDS, styleId, current_page,
				PAGE_SIZE);
		return new StringBuffer(Config.WEBSERVICE_URL).append(url).toString();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if (1 == requestCode && RESULT_OK == resultCode) {
			isResult = true;
			current_page = 1;
			styleId = intent.getIntExtra("styleId", 0);
			getData();
			Log.v("gl", "aaa");
		}
	}

	/**
	 * 解析XML，提取数据
	 * 
	 * @param html
	 * @return
	 */
	private void parseHtmlVideo(String html) {}

	@Override
	protected int centerLayoutId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void initCenter(View centerView) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initAction() {
		// TODO Auto-generated method stub
		
	}
}