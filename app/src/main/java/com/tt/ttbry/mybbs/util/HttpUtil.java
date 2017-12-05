package com.tt.ttbry.mybbs.util;

import android.content.Context;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.tt.ttbry.mybbs.R;

import java.util.Map;
import java.util.Set;

import cz.msebera.android.httpclient.Header;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by TTBry on 2017/12/5.
 */

public class HttpUtil {
    public static final int TIME_OUT = 60000;
    private Context mContext;
    private AsyncHttpClient client;

    private static HttpUtil httpUtil;

    private HttpUtil(Context context){
        this.mContext = context;
        client = new AsyncHttpClient();
        client.setTimeout(TIME_OUT);
    }

    public synchronized static HttpUtil getInstance(Context context){
        if(httpUtil == null){
            httpUtil = new HttpUtil(context);
        }
        return httpUtil;
    }

    public void get(String url, final OnResponseHandler handler){
        if(!NetworkUtil.getInstance().isNetworkConnected(mContext)){
            Toast.makeText(mContext, R.string.network_not_available, Toast.LENGTH_SHORT).show();
            return;
        }
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == HTTP_OK){
                    if(handler != null){
                        handler.onSuccess(new String(responseBody));
                    }
                }else{
                    Toast.makeText(mContext, mContext.getString(R.string.network_error) + statusCode, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if(handler != null){
                    handler.onFailure(error);
                }
            }
        });
    }

    public void get(String url, Map<String, Object> params,  final OnResponseHandler handler){
        if(!NetworkUtil.getInstance().isNetworkConnected(mContext)){
            Toast.makeText(mContext, R.string.network_not_available, Toast.LENGTH_SHORT).show();
            return;
        }
        RequestParams requestParams = new RequestParams();
        if(params != null){
            Set<Map.Entry<String, Object>> entries = params.entrySet();
            for(Map.Entry<String, Object> entry : entries){
                requestParams.put(entry.getKey(), entry.getValue());
            }
        }
        client.get(url, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == HTTP_OK){
                    if(handler != null){
                        handler.onSuccess(new String(responseBody));
                    }
                }else{
                    Toast.makeText(mContext, mContext.getString(R.string.network_error) + statusCode, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if(handler != null){
                    handler.onFailure(error);
                }
            }
        });
    }

    public interface OnResponseHandler{
        void onSuccess(String content);
        void onFailure(Throwable error);
    }
}
