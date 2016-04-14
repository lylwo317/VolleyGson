/*
 * -----------------------------------------------------------------
 * Copyright (C) 2014, by Peanut Run, Shenzhen, All rights reserved.
 * -----------------------------------------------------------------
 *
 * File: GsonRequest
 * Author: JiongBull
 * Version: 1.0
 * Create: 2014/9/25
 *
 * Changes (from 2014/9/25)
 * -----------------------------------------------------------------
 * 2014/9/25 : 创建 GsonRequest (JiongBull);
 * -----------------------------------------------------------------
 */
package com.kevin.volleygson.net;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.kevin.volleygson.exception.GsonParseError;
import com.kevin.volleygson.utils.LogUtils;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 可以把响应的JSON结果转换成Java对象.
 */
public class GsonRequest<T> extends Request<T> {

    /**
     * TAG.
     */
    public static final String TAG = "GsonRequest";

    /**
     * Gson.
     */
    private Gson mGson = new Gson();

    /**
     * Application Context, 注意不要传递Activity Context.
     */
    private Context mContext;

    /**
     * 对象类型.
     */
    private Class<T> mClazz;

    /**
     * 响应监听器.
     */
    private Listener<T> mListener;

    /**
     * 字符串请求参数列表.
     */
    private Map<String, String> mMap;

    /**
     * 构造方法.
     *
     * @param context       Context
     * @param method        方法类型
     * @param url           地址
     * @param map           请求参数列表
     * @param clazz         Response对象类型
     * @param listener      响应监听器
     * @param errorListener 错误监听器
     */
    public GsonRequest(Context context, int method, String url, Map<String, String> map, Class<T> clazz,
                       Listener<T> listener, ErrorListener errorListener) {
        super(method, url, errorListener);
        mContext = context;
        mClazz = clazz;
        mListener = listener;
        mMap = map;
        RetryPolicy retryPolicy = new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, -1 , DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        setRetryPolicy(retryPolicy);
    }

    /**
     * 构造方法, 使用POST形式提交.
     *
     * @param context       Context
     * @param url           地址
     * @param map           请求参数列表
     * @param clazz         Response对象类型
     * @param listener      响应监听器
     * @param errorListener 错误监听器
     */
    public GsonRequest(Context context, String url, Map<String, String> map, Class<T> clazz, Listener<T> listener,
                       ErrorListener errorListener) {
        this(context, Method.POST, url, map, clazz, listener, errorListener);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        String json = null;
        try {
            json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
//            LogUtils.d(RootApp.getContext(), "debug_d", json);
            return Response.success(mGson.fromJson(json, mClazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            /* 字符编码错误 */
            LogUtils.e(mContext, TAG, "编码错误", e);
            return Response.error(new VolleyError(e));
        } catch (JsonSyntaxException e) {
            /* JSON格式错误 */
            GsonParseError parseError = new GsonParseError(json, e);
            return Response.error(parseError);
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mMap;
    }

    /*@Override
    public RetryPolicy getRetryPolicy() {
        RetryPolicy retryPolicy = new DefaultRetryPolicy(Net.TIMEOUT, Net.RETRY,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        return retryPolicy;
    }*/
}
