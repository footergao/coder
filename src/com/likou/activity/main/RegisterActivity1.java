package com.likou.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.likou.R;
import com.likou.activity.common.BaseFragmentActivity;
import com.likou.application.Config;
import com.likou.model.Member;

public class RegisterActivity1 extends BaseFragmentActivity {

	// private static final String ACTION_VERIFYTELEPHONE =
	// "api/m/member_verifytelephone.action?";
	// private static final int API_VERIFYTELEPHONE = 0x01;

	// 注册手机验证接口
	// private static final String ACTION_SENDCODE =
	// "api/m/member_send_verification_code.action?";
	private static final String ACTION_SENDCODE = "/member/sendVerificationCode/%s";
	private static final int API_SENDCODE = 0x02;

	private EditText et_tel;
	private EditText et_code;
	private Button bt_sendcode;
	private Button bt_next;
	private Button bt_back;
	private TextView title;
	private String mTelephone;
	private Member mMember;
	private LinearLayout ll_code;
	private int number = 0;
	private Thread mThread;
	
	
	@Override
	protected int centerLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.glk_register1;
	}

	@Override
	protected void initCenter(View centerView) {
		// TODO Auto-generated method stub

		setTitleTextRes("忘记密码");
		
		bt_back = (Button) findViewById(R.id.top_btn_left);
		title = (TextView) findViewById(R.id.top_btn_center);
		title.setText(R.string.register);
		findViewById(R.id.top_btn_right).setBackgroundColor(000000);

		ll_code = (LinearLayout) findViewById(R.id.ll_code);

		et_tel = (EditText) findViewById(R.id.et_tel);
		et_code = (EditText) findViewById(R.id.et_code);
		bt_sendcode = (Button) findViewById(R.id.bt_sendcode);
		bt_next = (Button) findViewById(R.id.bt_next);
	
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		mThread = new Thread() {
			@Override
			public void run() {
				// 5分钟
				try {
					Thread.sleep(1000 * 60 * 3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				number = 0;
			}
		};
	}

	@Override
	protected void initAction() {
		// TODO Auto-generated method stub
		
	}
	


//	initAction() {
//		bt_back.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				onBackPressed();
//			}
//		});
//		bt_sendcode.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// 取得信息
//				mTelephone = et_tel.getText().toString().trim();
//
//				number++;
//				// 如果大于3次就暂停5分钟
//				if (number > 3) {
//					showToast(R.string.sendCodeError);
//					// 判断线程是否还存在
//					if (!mThread.isAlive()) {
//						mThread.start();
//					}
//				} else {
//					if (mTelephone.length() > 0 && mTelephone.length() == 11) {
//						// 发送验证码
//						mProgressDialog = getDefaultDialog(R.string.sendCodeMessage);
//						mProgressDialog.show();
//						httpRequest(getUrl(), API_SENDCODE);
//					} else {
//						showToast(R.string.teltphoneError);
//					}
//				}
//			}
//		});
//		bt_next.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				if (codeCheck()) {
//					Intent intent = new Intent(RegisterActivity1.this,
//							RegisterActivity2.class);
//					intent.putExtra("telephone", mTelephone);
//					startActivityForResult(intent, 10);
//				} else {
//					showToast(R.string.codeError);
//				}
//			}
//		});
//	}

	// /**
	// * 验证电话号码
	// */
	// protected void verifyTelephone() {
	// mProgressDialog = getDefaultDialog(R.string.vertifyTelephone);
	// mProgressDialog.show();
	// httpRequest(getVertifyTelephoneMap(), API_VERIFYTELEPHONE);
	// }

	// /**
	// * telephone：会员手机号，String型，不为空。 partner：应用标识。String型。
	// * input_charset：字符编码格式。String型。 sign：签名。String型，DES加密。
	// *
	// * @return
	// */
	// private String getVertifyTelephoneMap() {
	// Map<String, String> map = new HashMap<String, String>();
	// map.put("telephone", mTelephone);
	//
	// addBaseMap(map);
	//
	// try {
	// String url = ParamAnalysis.getUrlOfParams(map);
	// return new StringBuffer(Config.HTTP_URL)
	// .append(ACTION_VERIFYTELEPHONE).append(url).toString();
	// } catch (UnsupportedEncodingException e) {
	// e.printStackTrace();
	// }
	// return null;
	// }

	/**
	 * 验证码验证
	 * 
	 * @return
	 */
	protected boolean codeCheck() {
		if (et_code.getText().toString().equals(mMember.code))
			return true;
		return false;
	}

	// protected String getUrl() {
	// Map<String, String> params = new HashMap<String, String>();
	// params.put("telephone", mTelephone);
	//
	// addBaseMap(params);
	// try {
	// String url = ParamAnalysis.getUrlOfParams(params);
	// return new StringBuffer(Config.HTTP_URL).append(ACTION_SENDCODE)
	// .append(url).toString();
	// } catch (UnsupportedEncodingException e) {
	// e.printStackTrace();
	// }
	// return null;
	// }

	protected String getUrl() {
		String url = String.format(ACTION_SENDCODE, mTelephone);
		return new StringBuffer(Config.WEBSERVICE_URL).append(url).toString();
	}

	@Override
	protected void httpResponse(String xml, int action) {
		super.httpResponse(xml, action);
		switch (action) {
		// case API_VERIFYTELEPHONE:
		// verify(xml);
		// break;
		case API_SENDCODE:
			codeHandler(xml);
		default:
			break;
		}
	}

	// /**
	// * 可用手机号： <result>1</result> 不可用手机号：<result>-1</result>
	// *
	// * @param xml
	// */
	// private void verify(String xml) {
	// if (xml.contains("-1")) {
	// showToast(R.string.vertifyTelephoneError);
	// } else {
	// mProgressDialog = getDefaultDialog(R.string.sendCodeMessage);
	// mProgressDialog.show();
	// httpRequest(getUrl(), API_SENDCODE);
	// }
	// }

	// /**
	// * 100 发送成功 101 验证失败 102 短信不足 103 操作失败 104 非法字符 105 内容过多 106 号码过多 107 频率过快
	// * 108 号码内容空 110 禁止频繁单条发送 112 号码错误 113 定时时间格式不对 114 账号被锁 116 禁止接口发送 117
	// * 绑定IP不正确 120 系统升级
	// *
	// * @param xml
	// */
	// @SuppressWarnings("rawtypes")
	// private void codeHandler(String xml) {
	// Class[] clazz = { Member.class };
	// try {
	// mMember = XmlUtils.toBean(xml, clazz);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// if (mMember != null && mMember.stat == 100) {
	// ll_code.setVisibility(View.VISIBLE);
	// }
	// }

	private void codeHandler(String json) {
		mMember = gson.fromJson(json, Member.class);
		if (mMember != null && mMember.stat == 100) {
			ll_code.setVisibility(View.VISIBLE);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 10 && resultCode == 11) {
			finish();
		}
	}


}
