package com.likou.activity.main;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.likou.R;
import com.likou.activity.common.BaseFragmentActivity;
import com.likou.application.Config;
import com.likou.model.Member;

public class RegisterActivity2 extends BaseFragmentActivity {

	// 注册接口
	// private static final String ACTION_REGISTER =
	// "api/m/member_register.action?";
	private static final String ACTION_REGISTER = "/member/register/%s/%s/%s";
	private static final int API_REGISTER = 0x01;

	private Button bt_back;
	private TextView title;
	private EditText et_username;
	private EditText et_password;
	private EditText et_passagaion;
	private Button bt_finish;
	private String mUserName;
	private String mPassword;
	private String mTelephone;
	private String mPassword2;

	@Override
	protected int centerLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.glk_register2;
	}

	@Override
	protected void initCenter(View centerView) {
		// TODO Auto-generated method stub
		
		setTitleTextRes("用户注册");
		
		
		bt_back = (Button) findViewById(R.id.top_btn_left);
		title = (TextView) findViewById(R.id.top_btn_center);
		findViewById(R.id.top_btn_right).setBackgroundColor(000000);

		et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_pass);
		et_passagaion = (EditText) findViewById(R.id.et_passagain);

		bt_finish = (Button) findViewById(R.id.bt_finish);
		
		
		
		
		
	showRightView(true, "用已有登录账号", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startToActivity(LoginActivity.class, null);
			}
		});
	
	
	showLeftView(true,new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
		}
	});
		
		
		
		
		
		
		
		
		
		
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		mTelephone = getIntent().getStringExtra("telephone");
	}

	@Override
	protected void initAction() {
		// TODO Auto-generated method stub
		bt_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
	}

	protected void getInfo() {
		mPassword = et_password.getText().toString().trim();
		mPassword2 = et_passagaion.getText().toString().trim();
		mUserName = et_username.getText().toString().trim();
	}

	protected boolean passSameCheck() {
		if (mPassword.length() > 0 && mPassword.equals(mPassword2)) {
			return true;
		}
		return false;
	}

	@Override
	protected void httpResponse(String json, int action) {
		super.httpResponse(json, action);
		switch (action) {
		case API_REGISTER:
			registerHandler(json);
			break;
		default:
			break;
		}
	}

	// @SuppressWarnings("rawtypes")
	// private void registerHandler(String xml) {
	// Class[] clazz = { Member.class };
	// try {
	// mMember = XmlUtils.toBean(xml, clazz);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// if (mMember != null && mMember.memberId > 0) {
	// showToast(R.string.registerSucc);
	// setResult(11);
	// finish();
	// }
	// }

	private void registerHandler(String json) {
		mMember = gson.fromJson(json, Member.class);
		if (mMember != null && mMember.memberId > 0) {
			showToast(R.string.registerSucc);
			setResult(11);
			finish();
		}
	}

	// private String getUrl() {
	// Map<String, String> params = new HashMap<String, String>();
	// params.put("telephone", mTelephone);
	// params.put("pwd", mPassword);
	// params.put("memberName", mUserName);
	//
	// addBaseMap(params);
	// String url = null;
	// try {
	// url = ParamAnalysis.getUrlOfParams(params);
	// return new StringBuffer(Config.HTTP_URL).append(ACTION_REGISTER)
	// .append(url).toString();
	// } catch (UnsupportedEncodingException e) {
	// e.printStackTrace();
	// }
	// return null;
	// }

	private String getUrl() {
		String url = String.format(ACTION_REGISTER, mTelephone, mUserName,
				mPassword);
		return new StringBuffer(Config.WEBSERVICE_URL).append(url).toString();
	}

}
