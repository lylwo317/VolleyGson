/*
 * -----------------------------------------------------------------
 * Copyright (C) 2014, by Peanut Run, Shenzhen, All rights reserved.
 * -----------------------------------------------------------------
 *
 * File: RequestManager.java
 * Author: JiongBull
 * Version: 1.0
 * Create: 2014/9/25
 *
 * Changes (from 2014/9/25)
 * -----------------------------------------------------------------
 * 2014/9/25 : 创建 RequestManager.java (JiongBull);
 * -----------------------------------------------------------------
 */

package com.kevin.volleygson.net;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Volley的Request管理器.
 */
public class RequestManager {

    /**
     * 单例对象.
     */
    private static volatile RequestManager sInstance;

    /**
     * Context.
     */
    private static Context sContext;

    /**
     * 网络请求队列.
     */
    private static RequestQueue sRequestQueue;

    /**
     * Volley的Request管理器.
     */
    private RequestManager() {
    }

    /**
     * 获取Volley的Request管理器实例化对象.
     *
     * @param context Context
     * @return 单例的实例
     */
    public static RequestManager getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (RequestManager.class) {
                if (sInstance == null) {
                    sContext = context;
                    sInstance = new RequestManager();
                    sRequestQueue = Volley.newRequestQueue(context);
                    sRequestQueue.start();
                }
            }
        }
        return sInstance;
    }

    /**
     * 添加网络请求.
     *
     * @param request 网络请求
     * @param tag 请求标签
     * @param <T> 请求类型
     */
    public <T> void add(final Request<T> request, final Object tag) {
        if (tag != null) {
            request.setTag(tag);
        }
        sRequestQueue.add(request);
    }

    /**
     * 取消网络请求.
     *
     * @param tag 请求标签
     */
    public void cancel(final Object tag) {
        if (tag != null) {
            sRequestQueue.cancelAll(tag);
        }
    }
}
