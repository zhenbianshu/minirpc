package io.github.zhenbianshu.minirpc.core;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zbs
 * @date 2019/9/9
 */
public class ResponseFuture extends CompletableFuture<Object> {
    public static Map<Long, CompletableFuture<Object>> REQUEST_TO_FUTURE = new ConcurrentHashMap<>();

    public ResponseFuture(Request request) {
        REQUEST_TO_FUTURE.put(request.getRequestId(), this);
    }

    public static void remove(Request request) {
        REQUEST_TO_FUTURE.remove(request.getRequestId());
    }
}
