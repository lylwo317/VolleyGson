package com.kevin.volleygson.utils;

import android.provider.Settings;
import android.text.TextUtils;
import android.text.format.DateFormat;

import com.kevin.volleygson.constants.Times;

/**
 * Created by kevin on 4/14/16.
 * Email:lylwo317@gmail.com
 */
public class TimeUtils {
    /**
     * 获取当前时间的描述(yyyy-MM-dd HH:mm:ss).
     *
     * @return 当前时间的描述
     */
    public static String getCurDateTimeText() {
        long curMillis = System.currentTimeMillis();
        return TimeUtils.format(curMillis, Times.YYYY_MM_DD_KK_MM_SS);
    }

    /**
     * 获取当前时间的描述(yyyy-MM-dd).
     *
     * @return 当前时间的描述
     */
    public static String getCurDateText() {
        long curMillis = System.currentTimeMillis();
        return TimeUtils.format(curMillis, Times.YYYY_MM_DD);
    }

    /**
     * 格式化时间戳.
     *
     * @param millis 时间戳
     * @param fmt    时间格式
     * @return 格式化时间文本
     */
    public static String format(final long millis, final String fmt) throws IllegalArgumentException {
        if (TextUtils.isEmpty(fmt)) {
            throw new IllegalArgumentException("fmt不能为空");
        }
        return (String) DateFormat.format(fmt, millis);
    }

}
