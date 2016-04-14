package com.kevin.volleygson.net;

import com.android.volley.VolleyError;

/**
 * 网络响应接口
 */
public interface NetResponseListener<T> {
    /**
     * 当code==1时(业务成功)，这个方法会被调用
     * @param response 对应的Response对象
     */
    void onResponseSuccess(T response);

    /**
     * 处理业务逻辑错误
     * @return 如果是False，则表示这个业务错误会被默认的业务错误处理程序处理(也就是弹出Toast)，True则不会被处理。这两个都会将一些特定的错误上传友盟
     */
    boolean onResponseFailed();
    /**
     * 处理网络错误
     * @param volleyError 网络错误包装类
     * @return 如果是False，则表示这个网络错误会被默认的网络错误处理程序处理(也就是弹出Toast)，True则不会被处理。这两个都会将一些特定的错误上传友盟
     */
    boolean onResponseError(VolleyError volleyError);

    /**
     * 无论是否发生会调用这个接口
     */
    void onFinally();
}
