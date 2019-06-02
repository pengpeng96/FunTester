package com.fun.moco

import com.fun.base.bean.Result
import com.github.dreamhead.moco.ResponseHandler
import com.github.dreamhead.moco.procedure.LatencyProcedure
import com.google.common.collect.FluentIterable
import net.sf.json.JSONObject

import java.util.concurrent.TimeUnit

import static com.github.dreamhead.moco.Moco.*
import static com.github.dreamhead.moco.internal.ApiUtils.textToResource
import static com.github.dreamhead.moco.util.Iterables.asIterable

/**
 * responsehandle获取
 */
@SuppressWarnings("all")
class MocoResponse extends MocoRequest {

/**
 * 返回文本信息
 * @param content
 * @return
 */
    static ResponseHandler contentResponse(String content) {
        with content
    }

/**
 * 设置json格式的返回值
 * <p>
 *  json格式转成string返回
 * </p>
 * @param json
 * @return
 */
    static ResponseHandler jsonResponse(JSONObject json) {
        with json.toString()
    }

/**
 * 返回对象
 * @param result
 * @return
 */
    static ResponseHandler obResponse(Result result) {
        with result.toString()
    }

    static ResponseHandler success(Object result) {
        with obResponse(Result.success(result))
    }

    static ResponseHandler fail(Object result) {
        with obResponse(Result.fail(result))
    }
/**
 * 随机response
 * @param handlers
 * @return
 */
    static ResponseHandler randownResponse(ResponseHandler handler, ResponseHandler... handlers) {
        random handler, handlers
    }

/**
 * 随机返回闻文本
 * @param contents
 * @return
 */
    static ResponseHandler cycleResponse(String content, String... contents) {
        cycle content, contents
    }
/**
 * 随机返回闻文本
 * @param contents
 * @return
 */
    static ResponseHandler cycleResponse(JSONObject content, JSONObject... contents) {
        cycle content.toString(), (String[]) contents.toList().stream().map { x -> x.toString() }.toArray()
    }


    /**
     * 随机response
     * @param handlers
     * @return
     */
    static ResponseHandler cycleResponse(ResponseHandler handler, ResponseHandler... handlers) {
        cycle handler, handlers
    }

/**
 * 随机返回闻文本
 * @param contents
 * @return
 */
    static ResponseHandler randownResponse(String content, String... contents) {
        random content, contents
    }
/**
 * 随机返回闻文本
 * @param contents
 * @return
 */
    static ResponseHandler randownResponse(JSONObject content, JSONObject... contents) {
        random content.toString(), (String[]) contents.toList().stream().map { x -> x.toString() }.toArray()
    }


/**
 * 随机返回文本，会停留在最后一个文本内容
 * @param content
 * @param contents
 * @return
 */
    static ResponseHandler sequenceResponse(String content, String... contents) {
        seq content, contents
    }
/**
 * 随机返回文本，会停留在最后一个文本内容
 * @param content
 * @param contents
 * @return
 */
    static ResponseHandler sequenceResponse(JSONObject content, JSONObject... contents) {
        seq content.toString(), (String[]) contents.toList().stream().map { x -> x.toString() }.toArray()
    }

/**
 * 随机返回，最后会停留在最后一个handle
 * @param handler
 * @param handlers
 * @return
 */
    static ResponseHandler sequenceResponse(ResponseHandler handler, ResponseHandler... handlers) {
        seq handler, handlers
    }

/**
 * 设置cookie，只支持一个cookie设置，因为header不允许相同的key重复
 * @param key
 * @param value
 * @return
 */
    static ResponseHandler setCookie(String key, String value) {
        cookie key, value
    }

/**
 * 设置header
 * @param key
 * @param value
 * @return
 */
    static ResponseHandler setHeader(String key, String value) {
        header key, value
    }

/**
 * 批量设置header
 * @param json
 * @return
 */
    static ResponseHandler[] setHeader(JSONObject json) {
        json.keySet().stream().map { x -> setHeader(x.toString(), json.getString(x)) }.toArray() as ResponseHandler[]
    }

/**
 * 设置HTTP响应码，默认200
 * @param code
 * @return
 */
    static ResponseHandler setStatus(int code) {
        status code
    }

/**
 * 代理地址
 * @param url
 * @return
 */
    static ResponseHandler setProxy(String url) {
        proxy url
    }

/**
 * 延迟
 * @param duration 时间，单位：毫秒
 * @return
 */
    static LatencyProcedure delay(long duration) {
        latency duration, TimeUnit.MILLISECONDS
    }

/**
 * 随机
 * @param content
 * @param contents
 * @return
 */
    static random(String content, String... contents) {
        new RandomHandler(FluentIterable.from(asIterable(content, contents)).transform(textToResource()))
    }

/**
 * 随机
 * @param handler
 * @param handlers
 * @return
 */
    static ResponseHandler random(ResponseHandler handler, ResponseHandler... handlers) {
        new RandomHandler(asIterable(handler, handlers))
    }

/**
 * 循环返回
 * @param content
 * @param contents
 * @return
 */
    static ResponseHandler cycle(String content, String... contents) {
        new CycleHandle(FluentIterable.from(asIterable(content, contents)).transform(textToResource()))
    }

/**
 * 循环返回
 * @param handler
 * @param handlers
 * @return
 */
    static ResponseHandler cycle(final ResponseHandler handler, final ResponseHandler... handlers) {
        return new CycleHandle(asIterable(handler, handlers))
    }
}