/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.maiyu.hrssc.service.kefu;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.Error;
import com.hyphenate.helpdesk.callback.Callback;
import com.hyphenate.helpdesk.easeui.util.IntentBuilder;
import com.maiyu.hrssc.R;

import java.util.Locale;
import java.util.Random;


public class LoginActivity extends AppCompatActivity {

	private static final String TAG = "LoginActivity";

	private boolean progressShow;
	private ProgressDialog progressDialog;




	/*@Override
	protected void onResume() {
		super.onResume();
		UIProvider.getInstance().getNotifier().reset();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}*/

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		Intent intent = getIntent();

		//ChatClient.getInstance().isLoggedInBefore() 可以检测是否已经登录过环信，如果登录过则环信SDK会自动登录，不需要再次调用登录操作
		if (ChatClient.getInstance().isLoggedInBefore()) {
			progressDialog = getProgressDialog();
			progressDialog.setMessage(getResources().getString(R.string.is_contact_customer));
			progressDialog.show();
			toChatActivity();
		} else {
			//随机创建一个用户并登录环信服务器
			createRandomAccountThenLoginChatServer();
		}

	}

	/**
	 * demo为了演示功能，此处随机生成账号。
	 * @return
	 */
	private String getRandomAccount(){
		String val = "";
		Random random = new Random();
		for(int i = 0; i < 15; i++){
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; //输出字母还是数字
			if("char".equalsIgnoreCase(charOrNum)){// 字符串
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; //取得大写字母还是小写字母
				val += (char) (choice + random.nextInt(26));
			}else if("num".equalsIgnoreCase(charOrNum)){// 数字
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val.toLowerCase(Locale.getDefault());
	}


	private void createRandomAccountThenLoginChatServer() {
		// 自动生成账号,此处每次都随机生成一个账号,为了演示.正式应从自己服务器获取账号
		final String account = getRandomAccount();
		final String userPwd = "123456";

		progressDialog = getProgressDialog();
		progressDialog.setMessage(getString(R.string.system_is_regist));
		progressDialog.show();
		// createAccount to huanxin server
		// if you have a account, this step will ignore
		ChatClient.getInstance().createAccount(account, userPwd, new Callback() {
			@Override
			public void onSuccess() {
				Log.d(TAG, "demo register success");
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						//登录环信服务器
						login(account, userPwd);
					}
				});
			}

			@Override
			public void onError(final int errorCode, String error) {
				runOnUiThread(new Runnable() {
					public void run() {
						if(progressDialog != null && progressDialog.isShowing()){
							progressDialog.dismiss();
						}
						if (errorCode == Error.NETWORK_ERROR){
							Toast.makeText(getApplicationContext(), "网络不可用", Toast.LENGTH_SHORT).show();
						}else if (errorCode == Error.USER_ALREADY_EXIST){
							Toast.makeText(getApplicationContext(), "用户已经存在", Toast.LENGTH_SHORT).show();
						}else if(errorCode == Error.USER_AUTHENTICATION_FAILED){
							Toast.makeText(getApplicationContext(), "无开放注册权限", Toast.LENGTH_SHORT).show();
						} else if (errorCode == Error.USER_ILLEGAL_ARGUMENT){
							Toast.makeText(getApplicationContext(), "用户名非法", Toast.LENGTH_SHORT).show();
						}else {
							Toast.makeText(getApplicationContext(), "注册失败", Toast.LENGTH_SHORT).show();
						}
						finish();
					}
				});
			}

			@Override
			public void onProgress(int progress, String status) {

			}
		});
	}

	private ProgressDialog getProgressDialog() {
		if (progressDialog == null) {
			progressDialog = new ProgressDialog(LoginActivity.this);
			progressDialog.setCanceledOnTouchOutside(false);
			progressDialog.setOnCancelListener(new OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					progressShow = false;
				}
			});
		}
		return progressDialog;
	}

	private void login(final String uname, final String upwd) {
		progressShow = true;
		progressDialog = getProgressDialog();
		progressDialog.setMessage(getResources().getString(R.string.is_contact_customer));
		if (!progressDialog.isShowing()) {
			progressDialog.show();
		}
		// login huanxin server
		ChatClient.getInstance().login(uname, upwd, new Callback() {
			@Override
			public void onSuccess() {
				Log.d(TAG, "demo login success!");
				if (!progressShow) {
					return;
				}
				toChatActivity();
			}

			@Override
			public void onError(int code, String error) {
				Log.e(TAG, "login fail,code:" + code + ",error:" + error);
				if (!progressShow) {
					return;
				}
				runOnUiThread(new Runnable() {
					public void run() {
						progressDialog.dismiss();
						Toast.makeText(LoginActivity.this,
								getResources().getString(R.string.is_contact_customer_failure_seconed),
								Toast.LENGTH_SHORT).show();
						finish();
					}
				});
			}

			@Override
			public void onProgress(int progress, String status) {

			}
		});
	}

	private void toChatActivity() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (!LoginActivity.this.isFinishing())
					progressDialog.dismiss();

				//此处演示设置技能组,如果后台设置的技能组名称为[shouqian|shouhou],这样指定即分配到技能组中.
				//为null则不按照技能组分配,同理可以设置直接指定客服scheduleAgent
				/*Intent intent = new IntentBuilder(LoginActivity.this)
                        .setServiceIMNumber("kefuchannelimid_039309") //获取地址：kefu.easemob.com，“管理员模式 > 渠道管理 > 手机APP”页面的关联的“IM服务号”
						.build();
				startActivity(intent);
				finish();*/

				// 进入主页面
				Intent intent = new IntentBuilder(LoginActivity.this)
						.setTargetClass(ChatActivity.class)
						//.setVisitorInfo(MessageHelper.createVisitorInfo())
						.setServiceIMNumber("kefuchannelimid_039309")
						//.setScheduleAgent(MessageHelper.createAgentIdentity("ceshiok1@qq.com"))
						//.setShowUserNick(true)
						//.setBundle(bundle)
						.build();
				startActivity(intent);
				finish();

			}
		});
	}

}
