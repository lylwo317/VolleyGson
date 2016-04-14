package com.kevin.volleygson.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 网络请求的基类，主要是包含一些共同的属性
 */
public class BaseRequest {

    protected String mTag;

    private String url;

    private Map<String, String> requestMap = new HashMap<>();

    /**
     * 生成接口的关键信息.
     *
     * @return 接口的关键信息
     */
    public String toJson() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(this);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String,String> getRequestMap(){
        return getStringMap(this.getClass());
    }

    private Map<String, String> getStringMap(Class clazz) {
        for(Field field : clazz.getDeclaredFields()) {
            SerializedName name = field.getAnnotation(SerializedName.class);
            if (name!=null) {
                try {
                    field.setAccessible(true);
                    if (field.getType() == String.class && field.get(this) == null) {
                        continue;
                    }
                    requestMap.put(name.value(), String.valueOf(field.get(this)));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        Class superClazz = clazz.getSuperclass();

        if (superClazz != null) {
            getStringMap(superClazz);
        }

        return requestMap;
    }


    public String getTag() {
        return mTag;
    }

    public void setTag(String tag) {
        mTag = tag;
    }
}
