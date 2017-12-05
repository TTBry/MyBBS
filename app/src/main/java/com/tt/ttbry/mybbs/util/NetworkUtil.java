package com.tt.ttbry.mybbs.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Looper;
import android.widget.Toast;

/**
 * 网络类型判断工具类
 * 
 * @author luochun
 * 
 */
public class NetworkUtil {

	private static NetworkUtil networkUtil = null;

	public static NetworkUtil getInstance() {
		if (networkUtil == null) {
			networkUtil = new NetworkUtil();
		}
		return networkUtil;
	}

	public boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		if(Looper.myLooper() == Looper.getMainLooper()) {
			Toast.makeText(context, "请检查网络连接", Toast.LENGTH_SHORT).show();
		}
		return false;
	}
	
	public boolean isNetworkConnectedNoTip(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	public int getConnectedType(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
				return mNetworkInfo.getType();
			}
		}
		return -1;
	}

	public boolean isWiFi(Context context) {
		if (context != null) {
			return getConnectedType(context) == ConnectivityManager.TYPE_WIFI;
		}
		return false;
	}
}
