package com.kevin.volleygson.response;

import com.google.gson.Gson;

/**
 * Created by kevin on 4/14/16.
 * Email:lylwo317@gmail.com
 */
public class BaseResponse {

    /**
     * 生成接口的关键信息.
     *
     * @return 接口的关键信息
     */
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
