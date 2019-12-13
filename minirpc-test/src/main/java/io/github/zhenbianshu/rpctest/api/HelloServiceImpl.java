package io.github.zhenbianshu.rpctest.api;

/**
 * @author zbs
 * @date 2019/9/9
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String say(String someone) {
        return "hello " + someone;
    }
}
