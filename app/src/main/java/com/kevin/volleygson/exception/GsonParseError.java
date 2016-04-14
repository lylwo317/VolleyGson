package com.kevin.volleygson.exception;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;

/**
 * Gson解析错误.
 */
public class GsonParseError extends ParseError {

    /**
     * 解析的JSON字符串.
     */
    private String mJson;

    public GsonParseError() { }

    public GsonParseError(NetworkResponse networkResponse) {
        super(networkResponse);
    }

    public GsonParseError(String json, Throwable cause) {
        super(cause);
        mJson = json;
    }

    public String getJson() {
        return mJson;
    }

    public void setJson(String json) {
        mJson = json;
    }
}

