package com.kevin.volleygson.utils;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.kevin.volleygson.net.GsonRequest;
import com.kevin.volleygson.net.NetResponseListener;
import com.kevin.volleygson.net.RequestManager;
import com.kevin.volleygson.request.BaseRequest;
import com.kevin.volleygson.response.BaseResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by kevin on 4/14/16.
 * Email:lylwo317@gmail.com
 */
public class RequestUtil {
    private static final String TAG = "RequestUtil";

    private static RequestUtil sInstance;
    private final Context sContext;
    private final RequestManager sRequestManager;
    private Toast mToast;

    private RequestUtil(final Context context) {
        sContext = context;
        sRequestManager = RequestManager.getInstance(context);
    }

    public static RequestUtil getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (RequestUtil.class) {
                if (sInstance == null) {
                    sInstance = new RequestUtil(context);
                }
            }
        }
        return sInstance;
    }

    /**
     * 向服务器请求Json数据
     *
     * @param request       请求对象
     * @param responseClass 响应的类的类型
     * @param netListener   响应回调接口
     * @param <T>           响应的类的类型
     * @throws IllegalArgumentException
     */
    public <T extends BaseResponse> void queryPageInfo(final BaseRequest request, Class<T> responseClass, final NetResponseListener<T> netListener) throws IllegalArgumentException {

        sendRequest(request, responseClass, new Response.Listener<T>() {

            @Override
            public void onResponse(T response) {
                if (response != null) {
                    if (netListener != null) {
                        netListener.onResponseSuccess(response);
                        netListener.onFinally();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtils.e(sContext,TAG,error.toString(),error);
                if (netListener != null) {
                    if (!netListener.onResponseError(error)) {//如果返回值为false，就使用默认处理
                        //默认处理
                        showNetErrorToast(error);
                    }
                    netListener.onFinally();
                }
            }
        });
    }

    private void showNetErrorToast(VolleyError errorInfo) {
        if (errorInfo != null) {
            Toast.makeText(sContext, errorInfo.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private <T extends BaseResponse> void sendRequest(final BaseRequest request, Class<T> responseClass, Response.Listener<T> responseListener, Response.ErrorListener errorListener) throws IllegalArgumentException {

        final String url = request.getUrl();

        if (TextUtils.isEmpty(url)) {
            throw new IllegalArgumentException("url can not be null!");
        } else if (TextUtils.isEmpty(request.getTag())) {
            throw new IllegalArgumentException("tag can not be null!");
        }

        final Map<String, String> map = request.getRequestMap();
        GsonRequest<T> gsonRequest = new GsonRequest<>(sContext,
                url, map, responseClass, responseListener, errorListener);
        sRequestManager.add(gsonRequest, request.getTag());
    }

    /**
     * 移除网络请求
     *
     * @param tag
     */
    public void removeRequest(String tag) {
        sRequestManager.cancel(tag);
    }

}
