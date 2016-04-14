package com.kevin.volleygson.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kevin on 4/14/16.
 * Email:lylwo317@gmail.com
 */
public class IpResponse extends BaseResponse {

    /**
     * ret : 1
     * start : -1
     * end : -1
     * country : 中国
     * province : 江苏
     * city : 苏州
     * district :
     * isp :
     * type :
     * desc :
     */

    @SerializedName("ret")
    private int ret;
    @SerializedName("start")
    private int start;
    @SerializedName("end")
    private int end;
    @SerializedName("country")
    private String country;
    @SerializedName("province")
    private String province;
    @SerializedName("city")
    private String city;
    @SerializedName("district")
    private String district;
    @SerializedName("isp")
    private String isp;
    @SerializedName("type")
    private String type;
    @SerializedName("desc")
    private String desc;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "国家："+getCountry()+"\n"+
                "省份："+getProvince()+"\n"+
                "城市："+getCity();
    }
}
