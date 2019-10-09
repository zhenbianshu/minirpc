package io.github.zhenbianshu.minirpc.core.exception;

/**
 * @author zbs
 * @date 2019/10/9
 */
public class RpcException extends RuntimeException {
    protected String errMsg;

    protected ErrorCode errorCode;

    public RpcException(String errMsg) {
        this.errMsg = errMsg;
        this.errorCode = ErrorCode.INTERNAL;
    }

    public RpcException(String errMsg, ErrorCode errorCode) {
        this.errMsg = errMsg;
        this.errorCode = errorCode;
    }
}
