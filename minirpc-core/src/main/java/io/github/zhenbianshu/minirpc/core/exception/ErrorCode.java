package io.github.zhenbianshu.minirpc.core.exception;

/**
 * @author zbs
 * @date 2019/10/9
 */
public enum ErrorCode {
    /**
     * 框架错误
     */
    INTERNAL(1001),

    TRANSPORT(1002),

    SPRING(1003);


    private int codeNum;

    ErrorCode(int codeNum) {
        this.codeNum = codeNum;
    }

    public int getCodeNum() {
        return codeNum;
    }
}
