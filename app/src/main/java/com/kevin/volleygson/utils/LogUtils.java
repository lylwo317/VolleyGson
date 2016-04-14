/*
 * -----------------------------------------------------------------
 * Copyright (C) 2014, by Peanut Run, Shenzhen, All rights reserved.
 * -----------------------------------------------------------------
 *
 * File: LogUtils
 * Author: JiongBull
 * Create: 2014/9/25
 *
 * Changes (from 2014/9/25)
 * -----------------------------------------------------------------
 * 2014/9/25 : 创建 LogUtils (JiongBull);
 * -----------------------------------------------------------------
 */
package com.kevin.volleygson.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;


import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;

/**
 * 日志工具.
 */
public final class LogUtils {

    /**
     * 日志的扩展名.
     */
    public static final String LOG_EXTENSION = ".log";

    /**
     * 日志工具.
     */
    private LogUtils() {
    }

    /**
     * 输出VERBOSE级别的日志.
     *
     * @param context Context
     * @param tag     标签
     * @param content 日志内容
     */
    public static void v(final Context context, final String tag, final String content) {
        if (context == null) {
            throw new NullPointerException("context不能为空");
        }
        trace(context, Log.VERBOSE, tag, content, null);
    }

    /**
     * 输出DEBUG级别的日志.
     *
     * @param context Context
     * @param tag     标签
     * @param content 日志内容
     */
    public static void d(final Context context, final String tag, final String content) {
        if (context == null) {
            throw new NullPointerException("context不能为空");
        }
        trace(context, Log.DEBUG, tag, content, null);
    }

    /**
     * 输出INFO级别的日志.
     *
     * @param context Context
     * @param tag     标签
     * @param content 日志内容
     */
    public static void i(final Context context, final String tag, final String content) {
        if (context == null) {
            throw new NullPointerException("context不能为空");
        }
        trace(context, Log.INFO, tag, content, null);
    }

    /**
     * 输出WARN级别的日志.
     *
     * @param context Context
     * @param tag     标签
     * @param content 日志内容
     */
    public static void w(final Context context, final String tag, final String content) {
        if (context == null) {
            throw new NullPointerException("context不能为空");
        }
        trace(context, Log.WARN, tag, content, null);
    }

    /**
     * 输出ERROR级别的日志.
     *
     * @param context   Context
     * @param tag       标签
     * @param content   日志内容
     * @param throwable 异常
     */
    public static void e(final Context context, final String tag, final String content, final Throwable throwable) {
        if (context == null) {
            throw new NullPointerException("context不能为空");
        }
        trace(context, Log.ERROR, tag, content, throwable);
    }

    /**
     * 输出日志.
     *
     * @param context   Context
     * @param logLevel  日志等级
     * @param tag       标签
     * @param content   输出内容
     * @param throwable 异常信息, 若没有异常可为空
     */
    private static void trace(final Context context, final int logLevel, final String tag, final String content, final Throwable throwable) {
        String outputContent = content;

        /* 日志信息输出到控制台 */
        switch (logLevel) {
            case Log.VERBOSE:
                Log.v(tag, outputContent);
                break;
            case Log.DEBUG:
                Log.d(tag, outputContent);
                break;
            case Log.INFO:
                Log.i(tag, outputContent);
                break;
            case Log.WARN:
                Log.w(tag, outputContent);
                break;
            case Log.ERROR:
                if (throwable != null) {
                    StringBuilder sbContent = new StringBuilder();
                    StringWriter stringWriter = new StringWriter();
                    PrintWriter printWriter = new PrintWriter(stringWriter);
                    throwable.printStackTrace(printWriter);
                    sbContent.append(content);
                    sbContent.append(SysUtils.getLineSeparator());
                    sbContent.append("#--------------------------------");
                    sbContent.append(SysUtils.getLineSeparator());
                    sbContent.append(stringWriter.toString());
                    sbContent.append("#--------------------------------");
                    sbContent.append(SysUtils.getLineSeparator());
                    outputContent = sbContent.toString();
                }
                Log.e(tag, outputContent);
                break;
            default:
                Log.v(tag, outputContent);
                break;
        }
    }
}
