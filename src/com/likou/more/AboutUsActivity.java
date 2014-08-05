package com.likou.more;

import java.util.HashMap;
import java.util.Map;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.likou.R;
import com.likou.activity.common.BaseActivity;
import com.likou.application.Config;
import com.likou.model.SystemOption;
import com.likou.more.service.IMoreService;
import com.likou.util.BaseData;
import com.likou.util.ParamAnalysis;
import com.likou.util.XmlUtils;

public class AboutUsActivity extends BaseActivity {
	private IMoreService service;
	Button btn_right;
	private TextView about_us;
	private StringBuffer httpUrl;
	private double coordinateSLX = 0.0, coordinateSLY = 0.0;

	@Override
	protected int centerLayoutId() {
		return R.layout.about_us;
	}

	@Override
	protected void initCenter(View centerView) {
		// // TODO Auto-generated method stub
		// service=new MoreServiceImpl(WebServiceUtil.getInstance(this));
		// btn_right = (Button)findViewById(R.id.top_btn_right);
		// btn_right.setBackgroundColor(00000000);
		// about_us=(TextView)findViewById(R.id.about_us_textView);
		// queryAboutUs();
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initAction() {
		// TODO Auto-generated method stub

	}

	/**
	 * 关于我们
	 */
	private void queryAboutUs() {
		try {
			Map<String, String> params = new HashMap<String, String>();

			params.put("input_charset", BaseData.CHARSET);
			params.put("partner", BaseData.partner);
			params.put("creatorId", "2");
			params.put("pwd", "111111");
			params.put("coordinateX", String.valueOf(coordinateSLX));
			params.put("coordinateY", String.valueOf(coordinateSLY));
			params.put("flag", "1");

			String sign = ParamAnalysis.getSignOfParams(params,
					BaseData.encryptKey);
			params.put("sign", sign);
			String url = ParamAnalysis.getUrlOfParams(params);
			httpUrl = new StringBuffer(Config.HTTP_URL).append(
					"api/so/systemOption_aboutUs.action?").append(url);

			Log.v("xxx", httpUrl.toString());
			String xml = service.getAboutUs(httpUrl.toString());
			// String xml = APIHandler.getXML(httpUrl.toString());
			Log.v("xxx", xml);
			Class[] clazz = { SystemOption.class };
			try {
				SystemOption phoneModel = XmlUtils.toBean(xml, clazz);
				if (phoneModel != null) {
					String content = phoneModel.getContent();
					about_us.setText(content);

				}

			} catch (Exception e) {
				// toast(e.getMessage());
				e.printStackTrace();
			}
		} catch (Exception e) {
			// toast(e.getMessage());
			e.printStackTrace();
		}
	}

}
